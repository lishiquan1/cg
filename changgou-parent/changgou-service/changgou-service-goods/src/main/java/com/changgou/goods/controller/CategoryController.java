package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Category表现层
 *
 * @author lishiquan
 */
@Api(value = "CategoryController")
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    /**
     * category业务层接口
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询子分类
     * @return 查询结果
     */
    @ApiOperation(value = "Category根据ParentID查询",notes = "根据父节点查询子分类",tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "pid", value = "ParentID", required = true, dataType = "Integer")
    @GetMapping("/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable Integer pid){
        List<Category> list = categoryService.findByParentId(pid);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 查询Category全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Category",notes = "查询所Category有方法详情",tags = {"CategoryController"})
    @GetMapping
    public Result<List<Category>> findAll(){
        // 调用CategoryService实现查询所有Category
        List<Category> list = categoryService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list) ;
    }

    /**
     * 根据ID查询Category数据
     * @param id Category id
     * @return 查询成功
     */
    @ApiOperation(value = "Category根据ID查询",notes = "根据ID查询Category方法详情",tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id){
        // 调用CategoryService实现根据主键查询Category
        Category category = categoryService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", category);
    }

    /**
     * 新增category数据
     * @param category Category实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Category添加",notes = "添加Category方法详情",tags = {"CategoryController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Category对象",value = "传入JSON数据",required = true) Category category){
        // 调用CategoryService实现添加Category
        categoryService.add(category);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Category数据
     * @param category Category实体类
     * @param id Category id
     * @return 修改成功
     */
    @ApiOperation(value = "Category根据ID修改",notes = "根据ID修改Category方法详情",tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Category对象",value = "传入JSON数据",required = false) Category category,@PathVariable Integer id){
        // 设置主键值
        category.setId(id);
        // 调用categoryService实现修改Category
        categoryService.update(category);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Category id
     * @return 删除成功
     */
    @ApiOperation(value = "Category根据ID删除",notes = "根据ID删除Category方法详情",tags = {"CategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        // 调用CategoryService实现根据主键删除
        categoryService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param category 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Category条件查询",notes = "条件查询Category方法详情",tags = {"CategoryController"})
    @PostMapping(value = "/search" )
    public Result<List<Category>> findList(@RequestBody(required = false) @ApiParam(name = "Category对象",value = "传入JSON数据",required = false) Category category){
        // 调用CategoryService实现条件查询Category
        List<Category> list = categoryService.findList(category);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Category分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Category分页查询",notes = "分页查询Category方法详情",tags = {"CategoryController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Category>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用CategoryService实现分页查询Category
        PageInfo<Category> pageInfo = categoryService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Category分页条件搜索
     * @param category 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Category条件分页查询",notes = "分页条件查询Category方法详情",tags = {"CategoryController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Category>> findPage(@RequestBody(required = false) @ApiParam(name = "Category对象",value = "传入JSON数据",required = false) Category category, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用CategoryService实现分页条件查询Category
        PageInfo<Category> pageInfo = categoryService.findPage(category, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
