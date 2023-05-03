package com.microservices.microservice1.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempConf {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
