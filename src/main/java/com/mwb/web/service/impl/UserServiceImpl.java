package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.UserMapper;
import com.mwb.web.model.User;
import com.mwb.web.model.UserQuery;
import com.mwb.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageInfo<User> search(UserQuery query) {
        //分页
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(User.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(query.getName())) {
            criteria.andLike("name", query.getName() + "%");
        }
        if (query.getId() != null) {
            criteria.andGreaterThan("id", query.getId());
        }
        if (query.getSex() != null) {
            criteria.andEqualTo("sex", query.getSex());
        }
        if (query.getAgeMin() != null) {
            criteria.andGreaterThanOrEqualTo("age", query.getAgeMin());
        }
        if (query.getAgeMax() != null) {
            criteria.andLessThanOrEqualTo("age", query.getAgeMax());
        }
        //排序
        example.orderBy("id").desc();
        //去重
        example.setDistinct(true);
        List<User> users = selectByExample(example);
        return new PageInfo<>(users);
    }

    @Override
    public User saveOrUpdate(User user) {
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        if (user.getId() > 0) {
            updateNotNull(user);
        } else {//新建
            saveNotNull(user);
        }
        return user;
    }

}