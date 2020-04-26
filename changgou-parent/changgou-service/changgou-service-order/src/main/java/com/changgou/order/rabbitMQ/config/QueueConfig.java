package com.changgou.order.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Demo 延时队列配置
 *
 * @author lishiquan
 * @date 2020/4/25 11:29 下午
 */
@Configuration
public class QueueConfig {

    /**
     * 创建Queue1, 延时队列
     */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder.durable("orderDelayQueue")
                // orderDelayQueue队列信息会过期, 过期之后, 进入死信队列, 死信队列数据绑定到其他交换机中
                .withArgument("x-dead-letter-exchange", "orderDelayExchange")
                .withArgument("x-dead-letter-routing-key", "orderListenerQueue")
                .build();
    }

    /**
     * 创建Queue2, 死信队列
     */
    @Bean
    public Queue orderListenerQueue() {
        return new Queue("orderListenerQueue", true);
    }

    /**
     * 创建交换机
     */
    @Bean
    public Exchange orderDelayExchange() {
        return new DirectExchange("orderDelayExchange");
    }

    /**
     * 队列Queue2绑定Exchange
     */
    @Bean
    public Binding orderDelayBinding(Queue orderListenerQueue, Exchange orderDelayExchange) {
        return BindingBuilder.bind(orderListenerQueue).to(orderDelayExchange).with("orderListenerQueue").noargs();
    }

}
