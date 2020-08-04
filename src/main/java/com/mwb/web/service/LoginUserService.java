package com.mwb.web.service;

import com.mwb.web.model.LoginUser;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface LoginUserService extends BaseService<LoginUser> {

    LoginUser getByName(String name);

}
