package com.mwb.web.controller;

import com.mwb.web.model.LoginUser;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.LoginUserService;
import com.mwb.web.utils.AESUtil;
import com.mwb.web.utils.HttpUtil;
import com.mwb.web.utils.WebConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Controller
public class LoginController {
    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping("/html/{page}")
    public ModelAndView page(LoginUser loginUser, @PathVariable("page") String page) {
        page = page.substring(0, page.contains(".") ? page.indexOf(".") : page.length());
        ModelAndView modelAndView = new ModelAndView("/" + page);
        if (loginUser != null) {
            modelAndView.addObject("userName", loginUser.getName());
        }
        return modelAndView;
    }

    @RequestMapping("/login/out")
    public ModelAndView out(HttpServletResponse response) {
        HttpUtil.clearCookie(response, WebConstant.WAP_COOKIE_NAME, WebConstant.TC_EXPIRE_TIME, "/");
        return new ModelAndView("/html/login");
    }

    @ResponseBody
    @RequestMapping("/login/to")
    public ApiResult to(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletResponse response) {
        LoginUser user = loginUserService.getByName(name);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                String token = AESUtil.encrypt(String.valueOf(user.getId()));
                HttpUtil.saveCookie(response, WebConstant.WAP_COOKIE_NAME, token, WebConstant.TC_EXPIRE_TIME, "/");
                return ApiResult.success(user);
            }
            return ApiResult.failed("密码错误");
        }
        return ApiResult.failed("用户名不存在");
    }

    @ResponseBody
    @RequestMapping("/register")
    public ApiResult register(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletResponse response) {
        if (StringUtils.isBlank(name)) {
            return ApiResult.failed("用户名不可为空");
        }
        if (StringUtils.isBlank(password)) {
            return ApiResult.failed("密码不可为空");
        }
        LoginUser user = loginUserService.getByName(name);
        if (user != null) {
            return ApiResult.failed("用户名已存在");
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setAddTime(new Date());
        loginUser.setUpdateTime(new Date());
        loginUser.setPassword(password);
        loginUser.setName(name);
        int result = loginUserService.save(loginUser);
        String token = AESUtil.encrypt(String.valueOf(loginUser.getId()));
        HttpUtil.saveCookie(response, WebConstant.WAP_COOKIE_NAME, token, WebConstant.TC_EXPIRE_TIME, "/");
        return ApiResult.success(result);
    }

    @ResponseBody
    @RequestMapping("/password/update")
    public ApiResult passwordUpdate(LoginUser loginUser, String oldPassword, String newPassword) {
        if (!loginUser.getPassword().equals(oldPassword)) {
            return ApiResult.failed("当前密码不正确");
        }
        loginUser.setPassword(newPassword);
        loginUser.setUpdateTime(new Date());
        loginUserService.updateNotNull(loginUser);
        return ApiResult.success();
    }
}