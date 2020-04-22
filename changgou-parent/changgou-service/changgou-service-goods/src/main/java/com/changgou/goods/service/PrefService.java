package com.changgou.goods.service;

import com.changgou.goods.pojo.Pref;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Pref业务层接口
 *
 * @author lishiquan
 */
public interface PrefService {
    
    /**
     * 查询所有Pref
     * @return 查询结果
     */
    List<Pref> findAll();

    /**
     * 根据ID查询Pref
     * @param id Pref id
     * @return 查询结果
     */
    Pref findById(Integer id);


    /**
     * 新增Pref
     * @param pref Pref实体类
     */
    void add(Pref pref);


    /**
     * 修改Pref数据
     * @param pref Pref实体类
     */
    void update(Pref pref);

    /**
     * 删除Pref
     * @param id Pref id
     */
    void delete(Integer id);


    /**
     * Pref多条件搜索方法
     * @param pref 查询条件
     * @return 查询结果
     */
    List<Pref> findList(Pref pref);

    /**
     * Pref分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Pref> findPage(Integer page, Integer size);

    /**
     * Pref条件查询 + 分页
     * @param pref 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Pref> findPage(Pref pref, Integer page, Integer size);

}
