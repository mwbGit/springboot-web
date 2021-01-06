package com.mwb.web.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
//@Component
public class MockConfigContext implements InitializingBean, DisposableBean, ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockConfigContext.class);
    private static ScheduledExecutorService scheduled;
    private static ApplicationContext instance;

    public MockConfigContext() {

    }

    /**
     * 是否启用mock
     */
    private static volatile boolean mock = true;

    public static boolean isMock() {
        return mock;
    }

    /**
     * key:类名+方法名+参数
     * val:{
     * key:请求参数json
     * val:mock返回值json
     * }
     **/
    private static volatile Map<String, String> CONFIG_CACHE = new HashMap<>();

    public static String get(String classMethodName) {
        return CONFIG_CACHE.get(classMethodName);
    }


    @Override
    public void afterPropertiesSet() {
        if (!mock) {
            return;
        }
        scheduled = Executors.newSingleThreadScheduledExecutor();
        scheduled.scheduleAtFixedRate(() -> {
            Map<String, String> map = null;
            try {
               List<MockData> list = getInstance().getBean(MockConfigApi.class).getOnlineConfigs();
               if (list != null && list.size() > 0 ) {
                   map = new HashMap<>(list.size());
                   for (MockData mockData : list) {
                       map.put(mockData.getOnlyKey(), mockData.getResult());
                   }
               }
            } catch (Exception e) {
                LOGGER.error("MockConfigApi.load data err", e);
            }
            CONFIG_CACHE = map == null ? Collections.emptyMap() : map;
        }, 0, 10, TimeUnit.SECONDS);

    }

    @Override
    public void destroy() {
        if (scheduled != null) {
            scheduled.shutdownNow();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        instance = applicationContext;
    }

    private static ApplicationContext getInstance() {
        return instance;
    }

    public static boolean notReturnType(Class<?> returnType) {
        return returnType == null || returnType == Void.TYPE || returnType == Void.class;
    }
}
