package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.OrderLog;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo OrderLog Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/orderLog")
public interface OrderLogFeign {

    /**
     * 查询OrderLog全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<OrderLog>> findAll();

    /**
     * 根据ID查询OrderLog数据
     * @param id OrderLog id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<OrderLog> findById(@PathVariable Integer id);

    /**
     * 新增OrderLog数据
     * @param orderLog OrderLog实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody OrderLog orderLog);

    /**
     * 修改OrderLog数据
     * @param orderLog OrderLog实体类
     * @param id OrderLog id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody OrderLog orderLog,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id OrderLog id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<OrderLog> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param orderLog 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<OrderLog>> findList(@RequestBody(required = false) OrderLog orderLog);

    /**
     * OrderLog分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<OrderLog>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * OrderLog条件搜索 + 分页
     * @param orderLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<OrderLog>> findPage(@RequestBody(required = false) OrderLog orderLog, @PathVariable Integer page, @PathVariable Integer size);
}