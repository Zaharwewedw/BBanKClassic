package com.bank.publicinfo.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicInfoApi() {
        String[] paths = {"/api/public-info/"};
        String[] packagesToScan = {"com.bank.publicinfo.controller.bankDetailsController"
                , "com.bank.publicinfo.controller.branchController"};
        return GroupedOpenApi.builder().pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .group("public-info-app")
                .build();
    }
}
