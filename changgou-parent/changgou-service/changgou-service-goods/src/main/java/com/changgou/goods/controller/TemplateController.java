package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Template表现层
 *
 * @author lishiquan
 */
@Api(value = "TemplateController")
@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    /**
     * template业务层接口
     */
    @Autowired
    private TemplateService templateService;

    /**
     * 查询Template全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Template",notes = "查询所Template有方法详情",tags = {"TemplateController"})
    @GetMapping
    public Result<List<Template>> findAll(){
        // 调用TemplateService实现查询所有Template
        List<Template> list = templateService.findAll();
        return new Result<List<Template>>(true, StatusCode.OK, "查询成功", list) ;
    }

    /**
     * 根据ID查询Template数据
     * @param id Template id
     * @return 查询成功
     */
    @ApiOperation(value = "Template根据ID查询",notes = "根据ID查询Template方法详情",tags = {"TemplateController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable Integer id){
        // 调用TemplateService实现根据主键查询Template
        Template template = templateService.findById(id);
        return new Result<Template>(true, StatusCode.OK, "查询成功", template);
    }

    /**
     * 新增template数据
     * @param template Template实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Template添加",notes = "添加Template方法详情",tags = {"TemplateController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Template对象",value = "传入JSON数据",required = true) Template template){
        // 调用TemplateService实现添加Template
        templateService.add(template);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Template数据
     * @param template Template实体类
     * @param id Template id
     * @return 修改成功
     */
    @ApiOperation(value = "Template根据ID修改",notes = "根据ID修改Template方法详情",tags = {"TemplateController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Template对象",value = "传入JSON数据",required = false) Template template,@PathVariable Integer id){
        // 设置主键值
        template.setId(id);
        // 调用templateService实现修改Template
        templateService.update(template);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Template id
     * @return 删除成功
     */
    @ApiOperation(value = "Template根据ID删除",notes = "根据ID删除Template方法详情",tags = {"TemplateController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        // 调用TemplateService实现根据主键删除
        templateService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param template 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Template条件查询",notes = "条件查询Template方法详情",tags = {"TemplateController"})
    @PostMapping(value = "/search" )
    public Result<List<Template>> findList(@RequestBody(required = false) @ApiParam(name = "Template对象",value = "传入JSON数据",required = false) Template template){
        // 调用TemplateService实现条件查询Template
        List<Template> list = templateService.findList(template);
        return new Result<List<Template>>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Template分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Template分页查询",notes = "分页查询Template方法详情",tags = {"TemplateController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Template>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用TemplateService实现分页查询Template
        PageInfo<Template> pageInfo = templateService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Template分页条件搜索
     * @param template 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Template条件分页查询",notes = "分页条件查询Template方法详情",tags = {"TemplateController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Template>> findPage(@RequestBody(required = false) @ApiParam(name = "Template对象",value = "传入JSON数据",required = false) Template template, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用TemplateService实现分页条件查询Template
        PageInfo<Template> pageInfo = templateService.findPage(template, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
