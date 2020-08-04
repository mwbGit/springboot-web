package com.mwb.web.service.impl;

import com.mwb.web.mapper.LoginUserMapper;
import com.mwb.web.model.LoginUser;
import com.mwb.web.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Service("loginUserService")
public class LoginUserServiceImpl extends BaseServiceImpl<LoginUser> implements LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public LoginUser getByName(String name) {
        return loginUserMapper.selectByName(name);
    }
}
