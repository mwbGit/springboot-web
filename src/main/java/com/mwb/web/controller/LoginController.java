package com.mwb.web.controller;

import com.mwb.web.model.LoginUser;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.LoginUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginUserService loginUserService;

    @ResponseBody
    @RequestMapping("/to")
    public ApiResult to(@RequestParam("name") String name, @RequestParam("password") String password) {
        boolean result = loginUserService.login(name, password);
        return ApiResult.success(result);
    }

    @ResponseBody
    @RequestMapping("/register")
    public ApiResult register(LoginUser loginUser) {
        if (StringUtils.isBlank(loginUser.getName())) {
            return ApiResult.failed("用户名不可为空");
        }
        if (StringUtils.isBlank(loginUser.getPassword())) {
            return ApiResult.failed("密码不可为空");
        }
        loginUser.setAddTime(new Date());
        loginUser.setUpdateTime(new Date());
        int result = loginUserService.saveNotNull(loginUser);
        return ApiResult.success(result);
    }
}