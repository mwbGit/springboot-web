package com.mwb.web.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mwb.web.model.ActivityCode;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.service.ActivityCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/3
 */
@Slf4j
@RestController
@RequestMapping("/activity/")
public class ActivityCodeController {

    @Autowired
    private ActivityCodeService activityCodeService;


    @RequestMapping("/watermark")
    public ModelAndView watermark(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "url", required = false) String url) {
        ModelAndView modelAndView = new ModelAndView("/h5/watermark");
        modelAndView.addObject("code", code);
        modelAndView.addObject("url", url);
        return modelAndView;
    }

    @RequestMapping("/random")
    public ApiResult random() {
        ActivityCode activityCode = new ActivityCode();
        activityCode.setCode(getlinkNo());
        activityCode.setNote("random");
        activityCode.setTimes(20);
        activityCodeService.saveNotNull(activityCode);
        log.info("ActivityCodeController.random ok activityCode={}", activityCode);
        return ApiResult.success(activityCode.getCode());
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("url") String url) {
        try {
            url = URLDecoder.decode(url, "utf-8");
//            url = AESUtil.decrypt(url.replaceAll(" ", "+"));
            if (StringUtils.isBlank(url)) {
                return;
            }
            String name = System.currentTimeMillis() + ".mp4";
            log.info("ActivityCodeController.download fileName={}, url={}", name, url);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", name));
            response.setCharacterEncoding("UTF-8");

            HttpUtil.download(url, response.getOutputStream(), true);
        } catch (IOException e) {
            log.error("ActivityCodeController.download err url={}", url, e);
        }

    }

    @RequestMapping("/extract")
    public ApiResult extract(@RequestParam("code") String code, @RequestParam("url") String url) {
        if (StringUtils.isBlank(code)) {
            return ApiResult.failed("请输入提取码");
        }
        url = extractUrl(url);
        if (StringUtils.isBlank(url)) {
            return ApiResult.failed("请输入有效视频地址");
        }

        ActivityCode activityCode = activityCodeService.getByCode(code);
        if (activityCode == null) {
            return ApiResult.failed("提取码错误");
        } else if (activityCode.getUseTimes() >= activityCode.getTimes()) {
            return ApiResult.failed("提取码次数已用完");
        }
        try {
            ApiResult rsp = getVideoUrl(url);
            if (rsp.getCode() != 0) {
                return rsp;
            }

            JSONObject json = (JSONObject) rsp.getData();
            if (json != null) {
                String playAddr = json.getString("playAddr");
                if (StringUtils.isNotBlank(playAddr)) {
                    activityCode.setUseTimes(activityCode.getUseTimes() + 1);
                    int count = activityCodeService.updateNotNull(activityCode);
                    log.info("ActivityCodeController.extract ok code={}, videoUrl={}", code, url);

                    if (count > 0) {
                        Map<String, Object> map = new HashMap<>(2);
                        map.put("surplusTimes", activityCode.getTimes() - activityCode.getUseTimes());
                        map.put("url", URLEncoder.encode(playAddr, "utf-8"));
                        return ApiResult.success(map);
                    }
                }
            }
        } catch (Exception e) {
            log.error("ActivityCodeController.extract err code={}, videoUrl={}", code, url, e);
        }
        return ApiResult.failed("系统异常，请稍后再试");
    }

    private static final String URL = "http://api.mengweibo.com/api/video/parse/v2?source=mwb&url=";

    private static ApiResult getVideoUrl(String videoUrl) {
        try {
            String json = HttpUtil.get(URL + videoUrl, 2000);
            if (StringUtils.isNotBlank(json)) {
                ApiResult result = JSON.parseObject(json, ApiResult.class);
                if (result != null && result.getData() != null) {
                    return result;
                }
            }
        } catch (Exception e) {
            log.error("ActivityCodeController.getVideoUrl err videoUrl={}", videoUrl, e);
        }
        return ApiResult.failed("系统异常，请稍后再试");
    }

    public static String extractUrl(String urlStr) {
        if (StringUtils.isBlank(urlStr) || !urlStr.contains("http")) {
            return StringUtils.EMPTY;
        }

        String newUrlStr = urlStr.substring(urlStr.substring(0, urlStr.indexOf("http")).length());

        for (String string : newUrlStr.split(" ")) {
            if (string.startsWith("http")) {
                return string;
            }
        }
        return StringUtils.EMPTY;
    }

    private static String getlinkNo() {
        String linkNo = "";
        // 用字符数组的方式随机
        String model = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 36)];
            // 保证六位随机数之间没有重复的
            if (linkNo.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            linkNo = linkNo + c;
        }
        return linkNo;
    }

    public static void main(String[] args) {
        System.out.println(getlinkNo());
    }
}
