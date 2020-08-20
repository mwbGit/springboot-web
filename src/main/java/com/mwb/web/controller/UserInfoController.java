package com.mwb.web.controller;

import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.service.OssService;
import com.mwb.web.service.UserInfoService;
import com.mwb.web.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService loginUserService;

    @Autowired
    private OssService ossService;


    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/password/update")
    public ApiResult passwordUpdate(UserInfo userInfo, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        log.info("UserInfoController.passwordUpdate name={},newPassword={},oldPassword={}", userInfo, oldPassword, newPassword);
        if (!userInfo.getPassword().equals(oldPassword)) {
            return ApiResult.failed("当前密码不正确");
        } else if (newPassword.length() > 12) {
            return ApiResult.failed("密码过长");
        } else if (newPassword.length() < 6) {
            return ApiResult.failed("密码过短");
        }
        userInfo.setPassword(MD5Util.md5(newPassword));
        userInfo.setUpdateTime(new Date());
        loginUserService.update(userInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.MUST, valid = true, accessMax = 1, accessSeconds = 3)
    @RequestMapping("/user/update")
    public ApiResult userUpdate(UserInfo userInfo, @RequestParam("name") String name, @RequestParam(value = "wechat", required = false, defaultValue = "") String wechat,
                                @RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("UserInfoController.userUpdate name={},wechat={},file={}", userInfo, wechat, file == null);
        if (StringUtils.isBlank(name)) {
            return ApiResult.failed("用户名不可以为空");
        } else if (name.length() > 16) {
            return ApiResult.failed("用户名过长");
        } else if (name.length() < 3) {
            return ApiResult.failed("用户名过短");
        }
        userInfo.setName(name);
        if (StringUtils.isNotBlank(wechat)) {
            userInfo.setWechat(wechat);
        }
        if (file != null) {
            String url = ossService.uploadImage(file);
            if (StringUtils.isNotBlank(url)) {
                userInfo.setHeadImg(url);
            }
        }
        userInfo.setUpdateTime(new Date());
        loginUserService.update(userInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/user/detail")
    public ApiResult detail(UserInfo userInfo) {
        UserInfo user = new UserInfo();
        user.setName(userInfo.getName());
        user.setHeadImg(userInfo.getHeadImg());
        user.setWechat(userInfo.getWechat());
        user.setAccount(userInfo.getAccount());
        return ApiResult.success(user);
    }
}