package com.mwb.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Mapper
public interface PetPicture1Mapper {


    @Insert("insert ignore into pet_picture2 (title, image) values (#{title}, #{image})")
    void insert(@Param("title") String title, @Param("image") String image);


}
