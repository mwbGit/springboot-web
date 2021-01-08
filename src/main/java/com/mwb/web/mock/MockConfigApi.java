package com.mwb.web.mock;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
public interface MockConfigApi {

    /**
     * 保存配置
     * db/redis/内存
     */
    void saveConfig(MockData mockData);

    /**
     * 查询所有配置
     */
    List<MockData> getAllConfigs();

    /**
     * 查询生效的配置
     */
    List<MockData> getOnlineConfigs();

    /**
     * 上线、下线
     */
    void updateStatus(String key, int status);

}
