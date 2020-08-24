package com.mwb.web.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mwb.web.mapper.IpListMapper;
import com.mwb.web.model.IpList;
import com.mwb.web.model.common.AccessLimit;
import com.mwb.web.utils.WebConstant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@Service
public class AccessCacheService {
    /**
     * ip uri 访问频率
     */
    private static Cache<String, AccessLimit> URI_CACHE = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    /**
     * ip一天累计访问次数
     */
    private static Cache<String, Integer> IP_CACHE = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    private static Set<String> ipBlackSet = new HashSet<>(0);

    private static Set<String> ipWhiteSet = new HashSet<>(0);

    @Autowired
    private IpListMapper ipListMapper;

    @PostConstruct
    public void init() {
        List<IpList> ips = ipListMapper.selectAll();
        if (CollectionUtils.isNotEmpty(ips)) {
            for (IpList ipList : ips) {
                if (ipList.getType() == 0) {
                    ipBlackSet.add(ipList.getIp());
                } else {
                    ipWhiteSet.add(ipList.getIp());
                }
            }
        }
    }

    /**
     * ip+uri 查询访问限制
     *
     * @param ip
     * @param uri
     * @return
     */
    public AccessLimit get(String ip, String uri) {
        return URI_CACHE.getIfPresent(ip + uri);
    }


    /**
     * ip+uri 添加访问限制
     *
     * @param ip
     * @param uri
     * @return
     */
    public void put(String ip, String uri) {
        if (ipWhiteSet.contains(ip)) {
            return;
        }

        // 达到访问限制加入黑名单
        Integer count = IP_CACHE.getIfPresent(ip);
        count = count == null ? 1 : count + 1;
        if (count > WebConstant.ACCESS_TOTAL) {
            addBlackList(ip);
            return;
        } else {
            IP_CACHE.put(ip, count);
        }

        //uri 访问统计
        AccessLimit accessLimit = URI_CACHE.getIfPresent(ip + uri);
        if (accessLimit == null) {
            accessLimit = new AccessLimit(ip, uri, new ArrayList<>(1));
        }
        accessLimit.getTimes().add(0, System.currentTimeMillis());
        URI_CACHE.put(ip + uri, accessLimit);


    }

    /**
     * 是否在黑名单列表
     *
     * @param ip
     * @return
     */
    public boolean isBlackList(String ip) {
        return ipBlackSet.contains(ip);
    }

    /**
     * 添加至黑名单列表
     *
     * @param ip
     * @return
     */
    private void addBlackList(String ip) {
        if (!ipBlackSet.contains(ip)) {
            ipListMapper.insertSelective(new IpList(ip));
            ipBlackSet.add(ip);
        }
    }
}
