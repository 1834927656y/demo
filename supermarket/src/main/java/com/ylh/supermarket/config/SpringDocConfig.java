package com.ylh.supermarket.config;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylh.supermarket.entity.Money;
import com.ylh.supermarket.entity.Putstorage;
import com.ylh.supermarket.mapper.MoneyMapper;
import com.ylh.supermarket.service.PutstorageService;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("超市管理系统后端接口文档")
                        .description("本文档主要涵盖：商品，用户与管理员账户管理等功能接口API说明")
                        .version("v1.0.0")
                        .license(new License().name("开源文档协议"))
                        .contact(new Contact().name("ylh").url("www.baidu.com").email("****@qq.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("详细文档请看 Issues Documentation")
                        .url("http://www.softeem.com"));
    }

//    @Service
//    public class MoneyServiceImpl extends ServiceImpl<MoneyMapper, Money> implements MoneyService {
//        @Autowired
//        private PutstorageService putstorageService;
//
//        @Autowired
//        private Putstorage putstorage; // 将putstorage字段标记为@Autowired
//
//        // add 方法等其他代码保持不变
//    }

}
