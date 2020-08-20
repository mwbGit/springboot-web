package com.mwb.web.service.impl;

import com.mwb.web.mapper.PetCharacterMappingMapper;
import com.mwb.web.model.PetCharacterMapping;
import com.mwb.web.service.PetCharacterMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
