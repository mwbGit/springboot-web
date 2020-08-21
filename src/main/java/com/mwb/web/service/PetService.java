package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.PetInfo;
import com.mwb.web.model.query.PetQuery;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface PetService extends BaseService<PetInfo> {

    PageInfo<PetInfo> search(PetQuery query);

    PetInfo saveOrUpdate(PetInfo petInfo, String[] types);


}