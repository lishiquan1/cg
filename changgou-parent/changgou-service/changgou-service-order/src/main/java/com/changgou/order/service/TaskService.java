package com.changgou.order.service;

import com.changgou.order.pojo.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Task业务层接口
 *
 * @author lishiquan
 */
public interface TaskService {
    
    /**
     * 查询所有Task
     * @return 查询结果
     */
    List<Task> findAll();

    /**
     * 根据ID查询Task
     * @param id Task id
     * @return 查询结果
     */
    Task findById(Integer id);


    /**
     * 新增Task
     * @param task Task实体类
     */
    void add(Task task);


    /**
     * 修改Task数据
     * @param task Task实体类
     */
    void update(Task task);

    /**
     * 删除Task
     * @param id Task id
     */
    void delete(Integer id);


    /**
     * Task多条件搜索方法
     * @param task 查询条件
     * @return 查询结果
     */
    List<Task> findList(Task task);

    /**
     * Task分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Task> findPage(Integer page, Integer size);

    /**
     * Task条件查询 + 分页
     * @param task 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Task> findPage(Task task, Integer page, Integer size);

}
