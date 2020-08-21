package com.mwb.web.mapper;

import com.mwb.web.model.PetCharacterMapping;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
public interface PetCharacterMappingMapper extends tk.mybatis.mapper.common.Mapper<PetCharacterMapping> {

    @Select("select pet_id from pet_character_mapping where character_id = #{characterId}")
    List<Long> selectPetIdsByType(@Param("characterId") int characterId);

    @Select("select character_id from pet_character_mapping where pet_id = #{petId}")
    List<Long> selectPetTypeIdsByPet(@Param("petId") long petId);

    @Delete("delete from pet_character_mapping where pet_id = #{petId}")
    void deleteByPetId(@Param("petId") long petId);
}
