package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.CategoryBrand;
import com.changgou.goods.service.CategoryBrandService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo CategoryBrand表现层
 *
 * @author lishiquan
 */
@Api(value = "CategoryBrandController")
@RestController
@RequestMapping("/categoryBrand")
@CrossOrigin
public class CategoryBrandController {

    /**
     * categoryBrand业务层接口
     */
    @Autowired
    private CategoryBrandService categoryBrandService;

    /**
     * 查询CategoryBrand全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有CategoryBrand",notes = "查询所CategoryBrand有方法详情",tags = {"CategoryBrandController"})
    @GetMapping
    public Result<List<CategoryBrand>> findAll(){
        // 调用CategoryBrandService实现查询所有CategoryBrand
        List<CategoryBrand> list = categoryBrandService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list) ;
    }

    /**
     * 根据ID查询CategoryBrand数据
     * @param id CategoryBrand id
     * @return 查询成功
     */
    @ApiOperation(value = "CategoryBrand根据ID查询",notes = "根据ID查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<CategoryBrand> findById(@PathVariable Integer id){
        // 调用CategoryBrandService实现根据主键查询CategoryBrand
        CategoryBrand categoryBrand = categoryBrandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", categoryBrand);
    }

    /**
     * 新增categoryBrand数据
     * @param categoryBrand CategoryBrand实体类
     * @return 添加成功
     */
    @ApiOperation(value = "CategoryBrand添加",notes = "添加CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = true) CategoryBrand categoryBrand){
        // 调用CategoryBrandService实现添加CategoryBrand
        categoryBrandService.add(categoryBrand);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改CategoryBrand数据
     * @param categoryBrand CategoryBrand实体类
     * @param id CategoryBrand id
     * @return 修改成功
     */
    @ApiOperation(value = "CategoryBrand根据ID修改",notes = "根据ID修改CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = false) CategoryBrand categoryBrand,@PathVariable Integer id){
        // 设置主键值
        categoryBrand.setBrandId(id);
        // 调用categoryBrandService实现修改CategoryBrand
        categoryBrandService.update(categoryBrand);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id CategoryBrand id
     * @return 删除成功
     */
    @ApiOperation(value = "CategoryBrand根据ID删除",notes = "根据ID删除CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        // 调用CategoryBrandService实现根据主键删除
        categoryBrandService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param categoryBrand 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "CategoryBrand条件查询",notes = "条件查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @PostMapping(value = "/search" )
    public Result<List<CategoryBrand>> findList(@RequestBody(required = false) @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = false) CategoryBrand categoryBrand){
        // 调用CategoryBrandService实现条件查询CategoryBrand
        List<CategoryBrand> list = categoryBrandService.findList(categoryBrand);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * CategoryBrand分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "CategoryBrand分页查询",notes = "分页查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<CategoryBrand>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用CategoryBrandService实现分页查询CategoryBrand
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * CategoryBrand分页条件搜索
     * @param categoryBrand 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "CategoryBrand条件分页查询",notes = "分页条件查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<CategoryBrand>> findPage(@RequestBody(required = false) @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = false) CategoryBrand categoryBrand, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用CategoryBrandService实现分页条件查询CategoryBrand
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findPage(categoryBrand, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
