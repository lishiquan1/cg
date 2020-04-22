package com.changgou.user.feign;

import com.changgou.common.entity.Result;
import com.changgou.user.pojo.City;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo City Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "user")
@RequestMapping("/city")
public interface CityFeign {

    /**
     * 查询City全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<City>> findAll();

    /**
     * 根据ID查询City数据
     * @param id City id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<City> findById(@PathVariable Integer id);

    /**
     * 新增City数据
     * @param city City实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody City city);

    /**
     * 修改City数据
     * @param city City实体类
     * @param id City id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody City city,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id City id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<City> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param city 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<City>> findList(@RequestBody(required = false) City city);

    /**
     * City分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<City>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * City条件搜索 + 分页
     * @param city 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<City>> findPage(@RequestBody(required = false) City city, @PathVariable Integer page, @PathVariable Integer size);
}