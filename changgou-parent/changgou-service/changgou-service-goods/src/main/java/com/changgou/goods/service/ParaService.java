package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Para业务层接口
 *
 * @author lishiquan
 */
public interface ParaService {
    /**
     * 分类id查询模板id, 根据模板id查询参数集合
     * @param cid 分类di
     * @return 参数集合
     */
    List<Para> findByCategory(Integer cid);

    /**
     * 查询所有Para
     * @return 查询结果
     */
    List<Para> findAll();

    /**
     * 根据ID查询Para
     * @param id Para id
     * @return 查询结果
     */
    Para findById(Integer id);


    /**
     * 新增Para
     * @param para Para实体类
     */
    void add(Para para);


    /**
     * 修改Para数据
     * @param para Para实体类
     */
    void update(Para para);

    /**
     * 删除Para
     * @param id Para id
     */
    void delete(Integer id);


    /**
     * Para多条件搜索方法
     * @param para 查询条件
     * @return 查询结果
     */
    List<Para> findList(Para para);

    /**
     * Para分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Para> findPage(Integer page, Integer size);

    /**
     * Para条件查询 + 分页
     * @param para 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Para> findPage(Para para, Integer page, Integer size);

}
