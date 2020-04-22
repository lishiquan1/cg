package com.changgou.order.service;

import com.changgou.order.pojo.UndoLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo UndoLog业务层接口
 *
 * @author lishiquan
 */
public interface UndoLogService {
    
    /**
     * 查询所有UndoLog
     * @return 查询结果
     */
    List<UndoLog> findAll();

    /**
     * 根据ID查询UndoLog
     * @param id UndoLog id
     * @return 查询结果
     */
    UndoLog findById(Integer id);


    /**
     * 新增UndoLog
     * @param undoLog UndoLog实体类
     */
    void add(UndoLog undoLog);


    /**
     * 修改UndoLog数据
     * @param undoLog UndoLog实体类
     */
    void update(UndoLog undoLog);

    /**
     * 删除UndoLog
     * @param id UndoLog id
     */
    void delete(Integer id);


    /**
     * UndoLog多条件搜索方法
     * @param undoLog 查询条件
     * @return 查询结果
     */
    List<UndoLog> findList(UndoLog undoLog);

    /**
     * UndoLog分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<UndoLog> findPage(Integer page, Integer size);

    /**
     * UndoLog条件查询 + 分页
     * @param undoLog 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<UndoLog> findPage(UndoLog undoLog, Integer page, Integer size);

}
