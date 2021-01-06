package com.mwb.web.controller.common;

import cn.hutool.http.HttpUtil;
import com.mwb.web.model.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/4
 */
@Slf4j
@ControllerAdvice
public class WebExceptionController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult customException(Exception e) {
        log.error("ExceptionController.customException", e);
        return ApiResult.failed();
    }

    public static void main(String[] args) {
        String body = "deviceId=AB65BAD4-F3C7-417C-A61A-81E174B5094E&receiptData=nil&sign=f2ae62046f56c43da0bf99acb6c7d8c1&source=watermark&version1=Optional%28%221.1.1%22%29\n";
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", "aa");
        map.put("sign", "bbb");
        String ss = HttpUtil.post("http://localhost:8088/api/video/report1", body);
        System.out.println(ss);
    }

}

