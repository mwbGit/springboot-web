package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.FeedbackInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/18
 */
@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/save")
    public ApiResult save(UserInfo user, @RequestParam("content") String content) {
        if (StringUtils.isBlank(content)) {
            return ApiResult.failed("请输入反馈内容");
        }
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setContent(content);
        feedbackInfo.setUserId(user == null ? 0 : user.getId());
        feedbackInfo.setAddTime(new Date());
        feedbackInfo.setUpdateTime(new Date());
        feedbackService.saveNotNull(feedbackInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/search")
    public ApiResult search(PageQuery query) {
        PageInfo<FeedbackInfo> pageInfo = feedbackService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }
}
