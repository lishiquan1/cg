package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.Task;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo Task Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/task")
public interface TaskFeign {

    /**
     * 查询Task全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Task>> findAll();

    /**
     * 根据ID查询Task数据
     * @param id Task id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Task> findById(@PathVariable Integer id);

    /**
     * 新增Task数据
     * @param task Task实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Task task);

    /**
     * 修改Task数据
     * @param task Task实体类
     * @param id Task id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Task task,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Task id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Task> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param task 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Task>> findList(@RequestBody(required = false) Task task);

    /**
     * Task分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Task>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Task条件搜索 + 分页
     * @param task 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Task>> findPage(@RequestBody(required = false) Task task, @PathVariable Integer page, @PathVariable Integer size);
}