package com.mwb.web.service.impl;

import com.mwb.web.model.ActivityCode;
import com.mwb.web.service.ActivityCodeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/12/3
 */
@Service
public class ActivityCodeServiceImpl extends BaseServiceImpl<ActivityCode> implements ActivityCodeService {


    @Override
    public ActivityCode getByCode(String code) {
        Example example = new Example(ActivityCode.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        return selectOneByExample(example);
    }
}
