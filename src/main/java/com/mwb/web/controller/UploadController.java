package com.mwb.web.controller;

import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    String string = "https://imgs-xwz.coohua.com/20200819/1d38b3875aeeeb1a06ad9a1f8e2a522f.jpg";

    @Autowired
    private OssService ossService;

    @WebLogin(option = WebLogin.Option.MUST, valid = true, accessSeconds = 5, accessMax = 1)
    @RequestMapping("/image")
    public ApiResult image(@RequestParam(value = "file", required = false) MultipartFile file) {
        String url = ossService.uploadImage(file);
        return ApiResult.success(url);
    }
}
