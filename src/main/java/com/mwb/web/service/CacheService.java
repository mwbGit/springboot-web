package com.mwb.web.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mwb.web.model.common.AccessLimit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@Service
public class CacheService {
    private static Cache<String, AccessLimit> URI_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    public AccessLimit get(String ip, String uri) {
        return URI_CACHE.getIfPresent(ip + uri);
    }

    public void put(String ip, String uri) {
        AccessLimit accessLimit = URI_CACHE.getIfPresent(ip + uri);
        if (accessLimit == null) {
            accessLimit = new AccessLimit(ip, uri, new ArrayList<>(1));
        }
        accessLimit.getTimes().add(0, System.currentTimeMillis());
        URI_CACHE.put(ip + uri, accessLimit);
    }

}
