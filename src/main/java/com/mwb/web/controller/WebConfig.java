package com.mwb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private WebConfigurerAdapter adapter;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toTest").setViewName("/test");
        registry.addViewController("/toindex").setViewName("/index");
        registry.addViewController("/table").setViewName("/table");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adapter);
    }
}