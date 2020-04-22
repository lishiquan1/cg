package com.changgou.order.controller;

import com.changgou.order.pojo.ReturnCause;
import com.changgou.order.service.ReturnCauseService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo ReturnCause表现层
 *
 * @author lishiquan
 */
@Api("ReturnCauseController")
@RestController
@RequestMapping("/returnCause")
@CrossOrigin
public class ReturnCauseController {

    /**
     * returnCause业务层接口
     */
    @Autowired
    private ReturnCauseService returnCauseService;

    /**
     * 查询ReturnCause全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有ReturnCause", notes = "查询所ReturnCause有方法详情", tags = {"ReturnCauseController"})
    @GetMapping
    public Result<List<ReturnCause>> findAll(){
        // 调用ReturnCauseService实现查询所有ReturnCause
        List<ReturnCause> returnCauses = returnCauseService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", returnCauses) ;
    }

    /**
     * 根据ID查询ReturnCause数据
     * @param id ReturnCause id
     * @return 查询成功
     */
    @ApiOperation(value = "ReturnCause根据ID查询", notes = "根据ID查询ReturnCause方法详情", tags = {"ReturnCauseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<ReturnCause> findById(@PathVariable Integer id){
        // 调用ReturnCauseService实现根据主键查询ReturnCause
        ReturnCause returnCause = returnCauseService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", returnCause);
    }

    /**
     * 新增returnCause数据
     * @param returnCause ReturnCause实体类
     * @return 添加成功
     */
    @ApiOperation(value = "ReturnCause添加", notes = "添加ReturnCause方法详情", tags = {"ReturnCauseController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = true) ReturnCause returnCause){
        // 调用ReturnCauseService实现添加ReturnCause
        returnCauseService.add(returnCause);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改ReturnCause数据
     * @param returnCause ReturnCause实体类
     * @param id ReturnCause id
     * @return 修改成功
     */
    @ApiOperation(value = "ReturnCause根据ID修改", notes = "根据ID修改ReturnCause方法详情", tags = {"ReturnCauseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = false) ReturnCause returnCause,@PathVariable Integer id){
        // 设置主键值
        returnCause.setId(id);
        // 调用returnCauseService实现修改ReturnCause
        returnCauseService.update(returnCause);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id ReturnCause id
     * @return 删除成功
     */
    @ApiOperation(value = "ReturnCause根据ID删除", notes = "根据ID删除ReturnCause方法详情", tags = {"ReturnCauseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用ReturnCauseService实现根据主键删除
        returnCauseService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param returnCause 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "ReturnCause条件查询", notes = "条件查询ReturnCause方法详情", tags = {"ReturnCauseController"})
    @PostMapping("/search")
    public Result<List<ReturnCause>> findList(@RequestBody(required = false) @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = false) ReturnCause returnCause){
        // 调用ReturnCauseService实现条件查询ReturnCause
        List<ReturnCause> returnCauses = returnCauseService.findList(returnCause);
        return new Result<>(true, StatusCode.OK, "查询成功", returnCauses);
    }

    /**
     * ReturnCause分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "ReturnCause分页查询", notes = "分页查询ReturnCause方法详情", tags = {"ReturnCauseController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnCause>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用ReturnCauseService实现分页查询ReturnCause
        PageInfo<ReturnCause> pageInfo = returnCauseService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * ReturnCause分页条件搜索
     * @param returnCause 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "ReturnCause条件分页查询",notes = "分页条件查询ReturnCause方法详情",tags = {"ReturnCauseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnCause>> findPage(@RequestBody(required = false) @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = false) ReturnCause returnCause, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用ReturnCauseService实现分页条件查询ReturnCause
        PageInfo<ReturnCause> pageInfo = returnCauseService.findPage(returnCause, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
