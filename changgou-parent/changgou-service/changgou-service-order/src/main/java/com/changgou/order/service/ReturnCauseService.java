package com.changgou.order.service;

import com.changgou.order.pojo.ReturnCause;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo ReturnCause业务层接口
 *
 * @author lishiquan
 */
public interface ReturnCauseService {
    
    /**
     * 查询所有ReturnCause
     * @return 查询结果
     */
    List<ReturnCause> findAll();

    /**
     * 根据ID查询ReturnCause
     * @param id ReturnCause id
     * @return 查询结果
     */
    ReturnCause findById(Integer id);


    /**
     * 新增ReturnCause
     * @param returnCause ReturnCause实体类
     */
    void add(ReturnCause returnCause);


    /**
     * 修改ReturnCause数据
     * @param returnCause ReturnCause实体类
     */
    void update(ReturnCause returnCause);

    /**
     * 删除ReturnCause
     * @param id ReturnCause id
     */
    void delete(Integer id);


    /**
     * ReturnCause多条件搜索方法
     * @param returnCause 查询条件
     * @return 查询结果
     */
    List<ReturnCause> findList(ReturnCause returnCause);

    /**
     * ReturnCause分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<ReturnCause> findPage(Integer page, Integer size);

    /**
     * ReturnCause条件查询 + 分页
     * @param returnCause 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<ReturnCause> findPage(ReturnCause returnCause, Integer page, Integer size);

}
