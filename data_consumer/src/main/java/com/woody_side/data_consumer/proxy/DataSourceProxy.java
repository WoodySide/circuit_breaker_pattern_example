package com.woody_side.data_consumer.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DataSourceProxy {

    private final RestTemplate restTemplate;
    private static final String GET_RESOURCE_URL = "http://localhost:8089/api/v1/resources/";

    public String getResource() {
        return restTemplate.getForObject(GET_RESOURCE_URL, String.class);
    }
}
