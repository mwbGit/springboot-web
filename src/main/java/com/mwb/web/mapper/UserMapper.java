package com.mwb.web.mapper;

import com.mwb.web.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {


    @Select("select * from user")
    List<User> selectAll();
}
