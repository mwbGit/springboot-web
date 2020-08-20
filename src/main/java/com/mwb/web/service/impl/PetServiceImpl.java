package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.PetMapper;
import com.mwb.web.model.PetInfo;
import com.mwb.web.model.query.PetQuery;
import com.mwb.web.service.PetCharacterMappingService;
import com.mwb.web.service.PetService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("petService")
public class PetServiceImpl extends BaseServiceImpl<PetInfo> implements PetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetCharacterMappingService petCharacterMappingService;

    @Override
    public PageInfo<PetInfo> search(PetQuery query) {
        //分页
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(PetInfo.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        if (query.getType() > 0) {
            List<Long> petIds = petCharacterMappingService.getPetIdsByCharacterId(query.getType());
            if (CollectionUtils.isEmpty(petIds)) {
                return new PageInfo<>(Collections.emptyList());
            }
            criteria.andIn("id", petIds);
        }

        //排序
        example.orderBy("level").desc();
        List<PetInfo> users = selectByExample(example);
        return new PageInfo<>(users);
    }

    @Override
    public PetInfo saveOrUpdate(PetInfo petInfo) {
        petInfo.setAddTime(new Date());
        petInfo.setUpdateTime(new Date());
        if (petInfo.getId() > 0) {
            updateNotNull(petInfo);
        } else {//新建
            saveNotNull(petInfo);
        }
        return petInfo;
    }

}