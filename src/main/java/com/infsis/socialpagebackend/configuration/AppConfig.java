package com.infsis.socialpagebackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        //HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //factory.setReadTimeout(5000);
        //factory.setConnectTimeout(5000);

        return new RestTemplate();
    }
}