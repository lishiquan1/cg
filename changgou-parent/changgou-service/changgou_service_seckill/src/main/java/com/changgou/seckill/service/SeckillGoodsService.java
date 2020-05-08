package com.changgou.seckill.service;

import com.changgou.seckill.pojo.SeckillGoods;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo SeckillGoods业务层接口
 *
 * @author lishiquan
 */
public interface SeckillGoodsService {
    
    /**
     * 查询所有SeckillGoods
     * @return 查询结果
     */
    List<SeckillGoods> findAll();

    /**
     * 根据ID查询SeckillGoods
     * @param id SeckillGoods id
     * @return 查询结果
     */
    SeckillGoods findById(Integer id);


    /**
     * 新增SeckillGoods
     * @param seckillGoods SeckillGoods实体类
     */
    void add(SeckillGoods seckillGoods);


    /**
     * 修改SeckillGoods数据
     * @param seckillGoods SeckillGoods实体类
     */
    void update(SeckillGoods seckillGoods);

    /**
     * 删除SeckillGoods
     * @param id SeckillGoods id
     */
    void delete(Integer id);


    /**
     * SeckillGoods多条件搜索方法
     * @param seckillGoods 查询条件
     * @return 查询结果
     */
    List<SeckillGoods> findList(SeckillGoods seckillGoods);

    /**
     * SeckillGoods分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<SeckillGoods> findPage(Integer page, Integer size);

    /**
     * SeckillGoods条件查询 + 分页
     * @param seckillGoods 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<SeckillGoods> findPage(SeckillGoods seckillGoods, Integer page, Integer size);

    /**
     * 根据时间区间查询秒杀商品频道列表数据
     * @param time 秒杀频道时间
     * @return 秒杀商品列表
     */
    List<SeckillGoods> list(String time);

    /**
     * 查询秒杀商品详情
     * @param time 秒杀频道时间
     * @param id 商品id
     * @return 商品详情
     */
    SeckillGoods item(String time, Integer id);
}
