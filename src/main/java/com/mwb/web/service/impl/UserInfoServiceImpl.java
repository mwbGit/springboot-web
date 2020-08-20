package com.mwb.web.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mwb.web.mapper.UserInfoMapper;
import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.service.MessageService;
import com.mwb.web.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Service("loginUserService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private MessageService messageService;

    private static Cache<Long, UserInfo> USER_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public long register(UserInfo userInfo) {
        userInfo.setAddTime(new Date());
        userInfo.setUpdateTime(new Date());
        saveNotNull(userInfo);
        if (userInfo.getId() > 0) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setUserId(userInfo.getId());
            messageInfo.setType(1);
            messageInfo.setStatus(0);
            messageInfo.setTitle("注册成功");
            messageInfo.setBody("欢迎来到猫咪之家");
            messageService.sendMsg(messageInfo);
        }
        return userInfo.getId();
    }

    @Override
    public UserInfo getByAccount(String account) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", account);
        return selectOneByExample(example);
    }

    @Override
    public UserInfo getById(long id) {
        UserInfo userInfo = USER_CACHE.getIfPresent(id);
        if (userInfo == null) {
            userInfo = selectByKey(id);
            if (userInfo != null) {
                int num = messageService.unReadNum(id);
                userInfo.setUnReadMsgNum(num);
                USER_CACHE.put(id, userInfo);
            }
        }
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo user) {
        updateNotNull(user);
        USER_CACHE.put(user.getId(), user);
        return user;
    }

    @Override
    public void updateCache(long id) {
        USER_CACHE.invalidate(id);
    }
}
