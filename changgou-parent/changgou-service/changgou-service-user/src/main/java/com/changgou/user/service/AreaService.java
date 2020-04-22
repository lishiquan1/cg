package com.changgou.user.service;

import com.changgou.user.pojo.Area;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Area业务层接口
 *
 * @author lishiquan
 */
public interface AreaService {
    
    /**
     * 查询所有Area
     * @return 查询结果
     */
    List<Area> findAll();

    /**
     * 根据ID查询Area
     * @param id Area id
     * @return 查询结果
     */
    Area findById(Integer id);


    /**
     * 新增Area
     * @param area Area实体类
     */
    void add(Area area);


    /**
     * 修改Area数据
     * @param area Area实体类
     */
    void update(Area area);

    /**
     * 删除Area
     * @param id Area id
     */
    void delete(Integer id);


    /**
     * Area多条件搜索方法
     * @param area 查询条件
     * @return 查询结果
     */
    List<Area> findList(Area area);

    /**
     * Area分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Area> findPage(Integer page, Integer size);

    /**
     * Area条件查询 + 分页
     * @param area 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Area> findPage(Area area, Integer page, Integer size);

}
