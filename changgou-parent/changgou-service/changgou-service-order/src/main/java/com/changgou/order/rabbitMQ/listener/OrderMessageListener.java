package com.changgou.order.rabbitMQ.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/25 5:40 下午
 */
@Component
@RabbitListener(queues = "${mq.pay.queue.order}")
public class OrderMessageListener {
    @Autowired
    private OrderService orderService;
    /**
     * 支付结果监听
     * @param message 支付结果信息
     */
    @RabbitHandler
    private void getMessage(String message) throws Exception{
        // 支付结果
        Map<String, String> resultMap = JSON.parseObject(message, Map.class);
        // 通信标识
        String return_code = resultMap.get("return_code");
        if ("SUCCESS".equals(return_code)) {
            // 订单号
            String outTradeNo = resultMap.get("out_trade_no");
            // 业务结果
            String result_code = resultMap.get("result_code");
            // 支付成功, 修改订单状态
            if ("SUCCESS".equals(result_code)) {
                // 微信支付交易流水号
                orderService.updateStatus(outTradeNo, resultMap.get("time_end"), resultMap.get("transaction_id"));
            }else {
                // 支付失败, 关闭订单, 取消订单, 回滚库存
                orderService.deleteOrder(outTradeNo);
            }
        }
    }
}
