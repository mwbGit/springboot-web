package com.mwb.web.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
@Component
public class MockWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private MockWebInterceptor mockInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (MockConfigContext.isMock()) {
            registry.addInterceptor(mockInterceptor).addPathPatterns("/**").order(Ordered.LOWEST_PRECEDENCE);
        }
    }
}

