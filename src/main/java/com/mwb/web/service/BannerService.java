package com.mwb.web.service;

import com.mwb.web.model.BannerInfo;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface BannerService extends BaseService<BannerInfo> {

    List<BannerInfo> searchForHome(int size);

    BannerInfo saveOrUpdate(BannerInfo bannerInfo);


}