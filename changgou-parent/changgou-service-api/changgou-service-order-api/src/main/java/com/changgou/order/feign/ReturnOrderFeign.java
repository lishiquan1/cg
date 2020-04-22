package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.ReturnOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo ReturnOrder Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/returnOrder")
public interface ReturnOrderFeign {

    /**
     * 查询ReturnOrder全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<ReturnOrder>> findAll();

    /**
     * 根据ID查询ReturnOrder数据
     * @param id ReturnOrder id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<ReturnOrder> findById(@PathVariable Integer id);

    /**
     * 新增ReturnOrder数据
     * @param returnOrder ReturnOrder实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody ReturnOrder returnOrder);

    /**
     * 修改ReturnOrder数据
     * @param returnOrder ReturnOrder实体类
     * @param id ReturnOrder id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody ReturnOrder returnOrder,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id ReturnOrder id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<ReturnOrder> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param returnOrder 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<ReturnOrder>> findList(@RequestBody(required = false) ReturnOrder returnOrder);

    /**
     * ReturnOrder分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<ReturnOrder>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * ReturnOrder条件搜索 + 分页
     * @param returnOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<ReturnOrder>> findPage(@RequestBody(required = false) ReturnOrder returnOrder, @PathVariable Integer page, @PathVariable Integer size);
}