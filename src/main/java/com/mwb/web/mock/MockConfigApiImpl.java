package com.mwb.web.mock;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
@Service
public class MockConfigApiImpl implements MockConfigApi {
    private static volatile List<MockData> CONFIG_CACHE = new ArrayList<>();

    static {
        String json1 = "{\"classMethodName\":\"com.mwb.web.controller.MockController.test()\",\"onlyKey\":\"com.mwb.web.controller.MockController.test()\",\"param\":\"\",\"result\":\"false\",\"status\":0}";
        String json2 = "{\"classMethodName\":\"com.mwb.web.controller.MockController.test1(int type)2\",\"onlyKey\":\"com.mwb.web.controller.MockController.test1()?2\",\"param\":\"2\",\"status\":0,\"result\":\"200\"}";
        String json3 = "{\"classMethodName\":\"com.mwb.web.controller.MockController.test2(int type, MockData mockData)\",\"onlyKey\":\"com.mwb.web.controller.MockController.test2(int type, MockData mockData)?{\\\"mockData\\\":{\\\"id\\\":111,\\\"onlyKey\\\":\\\"\\\"},\\\"type\\\":11}\",\"param\":\"{\\\"mockData\\\":{\\\"id\\\":111,\\\"onlyKey\\\":\\\"\\\"},\\\"type\\\":11}\",\"result\":\"100\"}";
        String json4 = "{\"classMethodName\":\"/mock/test3\",\"onlyKey\":\"/mock/test3?type=2\",\"param\":\"type=2\",\"result\":\"ok\"}";

        CONFIG_CACHE.add(JSONObject.parseObject(json1, MockData.class));
        CONFIG_CACHE.add(JSONObject.parseObject(json2, MockData.class));
        CONFIG_CACHE.add(JSONObject.parseObject(json3, MockData.class));
        CONFIG_CACHE.add(JSONObject.parseObject(json4, MockData.class));
    }

    @Override
    public void saveConfig(MockData mockData) {
        System.out.println("saveConfig");
        CONFIG_CACHE.add(mockData);
    }

    @Override
    public List<MockData> getAllConfigs() {
        System.out.println("getAllConfigs");
        return CONFIG_CACHE;
    }

    @Override
    public List<MockData> getOnlineConfigs() {
        System.out.println("getOnlineConfigs");
        return CONFIG_CACHE;
    }

    @Override
    public void updateStatus(String key, int status) {

    }
}
