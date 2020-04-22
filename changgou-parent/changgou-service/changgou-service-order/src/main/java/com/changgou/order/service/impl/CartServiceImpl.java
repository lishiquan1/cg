package com.changgou.order.service.impl;

import com.changgou.common.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo 购物车操作
 *
 * @author lishiquan
 * @date 2020/4/19 10:38 上午
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    /**
     * 加入购物车
     * @param skuId 规格id
     * @param num 数量
     * @param id 用户id
     */
    @Override
    public void add(Integer skuId, Integer num, Integer id) {
        BoundHashOperations boundHashOps = redisTemplate.boundHashOps("Cart_" + id);
        // 当添加购物车数量 < 0的时候, 需要移除该商品信息
        if (num < 0 || num == 0) {
            boundHashOps.delete(skuId);
            // 如果此时购物车数量为空, 则连购物车一起移除
            Long size = boundHashOps.size();
            if (size == null || size <= 0){
                boundHashOps.delete();
            }
            return;
        }
        // 查询商品详情
        // 1.查询sku
        Result<Sku> skuResult = skuFeign.findById(skuId);
        Sku sku = skuResult.getData();
        // 2.查询spu
        Result<Spu> spuResult = spuFeign.findById(sku.getSpuId());
        Spu spu = spuResult.getData();
        OrderItem orderItem = createOrderItem(num, sku, spu);
        // 将购物车数存入redis
        boundHashOps.put(skuId, orderItem);
    }

    /**
     * 查询购物车
     * @param id 用户id
     * @return 查询结果集
     */
    @Override
    public List<OrderItem> list(Integer id) {
        
        // 获取指定命名空间下所有数据
        return redisTemplate.boundHashOps("Cart_" + id).values();
    }

    /**
     * 创建OrderItem对象
     * @param num 数量
     * @param sku sku
     * @param spu spu
     * @return OrderItem
     */
    private OrderItem createOrderItem(Integer num, Sku sku, Spu spu) {
        // 将加入购物车的商品信息封装成OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        orderItem.setSkuId(sku.getId());
        orderItem.setSupId(spu.getId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(num * sku.getPrice());
        orderItem.setImage(sku.getImage());
        return orderItem;
    }
}
