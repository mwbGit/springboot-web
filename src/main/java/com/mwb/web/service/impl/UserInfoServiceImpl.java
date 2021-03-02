package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mwb.web.mapper.UserInfoMapper;
import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.query.UserQuery;
import com.mwb.web.service.MessageService;
import com.mwb.web.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
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
            messageInfo.setType(0);
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
    public UserInfo getCacheById(long id) {
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

    @Override
    public PageInfo<UserInfo> search(UserQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (query.getId() != null) {
            criteria.andEqualTo("id", query.getId());
        }
        if (StringUtils.isNotBlank(query.getName())) {
            criteria.andLike("name", "%" + query.getName() + "%");
        }
        if (StringUtils.isNotBlank(query.getAccount())) {
            criteria.andLike("account", "%" + query.getName() + "%");
        }
        if (query.getSex() != null) {
            criteria.andEqualTo("sex", query.getSex());
        }
        if (query.getStatus() != null) {
            criteria.andEqualTo("status", query.getStatus());
        }
        example.orderBy("id").desc();
        return new PageInfo<>(selectByExample(example));
    }

    @Override
    public boolean audit(long id, int status, String reason) {
        UserInfo curUser = selectByKey(id);
        if (curUser != null && curUser.getStatus() != status) {
            curUser.setStatus(status);
            updateNotNull(curUser);
            if (StringUtils.isNotBlank(reason)) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setUserId(id);
                messageInfo.setType(0);
                messageInfo.setTitle("个人信息被驳回");
                messageInfo.setBody("原因：" + reason);
                messageInfo.setAddTime(new Date());
                messageService.sendMsg(messageInfo);
            }
        }
        return false;
    }

    @Override
    public List<Long> getValidIds() {
        return userInfoMapper.selectByStatus();
    }
}
