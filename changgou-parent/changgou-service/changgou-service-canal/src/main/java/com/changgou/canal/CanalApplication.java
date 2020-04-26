package com.changgou.canal;

import com.xpand.starter.canal.annotation.CanalEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Demo Canal微服务启动类
 *
 * @author lishiquan
 * @date 2020/4/7 4:23 下午
 */
@SpringBootApplication
@EnableEurekaClient
@CanalEventListener
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);
    }
}
