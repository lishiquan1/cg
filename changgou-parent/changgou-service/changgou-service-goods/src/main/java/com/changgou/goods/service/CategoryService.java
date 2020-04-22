package com.changgou.goods.service;

import com.changgou.goods.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Category业务层接口
 *
 * @author lishiquan
 */
public interface CategoryService {

    /**
     * 根据分类的父节点id查询所有子节点集合
     * @param pid 父节点ID -> 1级分类 = 0
     * @return 查询结果
     */
    List<Category> findByParentId(Integer pid);

    /**
     * 查询所有Category
     * @return 查询结果
     */
    List<Category> findAll();

    /**
     * 根据ID查询Category
     * @param id Category id
     * @return 查询结果
     */
    Category findById(Integer id);


    /**
     * 新增Category
     * @param category Category实体类
     */
    void add(Category category);


    /**
     * 修改Category数据
     * @param category Category实体类
     */
    void update(Category category);

    /**
     * 删除Category
     * @param id Category id
     */
    void delete(Integer id);


    /**
     * Category多条件搜索方法
     * @param category 查询条件
     * @return 查询结果
     */
    List<Category> findList(Category category);

    /**
     * Category分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Category> findPage(Integer page, Integer size);

    /**
     * Category条件查询 + 分页
     * @param category 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Category> findPage(Category category, Integer page, Integer size);

}
