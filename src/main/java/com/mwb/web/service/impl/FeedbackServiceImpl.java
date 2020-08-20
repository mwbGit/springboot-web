package com.mwb.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.FeedbackMapper;
import com.mwb.web.model.FeedbackInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/18
 */
@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackInfo> implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public PageInfo<FeedbackInfo> search(PageQuery query) {
        return new PageInfo<>(pageSearch(query, FeedbackInfo.class));
    }
}
