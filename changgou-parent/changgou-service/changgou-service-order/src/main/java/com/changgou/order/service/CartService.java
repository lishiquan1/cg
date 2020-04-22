package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;

import java.util.List;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/19 10:37 上午
 */
public interface CartService {
    /**
     * 加入购物车
     * @param skuId 商品id
     * @param num 数量
     * @param id 用户id
     */
    void add(Integer skuId, Integer num, Integer id);

    /**
     * 查询购物车
     * @param id 用户id
     * @return 查询结果集
     */
    List<OrderItem> list(Integer id);
}
