package com.changgou.goods.service;

import com.changgou.goods.pojo.Spec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Spec业务层接口
 *
 * @author lishiquan
 */
public interface SpecService {
    /**
     * 分类id查询模板id, 根据模板id查询规格集合
     * @param cid 分类id
     * @return 规格集合
     */
    List<Spec> findByCategory(Integer cid);

    /**
     * 查询所有Spec
     * @return 查询结果
     */
    List<Spec> findAll();

    /**
     * 根据ID查询Spec
     * @param id Spec id
     * @return 查询结果
     */
    Spec findById(Integer id);


    /**
     * 新增Spec
     * @param spec Spec实体类
     */
    void add(Spec spec);


    /**
     * 修改Spec数据
     * @param spec Spec实体类
     */
    void update(Spec spec);

    /**
     * 删除Spec
     * @param id Spec id
     */
    void delete(Integer id);


    /**
     * Spec多条件搜索方法
     * @param spec 查询条件
     * @return 查询结果
     */
    List<Spec> findList(Spec spec);

    /**
     * Spec分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Spec> findPage(Integer page, Integer size);

    /**
     * Spec条件查询 + 分页
     * @param spec 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Spec> findPage(Spec spec, Integer page, Integer size);

}
