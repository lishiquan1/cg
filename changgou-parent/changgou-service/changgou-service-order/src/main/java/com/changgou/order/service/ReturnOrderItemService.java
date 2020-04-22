package com.changgou.order.service;

import com.changgou.order.pojo.ReturnOrderItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo ReturnOrderItem业务层接口
 *
 * @author lishiquan
 */
public interface ReturnOrderItemService {
    
    /**
     * 查询所有ReturnOrderItem
     * @return 查询结果
     */
    List<ReturnOrderItem> findAll();

    /**
     * 根据ID查询ReturnOrderItem
     * @param id ReturnOrderItem id
     * @return 查询结果
     */
    ReturnOrderItem findById(Integer id);


    /**
     * 新增ReturnOrderItem
     * @param returnOrderItem ReturnOrderItem实体类
     */
    void add(ReturnOrderItem returnOrderItem);


    /**
     * 修改ReturnOrderItem数据
     * @param returnOrderItem ReturnOrderItem实体类
     */
    void update(ReturnOrderItem returnOrderItem);

    /**
     * 删除ReturnOrderItem
     * @param id ReturnOrderItem id
     */
    void delete(Integer id);


    /**
     * ReturnOrderItem多条件搜索方法
     * @param returnOrderItem 查询条件
     * @return 查询结果
     */
    List<ReturnOrderItem> findList(ReturnOrderItem returnOrderItem);

    /**
     * ReturnOrderItem分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<ReturnOrderItem> findPage(Integer page, Integer size);

    /**
     * ReturnOrderItem条件查询 + 分页
     * @param returnOrderItem 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<ReturnOrderItem> findPage(ReturnOrderItem returnOrderItem, Integer page, Integer size);

}
