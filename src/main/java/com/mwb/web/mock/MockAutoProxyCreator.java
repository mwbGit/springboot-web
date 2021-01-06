package com.mwb.web.mock;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
@Component
public class MockAutoProxyCreator extends AbstractAutoProxyCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockAutoProxyCreator.class);
    private static final Set<String> PROXYED_SET = new HashSet<>(1000);
    private MethodInterceptor methodInterceptor = new MockMethodInterceptor();

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        return new Object[]{methodInterceptor};
    }

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        try {
            synchronized (PROXYED_SET) {
                if (PROXYED_SET.contains(beanName)) {
                    return bean;
                }
                Method[] methods = bean.getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Mock.class)) {
                        if (!AopUtils.isAopProxy(bean)) {
                            bean = super.wrapIfNecessary(bean, beanName, cacheKey);
                        }
                        PROXYED_SET.add(beanName);
                        break;
                    }
                }
                return bean;
            }
        } catch (Exception e) {
            LOGGER.error("MockAutoProxyCreator.wrapIfNecessary error", e);
            return bean;
        }
    }

}
