package com.mwb.web.service.impl;

import com.mwb.web.mapper.PetCharacterMappingMapper;
import com.mwb.web.model.PetCharacterMapping;
import com.mwb.web.service.PetCharacterMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */
@Service("petCharacterMappingService")
public class PetCharacterServiceMappingImpl extends BaseServiceImpl<PetCharacterMapping> implements PetCharacterMappingService {

    @Autowired
    private PetCharacterMappingMapper petCharacterMappingMapper;

    @Override
    public List<Long> getPetIdsByCharacterId(int characterId) {
        return petCharacterMappingMapper.selectPetIdsByType(characterId);
    }

    @Override
    public List<Long> getPetTypeIdsByPetId(long petId) {
        return petCharacterMappingMapper.selectPetTypeIdsByPet(petId);
    }

    @Override
    public void deleteAndAdd(long petId, String[] types) {
        Date now = new Date();
        petCharacterMappingMapper.deleteByPetId(petId);
        for (String typeId : types) {
            PetCharacterMapping mapping = new PetCharacterMapping();
            mapping.setPetId(petId);
            mapping.setCharacterId(Long.parseLong(typeId));
            mapping.setAddTime(now);
            mapping.setUpdateTime(now);
            saveNotNull(mapping);
        }
    }
}
