package com.changgou.seckill.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Demo 延时队列配置
 *
 * @author lishiquan
 * @date 2020/5/2 12:40 上午
 */
@Configuration
public class QueueConfig {

    /**
     * 创建Queue1, 延时队列
     */
    @Bean
    public Queue seckillDelayQueue() {
        return QueueBuilder.durable("seckillDelayQueue")
                // seckillDelayQueue队列信息会过期, 过期之后, 进入死信队列, 死信队列数据绑定到其他交换机中
                .withArgument("x-dead-letter-exchange", "seckillExchange")
                .withArgument("x-dead-letter-routing-key", "seckillQueue")
                .build();
    }

    /**
     * 创建Queue2, 死信队列
     */
    @Bean
    public Queue seckillQueue() {
        return new Queue("seckillQueue", true);
    }

    /**
     * 创建交换机
     */
    @Bean
    public Exchange seckillExchange() {
        return new DirectExchange("seckillExchange");
    }

    /**
     * 队列Queue2绑定Exchange
     */
    @Bean
    public Binding seckillDelayBinding(Queue seckillQueue, Exchange seckillExchange) {
        return BindingBuilder.bind(seckillQueue).to(seckillExchange).with("seckillQueue").noargs();
    }

}
