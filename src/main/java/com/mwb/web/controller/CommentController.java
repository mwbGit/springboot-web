package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.UserRobot;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.model.query.CommentQuery;
import com.mwb.web.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/search")
    public ApiResult search(CommentQuery query) {
        PageInfo<CommentInfo> pageInfo = commentService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/save")
    public ApiResult save(UserInfo userInfo, CommentInfo commentInfo, @RequestParam("userType") long userType) {
        if (userType == 0) {
            commentInfo.setUserId(userInfo.getId());
            commentInfo.setUserName(userInfo.getName());
            commentInfo.setHeadImg(userInfo.getHeadImg());
        } else {
            commentInfo.setUserId(userInfo.getId());
            commentInfo.setUserName(UserRobot.getName(userType));
            commentInfo.setUserName(UserRobot.getImg(userType));
        }
        commentInfo.setStatus(1);
        commentService.saveOrUpdate(commentInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/audit")
    public ApiResult audit(@RequestParam("id") long id, @RequestParam("status") int status,
                           @RequestParam(value = "reason", required = false, defaultValue = "") String reason) {
        commentService.audit(id, status, reason);
        return ApiResult.success();
    }

}