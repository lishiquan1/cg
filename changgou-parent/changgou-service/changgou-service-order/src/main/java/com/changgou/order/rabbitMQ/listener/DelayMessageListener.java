package com.changgou.order.rabbitMQ.listener;

import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private OrderService orderService;

    /**
     * 延时队列监听
     */
    @RabbitHandler
    public void getDelayMessage(String message) {
        // 关闭订单
        orderService.deleteOrder(message);
        // 回滚库存

    }
}
