package com.changgou.gatewayweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/14 10:13 上午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

    /**
     * 创建用户唯一标识, 使用IP作为用户唯一标识, 来根据IP进行限流操作
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver userKeyResolver() {
        // 获取用户IP
        return exchange -> Mono.just((Objects.requireNonNull(exchange
                .getRequest()
                .getRemoteAddress()))
                        .getHostString());
    }

}
