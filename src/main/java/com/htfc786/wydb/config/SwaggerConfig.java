package com.htfc786.wydb.config;

// 配置原文链接
// https://blog.csdn.net/xhmico/article/details/131701790

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2    //开启 Swagger2
@EnableKnife4j     //开启 knife4j，可以不写
@EnableAutoConfiguration
@ConditionalOnProperty(name = "swagger.enable", matchIfMissing = true)
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        // 创建一个 swagger 的 bean 实例
        return new Docket(DocumentationType.SWAGGER_2)
                // 配置基本信息
                .apiInfo(apiInfo())

                // 配置接口信息，设置扫描接口
                .select()

                /*
                 * RequestHandlerSelectors
                 *      .any() // 扫描全部的接口，默认
                 *      .none() // 全部不扫描
                 *      .basePackage("com.mike.server") // 扫描指定包下的接口，最为常用
                 *      .withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                 *      .withMethodAnnotation(PostMapping.class) // 扫描带有只当注解的方法接口
                 */

                // 此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.htfc786.wydb"))
                // 加了 RestController 注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // 加了 ApiOperation 注解的方法，才生成接口文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  // @ApiOperation：swagger 常用注解，用户标注方法描述

                /*
                 * PathSelectors
                 *      .any() // 满足条件的路径，该断言总为true
                 *      .none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                 *      .ant("/user/**") // 满足字符串表达式路径
                 *      .regex("") // 符合正则的路径
                 */

                .paths(PathSelectors.any())

                .build();
    }

    /**
     * 基本信息设置
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("wydb-接口文档")
                // 描述
                .description("wydb-接口文档")
                // 服务条款链接
                .termsOfServiceUrl("https://github.com/htfc786/wydb")
                // 许可证
                .license("wydb")
                // 许可证链接
                .licenseUrl("https://github.com/htfc786/wydb")
                // 联系我
                .contact(new Contact(
                        "htfc786",
                        "https://github.com/htfc786/wydb",
                        "email@163.com"))
                // 版本
                .version("1.0")
                .build();
    }
}

