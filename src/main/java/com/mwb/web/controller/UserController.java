package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.User;
import com.mwb.web.model.UserQuery;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.PageResult;
import com.mwb.web.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/search")
    public ApiResult search(UserQuery query) {
        log.info("------------");
        PageInfo<User> pageInfo = userService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @RequestMapping("/saveOrUpdate")
    public ApiResult saveOrUpdate(User user) {
        return ApiResult.success(userService.saveOrUpdate(user));
    }


    @RequestMapping("/delete")
    public ApiResult delete(long id) {
        userService.delete(id);
        return ApiResult.success();
    }

    @RequestMapping("/detail")
    public ApiResult detail(long id) {
        return ApiResult.success(userService.selectByKey(id));
    }

    @RequestMapping("/detail1")
    public ApiResult detail1(long id) {
        try {
            throw new RuntimeException();

        } catch (Exception e) {
            log.error("aaaaaaaaaaaaaaaaaaa", e);
        }
        return ApiResult.failed();
    }

}