package com.mwb.web.service;

import com.mwb.web.model.PetCharacterMapping;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */
public interface PetCharacterMappingService extends BaseService<PetCharacterMapping> {

    List<Long> getPetIdsByCharacterId(int characterId);

    List<Long> getPetTypeIdsByPetId(long petId);

    void deleteAndAdd(long petId, String[] types);
}