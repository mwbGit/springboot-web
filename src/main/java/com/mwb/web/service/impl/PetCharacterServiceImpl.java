package com.mwb.web.service.impl;

import com.mwb.web.mapper.PetCharacterMapper;
import com.mwb.web.model.PetCharacter;
import com.mwb.web.service.PetCharacterMappingService;
import com.mwb.web.service.PetCharacterService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */
@Service("petCharacterService")
public class PetCharacterServiceImpl extends BaseServiceImpl<PetCharacter> implements PetCharacterService {

    @Autowired
    private PetCharacterMapper petCharacterMapper;

    @Autowired
    private PetCharacterMappingService characterMappingService;

    @Override
    public List<PetCharacter> getAll() {
        return selectAll();
    }

    @Override
    public PetCharacter saveOrUpdate(PetCharacter petCharacter) {
        petCharacter.setAddTime(new Date());
        petCharacter.setUpdateTime(new Date());
        if (petCharacter.getId() > 0) {
            updateNotNull(petCharacter);
        } else {//新建
            saveNotNull(petCharacter);
        }
        return petCharacter;
    }

    @Override
    public List<String> getCharacterNames(long petId) {
        List<Long> typeIds = characterMappingService.getPetTypeIdsByPetId(petId);
        if (CollectionUtils.isEmpty(typeIds)) {
            return Collections.emptyList();
        }
        Example example = new Example(PetCharacter.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", typeIds);
        List<PetCharacter> petCharacters = selectByExample(example);
        return CollectionUtils.isEmpty(petCharacters) ? Collections.emptyList() : petCharacters.stream().map(PetCharacter::getName).distinct().collect(Collectors.toList());
    }
}
