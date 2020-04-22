package com.changgou.order.service;

import com.changgou.order.pojo.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Order业务层接口
 *
 * @author lishiquan
 */
public interface OrderService {
    
    /**
     * 查询所有Order
     * @return 查询结果
     */
    List<Order> findAll();

    /**
     * 根据ID查询Order
     * @param id Order id
     * @return 查询结果
     */
    Order findById(Integer id);


    /**
     * 新增Order
     * @param order Order实体类
     */
    void add(Order order);


    /**
     * 修改Order数据
     * @param order Order实体类
     */
    void update(Order order);

    /**
     * 删除Order
     * @param id Order id
     */
    void delete(Integer id);


    /**
     * Order多条件搜索方法
     * @param order 查询条件
     * @return 查询结果
     */
    List<Order> findList(Order order);

    /**
     * Order分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Order> findPage(Integer page, Integer size);

    /**
     * Order条件查询 + 分页
     * @param order 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Order> findPage(Order order, Integer page, Integer size);

}
