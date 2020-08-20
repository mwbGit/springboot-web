package com.mwb.web.service;

import com.mwb.web.model.CommentInfo;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface CommentService extends BaseService<CommentInfo> {

    List<CommentInfo> search(List<Long> dynamicIds);

    CommentInfo saveOrUpdate(CommentInfo bannerInfo);


}