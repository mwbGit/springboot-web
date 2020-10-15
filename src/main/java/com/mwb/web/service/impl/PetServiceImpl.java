package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.PetMapper;
import com.mwb.web.model.PetInfo;
import com.mwb.web.model.query.PetQuery;
import com.mwb.web.service.PetCharacterMappingService;
import com.mwb.web.service.PetService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    public PageInfo<PetInfo> simpleSearch(PetQuery query) {
        List<PetInfo> list = petMapper.searchSimple( (query.getPage() - 1) * query.getPageSize(), query.getPageSize());
        PageInfo<PetInfo> pageInfo = new PageInfo(list);
        pageInfo.setTotal(petMapper.countAll());
        return pageInfo;
    }

    @Override
    public PageInfo<PetInfo> search(PetQuery query) {
        //分页
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(PetInfo.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        if (query.getCharacterId() > 0) {
            List<Long> petIds = petCharacterMappingService.getPetIdsByCharacterId(query.getCharacterId());
            if (CollectionUtils.isEmpty(petIds)) {
                return new PageInfo<>(Collections.emptyList());
            }
            criteria.andIn("id", petIds);
        }
        if (StringUtils.isNotBlank(query.getName())) {
            criteria.andLike("name", "%" + query.getName() + "%");
        }
        if (query.getLevel() != null) {
            criteria.andEqualTo("level", query.getLevel());
        }
        if (query.getScore() != null) {
            criteria.andEqualTo("score", query.getScore());
        }
        criteria.andEqualTo("deleted", 0);
        //排序
        example.orderBy(query.getOrder()).desc();
        List<PetInfo> users = selectByExample(example);
        return new PageInfo<>(users);
    }

    @Override
    public PetInfo saveOrUpdate(PetInfo petInfo, String[] types) {
        Date now = new Date();
        petInfo.setUpdateTime(now);
        if (petInfo.getId() > 0) {
            updateNotNull(petInfo);
        } else {//新建
            petInfo.setAddTime(now);
            saveNotNull(petInfo);
        }

        petCharacterMappingService.deleteAndAdd(petInfo.getId(), types);
        return petInfo;
    }

}