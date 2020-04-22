package com.changgou.order.service;

import com.changgou.order.pojo.OrderConfig;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo OrderConfig业务层接口
 *
 * @author lishiquan
 */
public interface OrderConfigService {
    
    /**
     * 查询所有OrderConfig
     * @return 查询结果
     */
    List<OrderConfig> findAll();

    /**
     * 根据ID查询OrderConfig
     * @param id OrderConfig id
     * @return 查询结果
     */
    OrderConfig findById(Integer id);


    /**
     * 新增OrderConfig
     * @param orderConfig OrderConfig实体类
     */
    void add(OrderConfig orderConfig);


    /**
     * 修改OrderConfig数据
     * @param orderConfig OrderConfig实体类
     */
    void update(OrderConfig orderConfig);

    /**
     * 删除OrderConfig
     * @param id OrderConfig id
     */
    void delete(Integer id);


    /**
     * OrderConfig多条件搜索方法
     * @param orderConfig 查询条件
     * @return 查询结果
     */
    List<OrderConfig> findList(OrderConfig orderConfig);

    /**
     * OrderConfig分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<OrderConfig> findPage(Integer page, Integer size);

    /**
     * OrderConfig条件查询 + 分页
     * @param orderConfig 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<OrderConfig> findPage(OrderConfig orderConfig, Integer page, Integer size);

}
