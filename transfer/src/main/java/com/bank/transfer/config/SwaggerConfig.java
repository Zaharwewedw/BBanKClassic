package com.bank.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //Создаем бин Docket(класс, кот.представляет конфигурацию для Swagger
    //И настраиваем где будет идти документация(в настройках указал, что везде)
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bank.transfer.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    //ApiInfo - вспомогательный класс, который содержит инфу, кот. будет отображаться в Сваггере
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Documentation's Bank 20000")
                .description("Bank 20000 API")
                .version("1.0.0")
                .build();
    }
}