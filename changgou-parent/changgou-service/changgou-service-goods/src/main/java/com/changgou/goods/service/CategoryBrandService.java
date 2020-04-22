package com.changgou.goods.service;

import com.changgou.goods.pojo.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo CategoryBrand业务层接口
 *
 * @author lishiquan
 */
public interface CategoryBrandService {
    
    /**
     * 查询所有CategoryBrand
     * @return 查询结果
     */
    List<CategoryBrand> findAll();

    /**
     * 根据ID查询CategoryBrand
     * @param id CategoryBrand id
     * @return 查询结果
     */
    CategoryBrand findById(Integer id);


    /**
     * 新增CategoryBrand
     * @param categoryBrand CategoryBrand实体类
     */
    void add(CategoryBrand categoryBrand);


    /**
     * 修改CategoryBrand数据
     * @param categoryBrand CategoryBrand实体类
     */
    void update(CategoryBrand categoryBrand);

    /**
     * 删除CategoryBrand
     * @param id CategoryBrand id
     */
    void delete(Integer id);


    /**
     * CategoryBrand多条件搜索方法
     * @param categoryBrand 查询条件
     * @return 查询结果
     */
    List<CategoryBrand> findList(CategoryBrand categoryBrand);

    /**
     * CategoryBrand分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<CategoryBrand> findPage(Integer page, Integer size);

    /**
     * CategoryBrand条件查询 + 分页
     * @param categoryBrand 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<CategoryBrand> findPage(CategoryBrand categoryBrand, Integer page, Integer size);

}
