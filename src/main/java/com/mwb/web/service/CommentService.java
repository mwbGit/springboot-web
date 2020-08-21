package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.model.query.CommentQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface CommentService extends BaseService<CommentInfo> {

    List<CommentInfo> searchById(List<Long> dynamicIds);

    PageInfo<CommentInfo> search(CommentQuery query);

    CommentInfo saveOrUpdate(CommentInfo bannerInfo);

    void audit(long id, int status, boolean sendMsg);


}