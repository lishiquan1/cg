package com.changgou.user.service;

import com.changgou.user.pojo.Province;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Province业务层接口
 *
 * @author lishiquan
 */
public interface ProvinceService {
    
    /**
     * 查询所有Province
     * @return 查询结果
     */
    List<Province> findAll();

    /**
     * 根据ID查询Province
     * @param id Province id
     * @return 查询结果
     */
    Province findById(Integer id);


    /**
     * 新增Province
     * @param province Province实体类
     */
    void add(Province province);


    /**
     * 修改Province数据
     * @param province Province实体类
     */
    void update(Province province);

    /**
     * 删除Province
     * @param id Province id
     */
    void delete(Integer id);


    /**
     * Province多条件搜索方法
     * @param province 查询条件
     * @return 查询结果
     */
    List<Province> findList(Province province);

    /**
     * Province分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Province> findPage(Integer page, Integer size);

    /**
     * Province条件查询 + 分页
     * @param province 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Province> findPage(Province province, Integer page, Integer size);

}
