package com.mwb.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.PetPictureMapper;
import com.mwb.web.model.PetPicture;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.PetPictureService;
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
@Service("petPictureService")
public class PetPictureServiceImpl extends BaseServiceImpl<PetPicture> implements PetPictureService {

    @Autowired
    private PetPictureMapper petPictureMapper;

    @Override
    public PageInfo<PetPicture> search(PageQuery query) {
        return new PageInfo<>(pageSearch(query, PetPicture.class));
    }

    @Override
    public PetPicture saveOrUpdate(PetPicture petCharacter) {
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
    public List<String> getPicturesByPetId(long petId) {
        Example example = new Example(PetPicture.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("petId", petId);
        example.orderBy("level").desc();
        example.orderBy("id").desc();
        List<PetPicture> petCharacters = selectByExample(example);
        return CollectionUtils.isEmpty(petCharacters) ? Collections.emptyList() : petCharacters.stream().map(PetPicture::getImage).collect(Collectors.toList());
    }
}
