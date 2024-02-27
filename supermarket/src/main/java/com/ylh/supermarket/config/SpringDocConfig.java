package com.ylh.supermarket.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("软帝云音乐后端接口文档")
                        .description("本文档主要涵盖：音乐资源、专辑、歌手、歌单、用户与管理员账户管理等功能接口API说明")
                        .version("v1.0.0")
                        .license(new License().name("开源文档协议"))
                        .contact(new Contact().name("ylh").url("www.baidu.com").email("****@qq.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("详细文档请看 Issues Documentation")
                        .url("http://www.softeem.com"));
    }
}
