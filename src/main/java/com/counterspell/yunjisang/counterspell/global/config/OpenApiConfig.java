package com.counterspell.yunjisang.counterspell.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.api-docs.version}") String openApiVersion) {
        Info info = new Info()
                .title("Counter Spell API")
                .version(openApiVersion)
                .description("Counter Spell TEAM 핫식스공장털이범")
                .termsOfService("TEAM 핫식스공장털이범");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
