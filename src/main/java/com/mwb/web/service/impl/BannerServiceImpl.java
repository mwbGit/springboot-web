package com.mwb.web.service.impl;

import com.mwb.web.mapper.BannerMapper;
import com.mwb.web.model.BannerInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("bannerService")
public class BannerServiceImpl extends BaseServiceImpl<BannerInfo> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerInfo> searchForHome(int size) {
        return pageSearch(new PageQuery(size, "level"), BannerInfo.class);
    }

    @Override
    public BannerInfo saveOrUpdate(BannerInfo articleInfo) {
        articleInfo.setAddTime(new Date());
        articleInfo.setUpdateTime(new Date());
        if (articleInfo.getId() > 0) {
            updateNotNull(articleInfo);
        } else {//新建
            saveNotNull(articleInfo);
        }
        return articleInfo;
    }

}