package com.bank.transfer.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi bankApi() {
        String[] paths = { "/api/transfer/" };
        String[] packagesToScan = { "com.bank.transfer.controller" };
        return GroupedOpenApi.builder().pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .group("transfer-app")
                .pathsToMatch(paths)
                .build();
    }
}