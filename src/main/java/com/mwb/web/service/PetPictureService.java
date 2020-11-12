package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.PetPicture;
import com.mwb.web.model.query.PetPictureQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */
public interface PetPictureService extends BaseService<PetPicture> {

    PageInfo<PetPicture> search(PetPictureQuery query);

    PetPicture saveOrUpdate(PetPicture petPicture);

    List<String> getPicturesByPetId(long petId);
}