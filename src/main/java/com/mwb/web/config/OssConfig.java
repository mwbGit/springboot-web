package com.mwb.web.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@Configuration
public class OssConfig {
    @Value("${oss.url:#{null}}")
    private String url;
    @Value("${oss.id: #{null}}")
    private String id;
    @Value("${oss.secret:#{null}}")
    private String secret;

    @Bean
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setMaxConnections(100);
        configuration.setConnectionTimeout(5000);
        configuration.setMaxErrorRetry(3);
        configuration.setSupportCname(true);
        configuration.setSocketTimeout(2000);
        return configuration;
    }

    @Bean
    public OSSClient ossClient(@Qualifier("clientConfiguration") ClientConfiguration clientConfiguration) {
        return new OSSClient(url, id, secret, clientConfiguration);
    }
}
