package com.changgou.websearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/12 9:16 下午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.changgou.search.feign")
public class WebSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSearchApplication.class, args);
    }
}
