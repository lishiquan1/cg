package com.changgou.search;

import com.changgou.common.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Demo Search微服务启动类
 *
 * @author lishiquan
 * @date 2020/4/9 5:38 下午
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.changgou.goods.feign"})
@EnableElasticsearchRepositories(basePackages = {"com.changgou.search.dao"})
public class SearchApplication {
    public static void main(String[] args) {
        // 防止netty冲突, 而引发报错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchApplication.class, args);
    }

    /**
     * 生成swagger文档
     * @return SwaggerConfig
     */
    @Bean
    public SwaggerConfig swaggerConfig() {
        return new SwaggerConfig("Search", "1.0.0-SNAPSHOT");
    }
}
