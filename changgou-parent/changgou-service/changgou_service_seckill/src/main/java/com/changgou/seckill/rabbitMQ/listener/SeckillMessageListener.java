package com.changgou.seckill.rabbitMQ.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Demo 秒杀订单监听
 *
 * @author lishiquan
 * @date 2020/4/28 3:05 下午
 */
@Component
@RabbitListener(queues = {"${mq.pay.queue.seckillorder}"})
public class SeckillMessageListener {
    @Autowired
    private SeckillOrderService seckillOrderService;
    /**
     * 消息监听
     * @param massage 监听到的消息(Map字符串)
     */
    @RabbitHandler
    public void getMessage(String massage){
        try {
            Map<String, String> resultMap = JSON.parseObject(massage, Map.class);
            String returnCode = resultMap.get("return_code");
            // 订单号
            String outTradeNo = resultMap.get("out_trade_no");
            // 自定义数据
            String attach = resultMap.get("attach");
            Map <String, String> attachMap = JSON.parseObject(attach, Map.class);
            if ("SUCCESS".equals(returnCode)){
                String resultCode = resultMap.get("result_code");
                if ("SUCCESS".equals(resultCode)){
                    // 修改订单状态
                    seckillOrderService.updatePayStatus(attachMap.get("id"), resultMap.get("transaction_id"), resultMap.get("time_end"));
                }else {
                    // 删除订单
                    seckillOrderService.deleteOrder(Integer.valueOf(attachMap.get("id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
