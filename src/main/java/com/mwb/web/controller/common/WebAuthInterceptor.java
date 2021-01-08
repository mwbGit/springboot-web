package com.mwb.web.controller.common;

import com.alibaba.fastjson.JSON;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.AccessLimit;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.service.AccessCacheService;
import com.mwb.web.service.UserInfoService;
import com.mwb.web.utils.AESUtil;
import com.mwb.web.utils.HttpUtil;
import com.mwb.web.utils.WebConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Configuration
public class WebAuthInterceptor extends HandlerInterceptorAdapter {
    private final static Logger accessLog = LoggerFactory.getLogger(WebAuthInterceptor.class);
    private static long TOTAL_ACCESS = 0;
    private final static String ERROR_URI = "/error";

    @Autowired
    private UserInfoService loginUserService;

    @Autowired
    private AccessCacheService accessCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long userId = getLoginUserId(request);
        String ip = HttpUtil.getIpAddress(request);
        String uri = request.getRequestURI();
        accessLog.info("access-total_access:{},ip: {} userId: {} uri: {}", TOTAL_ACCESS++, ip, userId, uri);
        if (ERROR_URI.equals(uri)) {
            return true;
        }

        //黑名单校验
        if (accessCacheService.isBlackList(ip)) {
            accessLog.warn("access-total_access:{},is black ip: {} userId: {} uri: {}", TOTAL_ACCESS++, ip, userId, uri);
            return false;
        }

        UserInfo userInfo = null;
        if (userId > 0) {
            userInfo = loginUserService.getCacheById(userId);
        }

        // 权限校验
        WebLogin annotation = ((HandlerMethod) handler).getMethodAnnotation(WebLogin.class);
        if (annotation != null) {
            if (annotation.option() == WebLogin.Option.MUST) {
                if (userInfo == null) {
                    accessLog.info("---access---noLogin---ip: {} ,uri: {} ，userInfo:{}", ip, uri, userInfo);
                    authError(response, "请登录后操作");
                    return false;
                } else if (annotation.valid()) {
                    if (userInfo.isFrozen()) {
                        authError(response, "账号已冻结");
                        accessLog.info("---access---isFrozen---ip: {} ,uri: {} ，userInfo:{}", ip, uri, userInfo);
                        return false;
                    } else if (userInfo.unPass()) {
                        authError(response, "请修改个人信息后重试");
                        accessLog.info("---access---unPass---ip: {} ,uri: {} ，userInfo:{}", ip, uri, userInfo);
                        return false;
                    }
                }
            } else if (annotation.option() == WebLogin.Option.ADMIN) {
                if (userInfo == null || userInfo.getIdentity() != 1) {
                    authError(response, "无权限操作");
                    accessLog.info("---access---identity---ip: {} ,uri: {} ，userInfo:{}", ip, uri, userInfo);
                    return false;
                }
            }
        }

        // 频次限制 默认 3次uri/1秒
        int accessMax = annotation != null ? annotation.accessMax() : 3;
        int accessSeconds = annotation != null ? annotation.accessSeconds() : 1;
        if (accessMax > 0 && accessSeconds > 0) {
            AccessLimit accessLimit = accessCacheService.get(ip, uri);
            if (accessLimit != null) {
                int curNum = 0;
                long lastTime = System.currentTimeMillis() - (accessSeconds * 1000);
                for (long time : accessLimit.getTimes()) {
                    if (time > lastTime) {
                        curNum++;
                    } else {
                        break;
                    }
                }
                accessCacheService.put(ip, uri);
                if (curNum > accessMax) {
                    authError(response, "请求过于频繁，请稍后再试");
                    accessLog.info("---access---limit---userId: {} ,ip: {} ,uri:{}，curNum:{}", userId, ip, uri, curNum);
                    return false;
                }
            } else {
                accessCacheService.put(ip, uri);
            }
        }

        if (userInfo != null) {
            request.setAttribute(WebConstant.USER_BASE, userInfo);
        }
        return true;
    }

    private long getLoginUserId(HttpServletRequest request) {
        String wt = HttpUtil.getCookieValue(WebConstant.WAP_COOKIE_NAME, request);
        if (StringUtils.isEmpty(wt)) {
            return 0;
        }
        String uid = AESUtil.decrypt(wt);
        if (StringUtils.isNotBlank(uid)) {
            try {
                return Long.parseLong(uid);
            } catch (Exception e) {
                accessLog.error("WebConfigurerAdapter.getLoginUserId uid={}, err", uid, e);
            }
        }
        return 0;
    }

    private void authError(HttpServletResponse response, String msg) throws IOException {
        sendFailedResp(response, JSON.toJSONString(ApiResult.failed(msg)));
    }

    private void sendFailedResp(HttpServletResponse response, String respStr) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        try {
            pw.write(respStr);
        } finally {
            pw.flush();
            pw.close();
        }
    }
}