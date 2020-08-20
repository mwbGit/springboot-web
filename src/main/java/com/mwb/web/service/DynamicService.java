package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.common.PageQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface DynamicService extends BaseService<DynamicInfo> {

    PageInfo<DynamicInfo> search(PageQuery userQuery, long userId);

    List<DynamicInfo> getNewList(int pageSize);

    DynamicInfo saveOrUpdate(DynamicInfo articleInfo);

    void updatePraiseNum(long id, boolean addUp);
}