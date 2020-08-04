package com.mwb.web.controller.common;

import com.alibaba.fastjson.JSON;
import com.mwb.web.model.LoginUser;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.LoginUserService;
import com.mwb.web.utils.AESUtil;
import com.mwb.web.utils.HttpUtil;
import com.mwb.web.utils.WebConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

@Slf4j
@Configuration
public class WebConfigurerAdapter extends HandlerInterceptorAdapter {

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long userId = getLoginUserId(request);
        if (userId <= 0L) {
            response.sendRedirect("/html/login");
            return false;
        }
        LoginUser loginUser = loginUserService.getById(userId);
        if (loginUser == null) {
            authError(response, "用户权限限制");
            return false;
        }
        request.setAttribute(WebConstant.USER_BASE, loginUser);
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
                log.error("WebConfigurerAdapter.getLoginUserId uid={}, err", uid, e);
            }

        }
        return 0;
    }

    private void authError(HttpServletResponse response, String resultCode) throws IOException {
        sendFailedResp(response, JSON.toJSONString(ApiResult.failed(resultCode)));
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