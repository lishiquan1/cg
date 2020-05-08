package com.changgou.seckill.service;

import com.changgou.common.entity.SeckillStatus;
import com.changgou.seckill.pojo.SeckillOrder;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo SeckillOrder业务层接口
 *
 * @author lishiquan
 */
public interface SeckillOrderService {

    /**
     * 删除订单
     * @param id 用户id
     */
    void deleteOrder(Integer id);

    /**
     * 修改订单状态
     * @param id 用户id
     * @param transactionId 微信支付流水号
     * @param endTime 交易结束时间
     */
    void updatePayStatus(String id, String transactionId, String endTime);

    /**
     * 添加秒杀订单
     * @param time 秒杀频道时间
     * @param id 商品id
     * @param userId 用户id
     */
    void add(String time, Integer id, Integer userId);

    /**
     * 查询所有SeckillOrder
     * @return 查询结果
     */
    List<SeckillOrder> findAll();

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder SeckillOrder实体类
     */
    void update(SeckillOrder seckillOrder);

    /**
     * SeckillOrder多条件搜索方法
     * @param seckillOrder 查询条件
     * @return 查询结果
     */
    List<SeckillOrder> findList(SeckillOrder seckillOrder);

    /**
     * SeckillOrder分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<SeckillOrder> findPage(Integer page, Integer size);

    /**
     * SeckillOrder条件查询 + 分页
     * @param seckillOrder 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<SeckillOrder> findPage(SeckillOrder seckillOrder, Integer page, Integer size);

    /**
     * 订单状态查询
     * @param id 用户id
     * @return 状态信息
     */
    SeckillStatus queryStatus(Integer id);
}
