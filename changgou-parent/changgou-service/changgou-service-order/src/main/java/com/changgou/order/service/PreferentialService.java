package com.changgou.order.service;

import com.changgou.order.pojo.Preferential;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Preferential业务层接口
 *
 * @author lishiquan
 */
public interface PreferentialService {
    
    /**
     * 查询所有Preferential
     * @return 查询结果
     */
    List<Preferential> findAll();

    /**
     * 根据ID查询Preferential
     * @param id Preferential id
     * @return 查询结果
     */
    Preferential findById(Integer id);


    /**
     * 新增Preferential
     * @param preferential Preferential实体类
     */
    void add(Preferential preferential);


    /**
     * 修改Preferential数据
     * @param preferential Preferential实体类
     */
    void update(Preferential preferential);

    /**
     * 删除Preferential
     * @param id Preferential id
     */
    void delete(Integer id);


    /**
     * Preferential多条件搜索方法
     * @param preferential 查询条件
     * @return 查询结果
     */
    List<Preferential> findList(Preferential preferential);

    /**
     * Preferential分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Preferential> findPage(Integer page, Integer size);

    /**
     * Preferential条件查询 + 分页
     * @param preferential 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Preferential> findPage(Preferential preferential, Integer page, Integer size);

}
