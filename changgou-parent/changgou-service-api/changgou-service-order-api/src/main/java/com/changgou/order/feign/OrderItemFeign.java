package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.OrderItem;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo OrderItem Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/orderItem")
public interface OrderItemFeign {

    /**
     * 查询OrderItem全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<OrderItem>> findAll();

    /**
     * 根据ID查询OrderItem数据
     * @param id OrderItem id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<OrderItem> findById(@PathVariable Integer id);

    /**
     * 新增OrderItem数据
     * @param orderItem OrderItem实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody OrderItem orderItem);

    /**
     * 修改OrderItem数据
     * @param orderItem OrderItem实体类
     * @param id OrderItem id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody OrderItem orderItem,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id OrderItem id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<OrderItem> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param orderItem 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<OrderItem>> findList(@RequestBody(required = false) OrderItem orderItem);

    /**
     * OrderItem分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<OrderItem>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * OrderItem条件搜索 + 分页
     * @param orderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<OrderItem>> findPage(@RequestBody(required = false) OrderItem orderItem, @PathVariable Integer page, @PathVariable Integer size);
}