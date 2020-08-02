package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.User;
import com.mwb.web.model.UserQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface UserService extends BaseService<User> {

    PageInfo<User> search(UserQuery userQuery);

    List<User> getAll();

    User saveOrUpdate(User user);


}