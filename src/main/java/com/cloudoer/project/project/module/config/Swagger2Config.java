package com.cloudoer.project.project.module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/20
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为controller包路径
                .apis(RequestHandlerSelectors.basePackage("com.cloudoer.project.project.module.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot使用 Swagger2 构建RestFul API")
                //创建人
                .contact(new Contact("小川", "http://localhost:8181/swagger-ui.html", "1598078574@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("跑圈模块接口文档")
                .build();
    }
}
