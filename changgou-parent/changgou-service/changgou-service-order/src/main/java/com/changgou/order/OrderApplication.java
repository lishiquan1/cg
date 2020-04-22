package com.changgou.order;

import com.changgou.common.config.FeignInterceptor;
import com.changgou.common.config.SwaggerConfig;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/18 1:59 下午
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.changgou.goods.feign"})
@MapperScan(basePackages = {"com.changgou.order.dao"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
    }

    /**
     * 将Feign调用拦截器注入到容器中, 调用Feign时传递令牌给下个微服务
     * @return FeignInterceptor
     */
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    /**
     * 生成swagger文档
     * @return SwaggerConfig
     */
    @Bean
    public SwaggerConfig swaggerConfig(){
        return new SwaggerConfig("Order", "1.0.0-SNAPSHOT");
    }
}
