package com.facemash.cat.feign;

import com.facemash.cat.feign.model.CatApiModel;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"squid:S3457","squid:S1604"})
class CatFallbackFactory implements FallbackFactory<CatClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatFallbackFactory.class);


    @Override
    public CatClient create(Throwable cause) {
        return new CatClient() {

            @Override
            public CatApiModel getCats() {
                LOGGER.warn("fallback calling cat latelier web service reason was: "+ cause.getMessage());
                return null;
            }
        };
    }
}
