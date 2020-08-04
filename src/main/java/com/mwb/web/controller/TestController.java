package com.mwb.web.controller;

import com.mwb.web.model.LoginUser;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/3
 */
@Controller
public class TestController {

    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping("/test")
    public ModelAndView test(LoginUser user) {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/test1")
    public ApiResult test1(LoginUser user) {
        int a = 1;
        if (a == 1) {
            throw new RuntimeException();
        }
        return ApiResult.success();
    }


    @ResponseBody
    @RequestMapping("/test2")
    public ApiResult test2(LoginUser user) {
        user = loginUserService.getById(user.getId());
        return ApiResult.success(user);
    }
}
