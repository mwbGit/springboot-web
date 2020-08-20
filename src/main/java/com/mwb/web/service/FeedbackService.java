package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.FeedbackInfo;
import com.mwb.web.model.common.PageQuery;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface FeedbackService extends BaseService<FeedbackInfo> {

    PageInfo<FeedbackInfo> search(PageQuery pageQuery);

}