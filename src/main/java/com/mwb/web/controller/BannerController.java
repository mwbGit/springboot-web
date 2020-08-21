package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.BannerInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/list")
    public ApiResult list() {
        List<BannerInfo> banners = bannerService.searchForHome(5);
        return ApiResult.success(banners);
    }

    /**
     * admin接口
     *
     * @param user
     * @return
     */
    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/save")
    public ApiResult saveOrUpdate(BannerInfo user) {
        return ApiResult.success(bannerService.saveOrUpdate(user));
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/online")
    public ApiResult online(@RequestParam("id") long id, @RequestParam("status") int status) {
        BannerInfo bannerInfo = bannerService.selectByKey(id);
        if (bannerInfo != null && bannerInfo.getStatus() != status) {
            bannerInfo.setStatus(status);
            bannerService.saveOrUpdate(bannerInfo);
        }
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/search")
    public ApiResult search(PageQuery pageQuery) {
        PageInfo<BannerInfo> banners = bannerService.search(pageQuery);
        return ApiResult.success(banners.getList(), banners.getTotal(), banners.getPages());
    }

}