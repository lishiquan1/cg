package com.changgou.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/26 11:14 下午
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients// (basePackages = {"com.changgou.user.feign"})
@MapperScan(basePackages = {"com.changgou.seckill.dao"})
@EnableScheduling
// 开启异步执行方式
@EnableAsync
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
