package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.Preferential;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo Preferential Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/preferential")
public interface PreferentialFeign {

    /**
     * 查询Preferential全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Preferential>> findAll();

    /**
     * 根据ID查询Preferential数据
     * @param id Preferential id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Preferential> findById(@PathVariable Integer id);

    /**
     * 新增Preferential数据
     * @param preferential Preferential实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Preferential preferential);

    /**
     * 修改Preferential数据
     * @param preferential Preferential实体类
     * @param id Preferential id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Preferential preferential,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Preferential id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Preferential> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param preferential 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Preferential>> findList(@RequestBody(required = false) Preferential preferential);

    /**
     * Preferential分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Preferential>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Preferential条件搜索 + 分页
     * @param preferential 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Preferential>> findPage(@RequestBody(required = false) Preferential preferential, @PathVariable Integer page, @PathVariable Integer size);
}