package com.changgou.common.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Demo Swagger配置类
 *
 * @author lishiquan
 * @date 2020/4/4 3:03 下午
 */
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 微服务名称
     */
    private final String serverName;
    /**
     * 版本号
     */
    private final String version;

    public SwaggerConfig(String serverName, String version) {
        this.serverName = serverName;
        this.version = version;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //apiInfo指定测试文档基本信息，这部分将在页面展示
                .apiInfo(apiInfo())
                .groupName(serverName)
                .select()
                //apis() 控制哪些接口暴露给swagger，
                // RequestHandlerSelectors.any() 所有都暴露
                // RequestHandlerSelectors.basePackage("com.info.*")  指定包位置
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 基本信息，页面展示
     */
    private ApiInfo apiInfo() {
        // 联系人实体类
        // Contact contact = new Contact("lishquan", null, "li_shiquan@outlook.com");
        return new ApiInfoBuilder()
                .title(serverName + "接口文档")
                .description("接口描述")
                // .contact(contact)
                // 版本号
                .version(version)
                .build();
    }
}