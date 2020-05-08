package com.changgou.seckill.feign;

import com.changgou.common.entity.Result;
import com.changgou.seckill.pojo.SeckillOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo SeckillOrder Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "seckill")
@RequestMapping("/seckillOrder")
public interface SeckillOrderFeign {

    /**
     * 查询SeckillOrder全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<SeckillOrder>> findAll();

    /**
     * 根据ID查询SeckillOrder数据
     * @param id SeckillOrder id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<SeckillOrder> findById(@PathVariable Integer id);

    /**
     * 新增SeckillOrder数据
     * @param seckillOrder SeckillOrder实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody SeckillOrder seckillOrder);

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder SeckillOrder实体类
     * @param id SeckillOrder id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody SeckillOrder seckillOrder,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id SeckillOrder id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<SeckillOrder> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param seckillOrder 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<SeckillOrder>> findList(@RequestBody(required = false) SeckillOrder seckillOrder);

    /**
     * SeckillOrder分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<SeckillOrder>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * SeckillOrder条件搜索 + 分页
     * @param seckillOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<SeckillOrder>> findPage(@RequestBody(required = false) SeckillOrder seckillOrder, @PathVariable Integer page, @PathVariable Integer size);
}