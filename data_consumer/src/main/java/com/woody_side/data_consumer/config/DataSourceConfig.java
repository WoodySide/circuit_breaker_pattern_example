package com.woody_side.data_consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataSourceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
