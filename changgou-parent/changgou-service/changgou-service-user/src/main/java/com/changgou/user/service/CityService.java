package com.changgou.user.service;

import com.changgou.user.pojo.City;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo City业务层接口
 *
 * @author lishiquan
 */
public interface CityService {
    
    /**
     * 查询所有City
     * @return 查询结果
     */
    List<City> findAll();

    /**
     * 根据ID查询City
     * @param id City id
     * @return 查询结果
     */
    City findById(Integer id);


    /**
     * 新增City
     * @param city City实体类
     */
    void add(City city);


    /**
     * 修改City数据
     * @param city City实体类
     */
    void update(City city);

    /**
     * 删除City
     * @param id City id
     */
    void delete(Integer id);


    /**
     * City多条件搜索方法
     * @param city 查询条件
     * @return 查询结果
     */
    List<City> findList(City city);

    /**
     * City分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<City> findPage(Integer page, Integer size);

    /**
     * City条件查询 + 分页
     * @param city 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<City> findPage(City city, Integer page, Integer size);

}
