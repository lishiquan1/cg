package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo OrderItem业务层接口
 *
 * @author lishiquan
 */
public interface OrderItemService {
    
    /**
     * 查询所有OrderItem
     * @return 查询结果
     */
    List<OrderItem> findAll();

    /**
     * 根据ID查询OrderItem
     * @param id OrderItem id
     * @return 查询结果
     */
    OrderItem findById(Integer id);


    /**
     * 新增OrderItem
     * @param orderItem OrderItem实体类
     */
    void add(OrderItem orderItem);


    /**
     * 修改OrderItem数据
     * @param orderItem OrderItem实体类
     */
    void update(OrderItem orderItem);

    /**
     * 删除OrderItem
     * @param id OrderItem id
     */
    void delete(Integer id);


    /**
     * OrderItem多条件搜索方法
     * @param orderItem 查询条件
     * @return 查询结果
     */
    List<OrderItem> findList(OrderItem orderItem);

    /**
     * OrderItem分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<OrderItem> findPage(Integer page, Integer size);

    /**
     * OrderItem条件查询 + 分页
     * @param orderItem 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<OrderItem> findPage(OrderItem orderItem, Integer page, Integer size);

}
