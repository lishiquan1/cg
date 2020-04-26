package com.changgou.order.rabbitMQ.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Demo 过期消息监听
 *
 * @author lishiquan
 * @date 2020/4/26 9:40 上午
 */
@Component
@RabbitListener(queues = "orderListenerQueue")
public class DelayMessageListener {
    /**
     * 延时队列监听
     */
    @RabbitHandler
    public void getDelayMessage(String message){

    }
}
