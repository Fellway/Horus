package com.example.horus.sqlinjection;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SqlmapClientConfig {

    public SqlMapClient getSqlMapClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(SqlMapClient.class))
                .logLevel(Logger.Level.FULL)
                .target(SqlMapClient.class, "http://sqlmap:8775/");
    }

}
