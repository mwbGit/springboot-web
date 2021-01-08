package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.query.UserQuery;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface UserInfoService extends BaseService<UserInfo> {

    PageInfo<UserInfo> search(UserQuery query);

    long register(UserInfo userInfo);

    UserInfo getByAccount(String name);

    UserInfo getCacheById(long id);

    UserInfo update(UserInfo user);

}
