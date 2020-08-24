package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.PraiseInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.UserRobot;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.model.query.DynamicQuery;
import com.mwb.web.service.CommentService;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.OssService;
import com.mwb.web.service.PraiseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Random;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/dynamic")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PraiseService praiseInfoService;

    @Autowired
    private OssService ossService;

    @RequestMapping("/new")
    public ApiResult newList(UserInfo userInfo) {
        DynamicQuery query = new DynamicQuery();
        query.setPageSize(5);
        query.setStatus(1);
        return ApiResult.success(dynamicService.search(query, userInfo.getId()).getList());
    }

    @RequestMapping("/search")
    public ApiResult search(UserInfo userInfo, DynamicQuery query) {
        PageInfo<DynamicInfo> pageInfo = dynamicService.search(query, userInfo == null ? 0 : userInfo.getId());
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }

    @WebLogin(option = WebLogin.Option.MUST, valid = true, accessMax = 1, accessSeconds = 3)
    @RequestMapping("/save")
    public ApiResult save(UserInfo userInfo, @RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam(value = "file", required = false) MultipartFile file) {
        if (StringUtils.isBlank(title)) {
            return ApiResult.failed("请输入标题");
        } else if (StringUtils.isBlank(content)) {
            return ApiResult.failed("请输入内容");
        } else if (title.length() > 100) {
            return ApiResult.failed("标题最多100个字");
        } else if (content.length() > 1000) {
            return ApiResult.failed("内容最多1000个字");
        }
        DynamicInfo dynamicInfo = new DynamicInfo();
        dynamicInfo.setTitle(title);
        dynamicInfo.setContent(content);
        dynamicInfo.setUserId(userInfo.getId());
        dynamicInfo.setUserName(userInfo.getName());
        dynamicInfo.setHeadImg(userInfo.getAvatar());
        dynamicInfo.setAddTime(new Date());
        dynamicInfo.setUpdateTime(new Date());
        if (file != null) {
            String url = ossService.uploadImage(file);
            if (StringUtils.isNotBlank(url)) {
                dynamicInfo.setImages(new String[]{url});
            }
        }

        dynamicService.saveOrUpdate(dynamicInfo);
        log.info("DynamicController.save user={},title={},content={},file={}", userInfo, title, content, file == null);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.MUST, valid = true, accessMax = 2, accessSeconds = 10)
    @RequestMapping("/comment")
    public ApiResult commentSave(UserInfo user, @RequestParam("dynamicId") long dynamicId,
                                 @RequestParam("comment") String comment, @RequestParam(value = "replyName", required = false, defaultValue = "") String replyName) {
        if (dynamicId <= 0) {
            return ApiResult.failed("参数错误");
        } else if (StringUtils.isBlank(comment)) {
            return ApiResult.failed("请输入评论内容");
        } else if (comment.length() > 100) {
            return ApiResult.failed("最多可评论100个字");
        }
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setDynamicId(dynamicId);
        commentInfo.setContent(comment);
        commentInfo.setUserId(user.getId());
        commentInfo.setUserName(user.getName());
        commentInfo.setHeadImg(user.getAvatar());
        commentInfo.setReplyName(replyName);
        commentService.saveOrUpdate(commentInfo);
        log.info("DynamicController.commentSave user={},dynamicId={},comment={},replyName={}", user, dynamicId, comment, replyName);
        return ApiResult.success();
    }

    @RequestMapping("/praise")
    public ApiResult praise(UserInfo user, @RequestParam("dynamicId") long dynamicId) {
        if (user != null && dynamicId > 0) {
            PraiseInfo praiseInfo = new PraiseInfo();
            praiseInfo.setObjectId(dynamicId);
            praiseInfo.setUserId(user.getId());
            praiseInfoService.savePraiseInfo(praiseInfo);
        }
        log.info("DynamicController.commentSave user={},dynamicId={}", user, dynamicId);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/audit")
    public ApiResult audit(@RequestParam("id") long id, @RequestParam("status") int status) {
        dynamicService.audit(id, status, true);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/publish")
    public ApiResult publish(UserInfo userInfo, DynamicInfo dynamicInfo, @RequestParam("userType") int userType) {
        if (StringUtils.isBlank(dynamicInfo.getTitle())) {
            return ApiResult.failed("请输入标题");
        } else if (StringUtils.isBlank(dynamicInfo.getContent())) {
            return ApiResult.failed("请输入内容");
        }
        if (userType == 0) {
            dynamicInfo.setUserId(userInfo.getId());
            dynamicInfo.setUserName(userInfo.getName());
            dynamicInfo.setHeadImg(userInfo.getHeadImg());
        } else {
            dynamicInfo.setUserId(userInfo.getId());
            dynamicInfo.setUserName(UserRobot.getName(userType));
            dynamicInfo.setHeadImg(UserRobot.getImg(userType));
        }

        dynamicInfo.setPraiseNum(10 + new Random().nextInt(20));
        dynamicInfo.setStatus(1);
        if (StringUtils.isNotBlank(dynamicInfo.getImages())) {
            dynamicInfo.setImages(dynamicInfo.getImages().split(","));
        }
        dynamicService.saveOrUpdate(dynamicInfo);
        log.info("DynamicController.save user={},dynamicInfo={},userType={}", userInfo, dynamicInfo, userType);
        return ApiResult.success();
    }
}