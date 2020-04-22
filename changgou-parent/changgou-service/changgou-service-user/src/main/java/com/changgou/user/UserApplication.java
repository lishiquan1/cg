package com.changgou.user;

import com.changgou.common.config.SwaggerConfig;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Demo 用户微服务启动类
 *
 * @author lishiquan
 * @date 2020/4/14 4:58 下午
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.changgou.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
    }

    /**
     * 生成swagger文档
     * @return SwaggerConfig
     */
    @Bean
    public SwaggerConfig swaggerConfig(){
        return new SwaggerConfig("User", "1.0.0-SNAPSHOT");
    }
}
