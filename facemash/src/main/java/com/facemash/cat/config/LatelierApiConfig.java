package com.facemash.cat.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LatelierApiConfig {

    @Value("${legacy.latelier.connectTimeOutMillis}")
    private int connectTimeOutMillis;
    @Value("${legacy.latelier.readTimeOutMillis}")
    private int readTimeOutMillis;


    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
}
