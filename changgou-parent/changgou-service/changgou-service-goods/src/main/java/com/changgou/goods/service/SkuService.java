package com.changgou.goods.service;

import com.changgou.goods.pojo.Sku;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Demo Sku业务层接口
 *
 * @author lishiquan
 */
public interface SkuService {
    /**
     * 查询所有Sku
     * @return 查询结果
     */
    List<Sku> findAll();

    /**
     * 根据ID查询Sku
     * @param id Sku id
     * @return 查询结果
     */
    Sku findById(Integer id);


    /**
     * 新增Sku
     * @param sku Sku实体类
     */
    void add(Sku sku);


    /**
     * 修改Sku数据
     * @param sku Sku实体类
     */
    void update(Sku sku);

    /**
     * 删除Sku
     * @param id Sku id
     */
    void delete(Integer id);


    /**
     * Sku多条件搜索方法
     * @param sku 查询条件
     * @return 查询结果
     */
    List<Sku> findList(Sku sku);

    /**
     * Sku分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Sku> findPage(Integer page, Integer size);

    /**
     * Sku条件查询 + 分页
     * @param sku 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Sku> findPage(Sku sku, Integer page, Integer size);

    /**
     * 商品库存递减
     * @param decrMap 商品信息
     */
    void decrCount(Map<String, Integer> decrMap);
}
