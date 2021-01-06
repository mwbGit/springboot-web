package com.mwb.web.service;

import com.mwb.web.model.ActivityCode;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/12/3
 */
public interface ActivityCodeService extends BaseService<ActivityCode> {

    ActivityCode getByCode(String code);
}
