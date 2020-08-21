package com.mwb.web.service;

import com.mwb.web.model.PetCharacter;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */
public interface PetCharacterService extends BaseService<PetCharacter> {

    List<PetCharacter> getAll();

    PetCharacter saveOrUpdate(PetCharacter petCharacter);

    List<String> getCharacterNames(long petId);

    List<PetCharacter> search(Long petId);
}