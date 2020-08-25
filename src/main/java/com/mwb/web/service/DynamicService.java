package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.query.DynamicQuery;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface DynamicService extends BaseService<DynamicInfo> {

    PageInfo<DynamicInfo> search(DynamicQuery userQuery, long userId);

    DynamicInfo saveOrUpdate(DynamicInfo articleInfo);

    void updatePraiseNum(long id, boolean addUp);

    void updateCommentNum(long id);

    void audit(long id, int status, String reason);
}