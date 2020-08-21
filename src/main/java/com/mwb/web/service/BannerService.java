package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.BannerInfo;
import com.mwb.web.model.common.PageQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface BannerService extends BaseService<BannerInfo> {

    List<BannerInfo> searchForHome(int size);

    PageInfo<BannerInfo> search(PageQuery query);

    BannerInfo saveOrUpdate(BannerInfo bannerInfo);


}