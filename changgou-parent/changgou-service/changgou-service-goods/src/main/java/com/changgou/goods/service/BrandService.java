package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Brand业务层接口
 *
 * @author lishiquan
 */
public interface BrandService {

    /**
     * 根据分类id查询品牌集合
     * @param cid Category分类id
     * @return 品牌集合
     */
    List<Brand> findByCategory(Integer cid);

    /**
     * 查询所有Brand
     * @return 查询结果
     */
    List<Brand> findAll();

    /**
     * 根据ID查询Brand
     * @param id Brand id
     * @return 查询结果
     */
    Brand findById(Integer id);


    /**
     * 新增Brand
     * @param brand Brand实体类
     */
    void add(Brand brand);


    /**
     * 修改Brand数据
     * @param brand Brand实体类
     */
    void update(Brand brand);

    /**
     * 删除Brand
     * @param id Brand id
     */
    void delete(Integer id);


    /**
     * Brand多条件搜索方法
     * @param brand 查询条件
     * @return 查询结果
     */
    List<Brand> findByCategory(Brand brand);

    /**
     * Brand分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * Brand条件查询 + 分页
     * @param brand 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

}
