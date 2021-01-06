package com.mwb.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@EnableScheduling
@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的
//首先要将spring boot自带的DataSourceAutoConfiguration禁掉，因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源。在@SpringBootApplication注解中添加exclude属性即可：
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootWebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }
}