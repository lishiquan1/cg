package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.Order;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo Order Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/order")
public interface OrderFeign {

    /**
     * 查询Order全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Order>> findAll();

    /**
     * 根据ID查询Order数据
     * @param id Order id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Order> findById(@PathVariable Integer id);

    /**
     * 新增Order数据
     * @param order Order实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Order order);

    /**
     * 修改Order数据
     * @param order Order实体类
     * @param id Order id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Order order,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Order id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Order> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param order 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Order>> findList(@RequestBody(required = false) Order order);

    /**
     * Order分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Order>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Order条件搜索 + 分页
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Order>> findPage(@RequestBody(required = false) Order order, @PathVariable Integer page, @PathVariable Integer size);
}