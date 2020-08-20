package com.mwb.web.mapper;

import com.mwb.web.model.DynamicInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface DynamicMapper extends tk.mybatis.mapper.common.Mapper<DynamicInfo> {


    @Select("select id, title, praise_num as praiseNum from article_info order by praise_num desc limit #{size}")
    List<DynamicInfo> selectOrderByNum(@Param("size") int size);
}
