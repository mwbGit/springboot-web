package com.mwb.web.controller;

import com.mwb.web.model.BannerInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ApiResult search() {
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
    @RequestMapping("/saveOrUpdate")
    public ApiResult saveOrUpdate(BannerInfo user) {
        return ApiResult.success(bannerService.saveOrUpdate(user));
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/delete")
    public ApiResult delete(long id) {
        bannerService.delete(id);
        return ApiResult.success();
    }

}