package com.mwb.web.controller;

import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.UserInfoService;
import com.mwb.web.utils.AESUtil;
import com.mwb.web.utils.HttpUtil;
import com.mwb.web.utils.MD5Util;
import com.mwb.web.utils.WebConstant;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserInfoService loginUserService;

    @GetMapping("/captcha")
    public void happyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response).length(4).height(38).type(CaptchaType.NUMBER).build().finish();
    }

    @RequestMapping("/login/out")
    public ModelAndView out(HttpServletResponse response) {
        HttpUtil.clearCookie(response, WebConstant.WAP_COOKIE_NAME, WebConstant.TC_EXPIRE_TIME, "/");
        return new ModelAndView("/html/login");
    }

    @RequestMapping("/login/to")
    public ApiResult to(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {
        log.info("UserInfoController.login account={},password={}", account, password);
        if (!HappyCaptcha.verification(request, code, true)) {
            return ApiResult.failed("验证码错误");
        }
        UserInfo user = loginUserService.getByAccount(account);
        if (user != null) {
            if (user.isFrozen()) {
                return ApiResult.failed("账号已被冻结");
            }
            if (user.getPassword().equals(MD5Util.md5Pwd(password))) {
                String token = AESUtil.encrypt(String.valueOf(user.getId()));
                HttpUtil.saveCookie(response, WebConstant.WAP_COOKIE_NAME, token, WebConstant.TC_EXPIRE_TIME, "/");
                return ApiResult.success(user);
            }
            return ApiResult.failed("密码错误");
        }
        return ApiResult.failed("账号不存在");
    }

    @RequestMapping("/register")
    public ApiResult register(@RequestParam("account") String account, @RequestParam("sex") int sex, @RequestParam("password") String password, @RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {
        log.info("UserInfoController.register name={},password={}", account, password);
        if (!HappyCaptcha.verification(request, code, true)) {
            return ApiResult.failed("验证码错误");
        } else if (StringUtils.isBlank(account)) {
            return ApiResult.failed("账号名不可为空");
        } else if (account.length() > 16) {
            return ApiResult.failed("账号名过长");
        } else if (account.length() < 3) {
            return ApiResult.failed("账号名过短");
        } else if (StringUtils.isBlank(password)) {
            return ApiResult.failed("密码不可为空");
        } else if (password.length() > 12) {
            return ApiResult.failed("密码过长");
        } else if (password.length() < 6) {
            return ApiResult.failed("密码过短");
        }
        UserInfo user = loginUserService.getByAccount(account);
        if (user != null) {
            return ApiResult.failed("账号名已存在");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(MD5Util.md5Pwd(password));
        userInfo.setAccount(account);
        userInfo.setName(account);
        userInfo.setSex(sex);
        long id = loginUserService.register(userInfo);
        String token = AESUtil.encrypt(String.valueOf(userInfo.getId()));
        HttpUtil.saveCookie(response, WebConstant.WAP_COOKIE_NAME, token, WebConstant.TC_EXPIRE_TIME, "/");
        return ApiResult.success(id > 0);
    }


}