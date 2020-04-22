package com.changgou.goods;

import com.changgou.common.config.FeignInterceptor;
import com.changgou.common.config.SwaggerConfig;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Demo Goods微服务启动类
 *
 * @author lishiquan
 * @date 2020/4/1 9:29 下午
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.changgou.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    /**
     * mybatis忽略数据库字段名称与实体类名称大小写
     * @return ConfigurationCustomizer
     */
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
        return new SwaggerConfig("Goods", "1.0.0-SNAPSHOT");
    }
}
