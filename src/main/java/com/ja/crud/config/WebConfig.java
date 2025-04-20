package com.ja.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebConfig implements WebMvcConfigurer   {

    public OpenAPI apiDoc(){
        return new OpenAPI().info(new Info()
                .title("user api")
                .description("...")
                .version("0.1")
        );
    }
}
