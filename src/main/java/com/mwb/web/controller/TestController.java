package com.mwb.web.controller;

import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.CommentService;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.MessageService;
import com.mwb.web.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/3
 */
@Controller
public class TestController {

    @Autowired
    private UserInfoService loginUserService;


    @Autowired
    private DynamicService dynamicService;


    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/test")
    public Boolean test(UserInfo user) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setUserId(user.getId());
        messageInfo.setType(1);
        messageInfo.setTitle("注册成功");
        messageInfo.setBody("欢迎来到猫咪之家");
        messageService.sendMsg(messageInfo);

        return true;
    }

    @ResponseBody
    @RequestMapping("/test1")
    public ApiResult test1(UserInfo user) {
        return ApiResult.success(dynamicService.search(new PageQuery(), user.getId()));
    }


    @ResponseBody
    @RequestMapping("/test11")
    public ApiResult test11(UserInfo user) {
        return ApiResult.success(commentService.search(Collections.singletonList(1L)));
    }


    @ResponseBody
    @RequestMapping("/test2")
    public ApiResult test2(String name) {
        UserInfo user = loginUserService.getByAccount(name);
        return ApiResult.success(user);
    }
}
