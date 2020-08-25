package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.UserRobot;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.model.query.DynamicQuery;
import com.mwb.web.model.query.UserQuery;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.OssService;
import com.mwb.web.service.UserInfoService;
import com.mwb.web.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userService;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private OssService ossService;

    /**
     * 主页信息
     *
     * @param
     * @return
     */
    @RequestMapping("/info")
    public ModelAndView info(UserInfo userInfo, @RequestParam(value = "userId", required = false) Long userId,
                             @RequestParam(value = "dynamicId", required = false) Long dynamicId,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView("/user_info");
        boolean admin = userInfo != null && userInfo.isAdmin();
        long curUserId = userInfo != null ? userInfo.getId() : 0;
        DynamicInfo dynamicInfo = null;
        DynamicQuery query = new DynamicQuery();
        query.setShowComment(true);

        if (dynamicId != null) {
            query.setId(dynamicId);
            if (admin) {
                query.setStatus(-1);
            }
            List<DynamicInfo> dynamics = dynamicService.search(query, curUserId).getList();
            if (CollectionUtils.isEmpty(dynamics)) {
                return null;
            }
            dynamicInfo = dynamics.get(0);
            userId = dynamicInfo.getUserId();
        }

        UserInfo user = userService.selectByKey(userId);
        if (user != null) {
            user.setAccount(null);
            user.setPassword(null);
            if (user.isFrozen()) {
                user.setName(UserRobot.DEFAULT_NAME);
                user.setHeadImg(UserRobot.DEFAULT_AVATAR);
            }
            query.setPageSize(10);
            query.setUserId(userId);
            query.setStatus(1);
            query.setId(null);
            query.setPage(page);
            PageInfo<DynamicInfo> allDynamics = dynamicService.search(query, curUserId);
            query.setOrder("praiseNum");
            query.setPage(1);
            List<DynamicInfo> hotDynamics = dynamicService.search(query, curUserId).getList();

            modelAndView.addObject("userInfo", user);
            modelAndView.addObject("hot", hotDynamics);
            modelAndView.addObject("all", allDynamics);
            modelAndView.addObject("dynamicInfo", dynamicInfo);
        }
        modelAndView.addObject("page", page);
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

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
        userInfo.setPassword(MD5Util.md5Pwd(newPassword));
        userInfo.setUpdateTime(new Date());
        userService.update(userInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.MUST, valid = true, accessMax = 1, accessSeconds = 3)
    @RequestMapping("/info/update")
    public ApiResult userUpdate(UserInfo userInfo, @RequestParam("name") String name, @RequestParam(value = "wechat", required = false, defaultValue = "") String wechat,
                                @RequestParam(value = "introduce", required = false, defaultValue = "") String introduce, @RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("UserInfoController.userUpdate name={},wechat={},file={}", userInfo, wechat, file == null);
        if (StringUtils.isBlank(name)) {
            return ApiResult.failed("用户名不可以为空");
        } else if (name.length() > 16) {
            return ApiResult.failed("用户名过长");
        } else if (name.length() < 3) {
            return ApiResult.failed("用户名过短");
        } else if (introduce.length() > 1000) {
            return ApiResult.failed("用户名过短");
        }
        userInfo.setName(name);
        if (StringUtils.isNotBlank(wechat)) {
            userInfo.setWechat(wechat);
        }
        if (StringUtils.isNotBlank(introduce)) {
            userInfo.setIntroduce(introduce);
        }
        if (file != null) {
            String url = ossService.uploadImage(file);
            if (StringUtils.isNotBlank(url)) {
                userInfo.setHeadImg(url);
            }
        }
        userInfo.setUpdateTime(new Date());
        userService.updateNotNull(userInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.MUST)
    @RequestMapping("/detail")
    public ApiResult detail(UserInfo userInfo) {
        UserInfo user = new UserInfo();
        user.setName(userInfo.getName());
        user.setHeadImg(userInfo.getAvatar());
        user.setWechat(userInfo.getWechat());
        user.setAccount(userInfo.getAccount());
        user.setIntroduce(userInfo.getIntroduce());
        return ApiResult.success(user);
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/search")
    public ApiResult search(UserQuery query) {
        PageInfo<UserInfo> pageInfo = userService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/audit")
    public ApiResult audit(UserInfo userInfo, @RequestParam("status") int status, @RequestParam("id") Long id,
                           @RequestParam(value = "reason", required = false, defaultValue = "") String reason) {
        log.info("UserController.audit userId={}, id={}, status={},reason={}", userInfo.getId(), id, status, reason);
        return ApiResult.success(userService.audit(id, status, reason));
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/add")
    public ApiResult add(UserInfo userInfo, @RequestParam("account") String account, @RequestParam("name") String name,
                         @RequestParam("status") int status, @RequestParam("sex") int sex,
                         @RequestParam("password") String password, @RequestParam("identity") int identity) {
        UserInfo user = userService.getByAccount(account);
        if (user != null) {
            return ApiResult.failed("账号名已存在");
        }

        UserInfo newUser = new UserInfo();
        newUser.setAccount(account);
        newUser.setName(name);
        newUser.setStatus(status);
        newUser.setSex(sex);
        newUser.setIdentity(identity);
        newUser.setPassword(MD5Util.md5Pwd(password));
        newUser.setAddTime(new Date());
        newUser.setUpdateTime(new Date());
        userService.saveNotNull(newUser);

        log.info("UserController.add userId={}, userInfo={}", userInfo.getId(), newUser);
        return ApiResult.success();
    }
}