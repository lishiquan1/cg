package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Spu业务层接口
 *
 * @author lishiquan
 */
public interface SpuService {
    /**
     * 批量上架
     * @param ids 商品id组
     */
    void putMany(Integer[] ids);

    /**
     * 商品上架
     * @param id Spu id
     */
    void put(Integer id);

    /**
     * 商品下架
     * @param id Spu id
     */
    void pull(Integer id);

    /**
     * 商品审核
     * @param id Spu id
     */
    void audit(Integer id);

    /**
     * 根据id查询Goods信息
     * @param id Spu id
     * @return 商品信息
     */
    Goods findGoods(Integer id);

    /**
     * 商品添加
     * @param goods 商品信息
     */
    void saveGoods(Goods goods);

    /**
     * 查询所有Spu
     * @return 查询结果
     */
    List<Spu> findAll();

    /**
     * 根据ID查询Spu
     * @param id Spu id
     * @return 查询结果
     */
    Spu findById(Integer id);


    /**
     * 新增Spu
     * @param spu Spu实体类
     */
    void add(Spu spu);


    /**
     * 修改Spu数据
     * @param spu Spu实体类
     */
    void update(Spu spu);

    /**
     * 删除Spu
     * @param id Spu id
     */
    void delete(Integer id);


    /**
     * Spu多条件搜索方法
     * @param spu 查询条件
     * @return 查询结果
     */
    List<Spu> findList(Spu spu);

    /**
     * Spu分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Spu> findPage(Integer page, Integer size);

    /**
     * Spu条件查询 + 分页
     * @param spu 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Spu> findPage(Spu spu, Integer page, Integer size);
}
