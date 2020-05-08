package com.changgou.seckill.rabbitMQ.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.common.entity.SeckillStatus;
import com.changgou.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Demo 秒杀订单监听
 *
 * @author lishiquan
 * @date 2020/4/28 3:05 下午
 */
@Component
@RabbitListener(queues = {"seckillQueue"})
public class SeckillDelayListener {
    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 消息监听
     * @param massage 监听到的消息(Map字符串)
     */
    @RabbitHandler
    public void getMessage(String massage){
        try {
            // 获取用户排队信息
            SeckillStatus seckillStatus = JSON.parseObject(massage, SeckillStatus.class);
            // 如果Redis中没有用户排队信息, 则表明该订单已经处理, 如果有用户排队信息, 则表示用户尚未完成支付, 关闭订单[关闭微信支付]
            Object userQueueStatus = redisTemplate.boundHashOps("UserQueueStatus").get(seckillStatus.getUserId());
            if (userQueueStatus != null){
                // 删除订单
                seckillOrderService.deleteOrder(seckillStatus.getUserId());
                // 关闭微信支付
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
