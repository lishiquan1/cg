package com.changgou.user.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.user.pojo.City;
import com.changgou.user.service.CityService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo City表现层
 *
 * @author lishiquan
 */
@Api("CityController")
@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    /**
     * city业务层接口
     */
    @Autowired
    private CityService cityService;

    /**
     * 查询City全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有City", notes = "查询所City有方法详情", tags = {"CityController"})
    @GetMapping
    public Result<List<City>> findAll(){
        // 调用CityService实现查询所有City
        List<City> citys = cityService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", citys) ;
    }

    /**
     * 根据ID查询City数据
     * @param id City id
     * @return 查询成功
     */
    @ApiOperation(value = "City根据ID查询", notes = "根据ID查询City方法详情", tags = {"CityController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<City> findById(@PathVariable Integer id){
        // 调用CityService实现根据主键查询City
        City city = cityService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", city);
    }

    /**
     * 新增city数据
     * @param city City实体类
     * @return 添加成功
     */
    @ApiOperation(value = "City添加", notes = "添加City方法详情", tags = {"CityController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "City对象",value = "传入JSON数据",required = true) City city){
        // 调用CityService实现添加City
        cityService.add(city);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改City数据
     * @param city City实体类
     * @param id City id
     * @return 修改成功
     */
    @ApiOperation(value = "City根据ID修改", notes = "根据ID修改City方法详情", tags = {"CityController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "City对象",value = "传入JSON数据",required = false) City city,@PathVariable Integer id){
        // 设置主键值
        city.setId(id);
        // 调用cityService实现修改City
        cityService.update(city);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id City id
     * @return 删除成功
     */
    @ApiOperation(value = "City根据ID删除", notes = "根据ID删除City方法详情", tags = {"CityController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用CityService实现根据主键删除
        cityService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param city 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "City条件查询", notes = "条件查询City方法详情", tags = {"CityController"})
    @PostMapping("/search")
    public Result<List<City>> findList(@RequestBody(required = false) @ApiParam(name = "City对象",value = "传入JSON数据",required = false) City city){
        // 调用CityService实现条件查询City
        List<City> citys = cityService.findList(city);
        return new Result<>(true, StatusCode.OK, "查询成功", citys);
    }

    /**
     * City分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "City分页查询", notes = "分页查询City方法详情", tags = {"CityController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<City>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用CityService实现分页查询City
        PageInfo<City> pageInfo = cityService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * City分页条件搜索
     * @param city 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "City条件分页查询",notes = "分页条件查询City方法详情",tags = {"CityController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<City>> findPage(@RequestBody(required = false) @ApiParam(name = "City对象",value = "传入JSON数据",required = false) City city, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用CityService实现分页条件查询City
        PageInfo<City> pageInfo = cityService.findPage(city, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
