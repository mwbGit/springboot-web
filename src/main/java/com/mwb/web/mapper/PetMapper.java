package com.mwb.web.mapper;

import com.mwb.web.model.PetInfo;
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
public interface PetMapper extends tk.mybatis.mapper.common.Mapper<PetInfo> {

    @Select("select id, name, image from pet_info order by level desc limit #{offset}, #{limit}")
    List<PetInfo> searchSimple(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from pet_info ")
    int countAll();
}
