package com.mwb.web.mapper;

import com.mwb.web.model.PraiseInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface PraiseMapper extends tk.mybatis.mapper.common.Mapper<PraiseInfo> {

    @Select("select id from praise_info where object_id = #{objectId} and user_id = #{userId} and type =#{type}")
    Long selectIdByUk(@Param("objectId") long objectId, @Param("userId") long userId, @Param("type") int type);
}
