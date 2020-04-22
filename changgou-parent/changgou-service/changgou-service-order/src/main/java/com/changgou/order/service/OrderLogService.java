package com.changgou.order.service;

import com.changgou.order.pojo.OrderLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo OrderLog业务层接口
 *
 * @author lishiquan
 */
public interface OrderLogService {
    
    /**
     * 查询所有OrderLog
     * @return 查询结果
     */
    List<OrderLog> findAll();

    /**
     * 根据ID查询OrderLog
     * @param id OrderLog id
     * @return 查询结果
     */
    OrderLog findById(Integer id);


    /**
     * 新增OrderLog
     * @param orderLog OrderLog实体类
     */
    void add(OrderLog orderLog);


    /**
     * 修改OrderLog数据
     * @param orderLog OrderLog实体类
     */
    void update(OrderLog orderLog);

    /**
     * 删除OrderLog
     * @param id OrderLog id
     */
    void delete(Integer id);


    /**
     * OrderLog多条件搜索方法
     * @param orderLog 查询条件
     * @return 查询结果
     */
    List<OrderLog> findList(OrderLog orderLog);

    /**
     * OrderLog分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<OrderLog> findPage(Integer page, Integer size);

    /**
     * OrderLog条件查询 + 分页
     * @param orderLog 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<OrderLog> findPage(OrderLog orderLog, Integer page, Integer size);

}
