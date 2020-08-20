package com.mwb.web.mapper;

import com.mwb.web.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface UserInfoMapper extends tk.mybatis.mapper.common.Mapper<UserInfo> {

    @Select("select * from user_info where name = #{name}")
    UserInfo selectByName(@Param("name") String name);
}
