package com.bank.transfer.config;

import org.junit.jupiter.api.Test;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SwaggerConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void bankApiBeanShouldExist() {
        GroupedOpenApi bean = applicationContext.getBean(GroupedOpenApi.class);
        assertThat(bean).isNotNull();
        assertThat(bean.getGroup()).isEqualTo("transfer-app");
    }
}