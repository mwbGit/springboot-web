package com.mwb.web.mapper;

import com.mwb.web.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Mapper
public interface ArticleMapper extends tk.mybatis.mapper.common.Mapper<ArticleInfo> {


    @Select("select id, title, praise_num as praiseNum from article_info order by praise_num desc limit #{size}")
    List<ArticleInfo> selectOrderByNum(@Param("size") int size);
}
