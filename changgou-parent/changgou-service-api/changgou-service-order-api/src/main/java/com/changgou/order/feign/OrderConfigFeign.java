package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.OrderConfig;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo OrderConfig Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/orderConfig")
public interface OrderConfigFeign {

    /**
     * 查询OrderConfig全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<OrderConfig>> findAll();

    /**
     * 根据ID查询OrderConfig数据
     * @param id OrderConfig id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<OrderConfig> findById(@PathVariable Integer id);

    /**
     * 新增OrderConfig数据
     * @param orderConfig OrderConfig实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody OrderConfig orderConfig);

    /**
     * 修改OrderConfig数据
     * @param orderConfig OrderConfig实体类
     * @param id OrderConfig id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody OrderConfig orderConfig,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id OrderConfig id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<OrderConfig> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param orderConfig 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<OrderConfig>> findList(@RequestBody(required = false) OrderConfig orderConfig);

    /**
     * OrderConfig分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<OrderConfig>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * OrderConfig条件搜索 + 分页
     * @param orderConfig 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<OrderConfig>> findPage(@RequestBody(required = false) OrderConfig orderConfig, @PathVariable Integer page, @PathVariable Integer size);
}