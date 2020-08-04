package com.mwb.web.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mwb.web.mapper.LoginUserMapper;
import com.mwb.web.model.LoginUser;
import com.mwb.web.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Service("loginUserService")
public class LoginUserServiceImpl extends BaseServiceImpl<LoginUser> implements LoginUserService {

    private static Cache<Long, LoginUser> USER_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public LoginUser getByName(String name) {
        return loginUserMapper.selectByName(name);
    }

    @Override
    public LoginUser getById(long id) {
        LoginUser loginUser = USER_CACHE.getIfPresent(id);
        if (loginUser == null) {
            loginUser = loginUserMapper.selectByPrimaryKey(id);
            if (loginUser != null) {
                USER_CACHE.put(id, loginUser);
            }
        }
        return loginUser;
    }

    @Override
    public LoginUser update(LoginUser user) {
        updateNotNull(user);
        USER_CACHE.put(user.getId(), user);
        return user;
    }

}
