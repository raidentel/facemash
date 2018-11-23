package com.facemash.cat.feign;

import com.facemash.cat.config.LatelierApiConfig;
import com.facemash.cat.feign.model.CatApiModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cat",
        configuration = LatelierApiConfig.class,
        fallbackFactory = CatFallbackFactory.class,
        url = "${legacy.latelier.url}")
public interface CatClient {

    @RequestMapping(value="/data/cats.json",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CatApiModel getCats();

}
