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


    @Select("select count(*) from login_user where name = #{name} and password =#{password}")
    int countByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
