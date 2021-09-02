package com.mwb.web.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2021/6/3
 */
@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Value(value = "${spring.elasticsearch.rest.uris}")
    String esNodes;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(esNodes.split(","))
                .withSocketTimeout(30 * 1000)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}

