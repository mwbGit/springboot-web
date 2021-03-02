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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

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
    @RequestMapping("/alone/send")
    public ApiResult aloneSend(UserInfo userInfo, @RequestParam("userId") long userId, @RequestParam("content") String content) {
        log.info("MessageController.aloneSend userInfo={},userId={},content={}", userInfo, userId, content);
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setType(2);
        messageInfo.setTitle("来自用户" + userInfo.getName() + "的私信");
        messageInfo.setBody(content);
        messageInfo.setUserId(userInfo.getId());
        messageInfo.setObjectId(userId);
        messageInfo.setStatus(2);
        messageInfo.setAddTime(new Date());
        messageService.saveNotNull(messageInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/list")
    public ApiResult list(UserInfo user, MsgQuery msgQuery) {
        msgQuery.setUserId(user.getId());
        msgQuery.setStatusList(Arrays.asList(0, 1));
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

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/send")
    public ApiResult batchSend(MessageInfo messageInfo) {
        messageService.batchSend(messageInfo);
        log.info("MessageController.batchSend msg={}", messageInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/search")
    public ApiResult search(MsgQuery msgQuery) {
        msgQuery.setType(3);
        PageInfo<MessageInfo> pageInfo = messageService.search(msgQuery);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/audit")
    public ApiResult audit(@RequestParam("id") Long id, @RequestParam("status") int status) {
        MessageInfo messageInfo = messageService.selectByKey(id);
        if (messageInfo != null) {
            messageInfo.setStatus(status);
            messageService.updateNotNull(messageInfo);
            //todo msg
        }
        return ApiResult.success();
    }
}