package com.changgou.seckill.task;

import com.alibaba.fastjson.JSON;
import com.changgou.common.entity.SeckillStatus;
import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.pojo.SeckillOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demo 排队下单
 *
 * @author lishiquan
 * @date 2020/4/27 1:51 下午
 */
@Component
public class MultiThreadingCreateOrder {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 该方法会异步执行(底层多线程方式)
     */
    @Async
    public void createOrder() {
        // 从Redis队列中过去用户排队信息
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps("SeckillOrderQueue").rightPop();
        if (seckillStatus == null) {
            return;
        }
        // 抢单时间段
        String time = seckillStatus.getTime();
        // 商品id
        Integer id = seckillStatus.getGoodsId();
        // 用户id
        Integer userId = seckillStatus.getUserId();
        // 先到SeckillGoodsCountList_ID队列中获取该商品的一个信息, 如果能获取则可以下单
        // Object sgoods =redisTemplate.boundListOps("SeckillGoodsCountList_" + id).rightPop();
        Integer count = (Integer) redisTemplate.boundHashOps("SeckillGoodsCountList").get(id);
        Long goodsCount = redisTemplate.boundHashOps("SeckillGoodsCount").increment(id, 1);
        // 如果不能获取该商品的队列信息, 则表示没有库存, 清理排队
        if (count == goodsCount.intValue()) {
            // 情况排队信息
            this.clearUserQueue(userId);
            return;
        }
        String nameSpace = "SeckillGoods_" + time;
        // 查询秒杀商品
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(nameSpace).get(id);
        // 判断是否有库存
        if (seckillGoods == null || seckillGoods.getStockCount() <= 0) {
            throw new RuntimeException("已售罄");
        }
        // 创建订单对象
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setSeckillId(id);
        seckillOrder.setMoney(seckillGoods.getCostPrice());
        seckillOrder.setUserId(userId);
        seckillOrder.setCreateTime(new Date());
        // 将订单对象存储起来
        redisTemplate.boundHashOps("SeckillOrder").put(id, seckillOrder);
        // 判断库存是不是最后一个
        if (count == goodsCount.intValue()) {
            seckillGoods.setStockCount(0);
            // 同步数据到MySQL
            seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
            // 删除Redis中的商品数据
            redisTemplate.boundHashOps(nameSpace).delete(id);
        } else {
            // 库存递减
            seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
            // 同步数据到Redis
            redisTemplate.boundHashOps(nameSpace).put(id, seckillGoods);
        }
        // 组成订单号 SH + 创建时间(精确到毫秒) + 六位随机数
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 订单号
        seckillStatus.setOrder("SH" + sdf.format(date) + (int) ((Math.random() * 9 + 1) * 100000));
        // 支付金额
        seckillStatus.setMoney(seckillGoods.getCostPrice());
        // 支付状态, 待付款
        seckillStatus.setStatus(2);
        redisTemplate.boundHashOps("UserQueueStatus").put(userId, seckillStatus);

        // 发送消息给延迟对列
        rabbitTemplate.convertAndSend("seckillDelayQueue", (Object) JSON.toJSONString(seckillStatus), message -> {
            // 设置延时对列超时时间 1000 * 60 * 30 (30分钟)
            message.getMessageProperties().setExpiration("1800000");
            return message;
        });

    }

    /**
     * 清除用户排队抢单信息
     * @param id 用户id
     */
    public void clearUserQueue(Integer id) {
        // 清除排队标识
        redisTemplate.boundHashOps("UserQueueCount").delete(id);
        // 清除抢单状态
        redisTemplate.boundHashOps("UserQueueStatus").delete(id);
    }
}
