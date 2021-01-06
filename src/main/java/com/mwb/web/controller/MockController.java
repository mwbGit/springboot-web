package com.mwb.web.controller;

import com.mwb.web.mock.Mock;
import com.mwb.web.mock.MockConfigApi;
import com.mwb.web.mock.MockData;
import com.mwb.web.mock.MockMethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
@RestController
@RequestMapping("/mock")
public class MockController {

    @Value("${m.mock.package.names:#{null}}")
    private String packageNames;

    @Autowired
    private MockConfigApi mockConfigApi;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    private Set<String> getUris() {
        Map map = this.handlerMapping.getHandlerMethods();
        Set set = map.keySet();
        Set<String> uris = new HashSet<>(set.size());
        for (Object object : set) {
            RequestMappingInfo info = (RequestMappingInfo) object;
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            uris.addAll(patterns);
        }
        System.out.println(uris);
        return uris;
    }

    /**
     * web管理
     *
     * @return
     */
    @RequestMapping("/query/methods")
    public Map<String, Object> queryMethods() {
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", 0);
        Set<String> uris = getUris();
        if (StringUtils.isNotBlank(packageNames)) {
            uris.addAll(MockMethodUtils.getClassMethodNames(packageNames.split(",")));
            result.put("data", uris);
        }
        return result;
    }

    @RequestMapping("/search")
    public Map<String, Object> search() {
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", 0);
        result.put("data", mockConfigApi.getAllConfigs());
        return result;
    }

    @RequestMapping("/save")
    public Map<String, Object> save(@RequestParam String classMethodName, @RequestParam String param, @RequestParam String returnJson) {
        MockData mockData = new MockData();
        mockData.setParam(param);
        mockData.setClassMethodName(classMethodName);
        mockData.setResult(returnJson);
        mockConfigApi.saveConfig(mockData);
        Map<String, Object> result = new HashMap<>(1);
        result.put("code", 0);
        return result;
    }

    @RequestMapping("/status/update")
    public Map<String, Object> statusUpdate(@RequestParam String classMethodName, @RequestParam int status) {
        mockConfigApi.updateStatus(classMethodName, status);
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", 0);
        return result;
    }


    /**
     * 测试接口
     * 注解、web
     *
     * @return
     */
    @Mock
    @RequestMapping("/test")
    public Boolean test() {
        return true;
    }

    @Mock
    @RequestMapping("/test1")
    public Integer test1(int type) {
        //mock/test1?type=22
        return 10;
    }

    @Mock
    @RequestMapping("/test2")
    public Integer test2(int type, MockData mockData) {
        //mock/test2?type=11&id=111
        return 11;
    }

    @RequestMapping("/test3")
    public String test3(int type) {
        //mock/test3?type=2
        return "err";
    }
}
