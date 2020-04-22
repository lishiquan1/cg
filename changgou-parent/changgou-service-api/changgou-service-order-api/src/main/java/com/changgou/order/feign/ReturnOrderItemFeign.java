package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.ReturnOrderItem;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo ReturnOrderItem Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/returnOrderItem")
public interface ReturnOrderItemFeign {

    /**
     * 查询ReturnOrderItem全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<ReturnOrderItem>> findAll();

    /**
     * 根据ID查询ReturnOrderItem数据
     * @param id ReturnOrderItem id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<ReturnOrderItem> findById(@PathVariable Integer id);

    /**
     * 新增ReturnOrderItem数据
     * @param returnOrderItem ReturnOrderItem实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody ReturnOrderItem returnOrderItem);

    /**
     * 修改ReturnOrderItem数据
     * @param returnOrderItem ReturnOrderItem实体类
     * @param id ReturnOrderItem id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody ReturnOrderItem returnOrderItem,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id ReturnOrderItem id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<ReturnOrderItem> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param returnOrderItem 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<ReturnOrderItem>> findList(@RequestBody(required = false) ReturnOrderItem returnOrderItem);

    /**
     * ReturnOrderItem分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<ReturnOrderItem>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * ReturnOrderItem条件搜索 + 分页
     * @param returnOrderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<ReturnOrderItem>> findPage(@RequestBody(required = false) ReturnOrderItem returnOrderItem, @PathVariable Integer page, @PathVariable Integer size);
}