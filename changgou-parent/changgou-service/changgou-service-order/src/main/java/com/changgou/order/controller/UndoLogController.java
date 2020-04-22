package com.changgou.order.controller;

import com.changgou.order.pojo.UndoLog;
import com.changgou.order.service.UndoLogService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo UndoLog表现层
 *
 * @author lishiquan
 */
@Api("UndoLogController")
@RestController
@RequestMapping("/undoLog")
@CrossOrigin
public class UndoLogController {

    /**
     * undoLog业务层接口
     */
    @Autowired
    private UndoLogService undoLogService;

    /**
     * 查询UndoLog全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有UndoLog", notes = "查询所UndoLog有方法详情", tags = {"UndoLogController"})
    @GetMapping
    public Result<List<UndoLog>> findAll(){
        // 调用UndoLogService实现查询所有UndoLog
        List<UndoLog> undoLogs = undoLogService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", undoLogs) ;
    }

    /**
     * 根据ID查询UndoLog数据
     * @param id UndoLog id
     * @return 查询成功
     */
    @ApiOperation(value = "UndoLog根据ID查询", notes = "根据ID查询UndoLog方法详情", tags = {"UndoLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<UndoLog> findById(@PathVariable Integer id){
        // 调用UndoLogService实现根据主键查询UndoLog
        UndoLog undoLog = undoLogService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", undoLog);
    }

    /**
     * 新增undoLog数据
     * @param undoLog UndoLog实体类
     * @return 添加成功
     */
    @ApiOperation(value = "UndoLog添加", notes = "添加UndoLog方法详情", tags = {"UndoLogController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = true) UndoLog undoLog){
        // 调用UndoLogService实现添加UndoLog
        undoLogService.add(undoLog);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改UndoLog数据
     * @param undoLog UndoLog实体类
     * @param id UndoLog id
     * @return 修改成功
     */
    @ApiOperation(value = "UndoLog根据ID修改", notes = "根据ID修改UndoLog方法详情", tags = {"UndoLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = false) UndoLog undoLog,@PathVariable Integer id){
        // 设置主键值
        undoLog.setId(id);
        // 调用undoLogService实现修改UndoLog
        undoLogService.update(undoLog);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id UndoLog id
     * @return 删除成功
     */
    @ApiOperation(value = "UndoLog根据ID删除", notes = "根据ID删除UndoLog方法详情", tags = {"UndoLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用UndoLogService实现根据主键删除
        undoLogService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param undoLog 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "UndoLog条件查询", notes = "条件查询UndoLog方法详情", tags = {"UndoLogController"})
    @PostMapping("/search")
    public Result<List<UndoLog>> findList(@RequestBody(required = false) @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = false) UndoLog undoLog){
        // 调用UndoLogService实现条件查询UndoLog
        List<UndoLog> undoLogs = undoLogService.findList(undoLog);
        return new Result<>(true, StatusCode.OK, "查询成功", undoLogs);
    }

    /**
     * UndoLog分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "UndoLog分页查询", notes = "分页查询UndoLog方法详情", tags = {"UndoLogController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<UndoLog>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用UndoLogService实现分页查询UndoLog
        PageInfo<UndoLog> pageInfo = undoLogService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * UndoLog分页条件搜索
     * @param undoLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "UndoLog条件分页查询",notes = "分页条件查询UndoLog方法详情",tags = {"UndoLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<UndoLog>> findPage(@RequestBody(required = false) @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = false) UndoLog undoLog, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用UndoLogService实现分页条件查询UndoLog
        PageInfo<UndoLog> pageInfo = undoLogService.findPage(undoLog, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
