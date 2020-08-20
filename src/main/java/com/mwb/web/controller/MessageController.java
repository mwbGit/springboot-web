package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.model.query.MsgQuery;
import com.mwb.web.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/list")
    public ApiResult search(UserInfo user, MsgQuery msgQuery) {
        msgQuery.setUserId(user.getId());
        PageInfo<MessageInfo> pageInfo = messageService.search(msgQuery);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/new")
    public ApiResult newList(UserInfo user) {
        MsgQuery msgQuery = new MsgQuery();
        msgQuery.setUserId(user.getId());
        msgQuery.setPageSize(10);
        msgQuery.setStatus(0);
        PageInfo<MessageInfo> messageInfos = messageService.search(msgQuery);
        return ApiResult.success(messageInfos.getList());
    }

    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/readAll")
    public ApiResult readAll(UserInfo user) {
        messageService.readAll(user.getId());
        log.info("MessageController.readAll user={}", user);
        return ApiResult.success();
    }

}