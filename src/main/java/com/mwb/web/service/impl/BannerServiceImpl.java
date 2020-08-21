package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.BannerMapper;
import com.mwb.web.model.BannerInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        //分页
        PageHelper.startPage(1, size);
        Example example = new Example(BannerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        //排序
        example.orderBy("level").desc();
        return selectByExample(example);
    }

    @Override
    public PageInfo<BannerInfo> search(PageQuery query) {
        return new PageInfo<>(pageSearch(query, BannerInfo.class));
    }

    @Override
    public BannerInfo saveOrUpdate(BannerInfo articleInfo) {
        articleInfo.setUpdateTime(new Date());
        if (articleInfo.getId() > 0) {
            updateNotNull(articleInfo);
        } else {//新建
            articleInfo.setAddTime(new Date());
            saveNotNull(articleInfo);
        }
        return articleInfo;
    }

}