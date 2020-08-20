package com.mwb.web.service.impl;

import com.mwb.web.mapper.PraiseMapper;
import com.mwb.web.model.PraiseInfo;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.PraiseService;
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
 * @create 2020/7/31
 */

@Service("praiseInfoService")
public class PraiseServiceImpl extends BaseServiceImpl<PraiseInfo> implements PraiseService {

    @Autowired
    private PraiseMapper praiseMapper;

    @Autowired
    private DynamicService dynamicService;

    @Override
    public List<Long> search(List<Long> objectIds, long userId, int type) {
        if (CollectionUtils.isEmpty(objectIds)) {
            return Collections.emptyList();
        }
        Example example = new Example(PraiseInfo.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("objectId", objectIds);
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("userId", userId);
        //排序
        example.orderBy("id").desc();
        List<PraiseInfo> list = selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().map(PraiseInfo::getObjectId).distinct().collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean savePraiseInfo(PraiseInfo praiseInfo) {
        Long id = praiseMapper.selectIdByUk(praiseInfo.getObjectId(), praiseInfo.getUserId(), praiseInfo.getType());
        if (id != null) {
            if (praiseInfo.getType() == 1) {
                return false;
            }
            delete(id);
        } else {
            praiseInfo.setAddTime(new Date());
            praiseInfo.setUpdateTime(new Date());
            saveNotNull(praiseInfo);
        }
        dynamicService.updatePraiseNum(praiseInfo.getObjectId(), id == null);
        return true;
    }

}