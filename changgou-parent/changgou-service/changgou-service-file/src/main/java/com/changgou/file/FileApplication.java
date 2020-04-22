package com.changgou.file;

import com.changgou.common.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Demo File微服务启动类
 *
 * @author lishiquan
 * @date 2020/4/2 12:16 下午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

    /**
     * 生成swagger文档
     * @return SwaggerConfig
     */
    @Bean
    public SwaggerConfig swaggerConfig() {
        return new SwaggerConfig("File", "1.0.0-SNAPSHOT");
    }
}
