package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.UndoLog;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo UndoLog Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/undoLog")
public interface UndoLogFeign {

    /**
     * 查询UndoLog全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<UndoLog>> findAll();

    /**
     * 根据ID查询UndoLog数据
     * @param id UndoLog id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<UndoLog> findById(@PathVariable Integer id);

    /**
     * 新增UndoLog数据
     * @param undoLog UndoLog实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody UndoLog undoLog);

    /**
     * 修改UndoLog数据
     * @param undoLog UndoLog实体类
     * @param id UndoLog id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody UndoLog undoLog,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id UndoLog id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<UndoLog> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param undoLog 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<UndoLog>> findList(@RequestBody(required = false) UndoLog undoLog);

    /**
     * UndoLog分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<UndoLog>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * UndoLog条件搜索 + 分页
     * @param undoLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<UndoLog>> findPage(@RequestBody(required = false) UndoLog undoLog, @PathVariable Integer page, @PathVariable Integer size);
}