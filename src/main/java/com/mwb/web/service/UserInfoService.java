package com.mwb.web.service;

import com.mwb.web.model.UserInfo;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface UserInfoService extends BaseService<UserInfo> {

    long register(UserInfo userInfo);

    UserInfo getByAccount(String name);

    UserInfo getById(long id);

    UserInfo update(UserInfo user);

    void updateCache(long id);
}
