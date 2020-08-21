package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.model.query.DynamicQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface DynamicService extends BaseService<DynamicInfo> {

    PageInfo<DynamicInfo> search(DynamicQuery userQuery, long userId);

    List<DynamicInfo> getNewList(PageQuery query);

    DynamicInfo saveOrUpdate(DynamicInfo articleInfo);

    void updatePraiseNum(long id, boolean addUp);

    void updateCommentNum(long id);

    void audit(long id, int status, boolean sendMsg);
}