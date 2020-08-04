package com.mwb.web.mapper;

import com.mwb.web.model.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Mapper
public interface LoginUserMapper extends tk.mybatis.mapper.common.Mapper<LoginUser> {

    @Select("select * from login_user where name = #{name}")
    LoginUser selectByName(@Param("name") String name);
}
