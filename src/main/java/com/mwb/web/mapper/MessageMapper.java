package com.mwb.web.mapper;

import com.mwb.web.model.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface MessageMapper extends tk.mybatis.mapper.common.Mapper<MessageInfo> {

    @Update("update message_info set status =1 where user_id = #{userId} and status =0")
    int updateStatus(@Param("userId") long userId);

    @Select("select count(*) from message_info where user_id = #{userId} and status =0")
    int countStatus(@Param("userId") long userId);

    @Insert("insert into geek_second_chat (uid_tail, type, position, add_time) values(#{uidTail}, #{type}, #{position}, now())")
    int insertCaht(@Param("uidTail") int uidTail, @Param("type") int type,@Param("position") int position);
}
