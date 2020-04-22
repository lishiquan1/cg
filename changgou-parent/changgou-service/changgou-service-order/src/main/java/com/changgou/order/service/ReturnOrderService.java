package com.changgou.order.service;

import com.changgou.order.pojo.ReturnOrder;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo ReturnOrder业务层接口
 *
 * @author lishiquan
 */
public interface ReturnOrderService {
    
    /**
     * 查询所有ReturnOrder
     * @return 查询结果
     */
    List<ReturnOrder> findAll();

    /**
     * 根据ID查询ReturnOrder
     * @param id ReturnOrder id
     * @return 查询结果
     */
    ReturnOrder findById(Integer id);


    /**
     * 新增ReturnOrder
     * @param returnOrder ReturnOrder实体类
     */
    void add(ReturnOrder returnOrder);


    /**
     * 修改ReturnOrder数据
     * @param returnOrder ReturnOrder实体类
     */
    void update(ReturnOrder returnOrder);

    /**
     * 删除ReturnOrder
     * @param id ReturnOrder id
     */
    void delete(Integer id);


    /**
     * ReturnOrder多条件搜索方法
     * @param returnOrder 查询条件
     * @return 查询结果
     */
    List<ReturnOrder> findList(ReturnOrder returnOrder);

    /**
     * ReturnOrder分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<ReturnOrder> findPage(Integer page, Integer size);

    /**
     * ReturnOrder条件查询 + 分页
     * @param returnOrder 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<ReturnOrder> findPage(ReturnOrder returnOrder, Integer page, Integer size);

}
