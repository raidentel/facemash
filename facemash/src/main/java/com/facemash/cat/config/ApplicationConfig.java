package com.facemash.cat.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.facemash.cat.feign")
public class ApplicationConfig {

}
