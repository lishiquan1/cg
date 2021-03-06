package com.changgou.order.controller;

import com.changgou.order.pojo.Task;
import com.changgou.order.service.TaskService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Task表现层
 *
 * @author lishiquan
 */
@Api("TaskController")
@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    /**
     * task业务层接口
     */
    @Autowired
    private TaskService taskService;

    /**
     * 查询Task全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Task", notes = "查询所Task有方法详情", tags = {"TaskController"})
    @GetMapping
    public Result<List<Task>> findAll(){
        // 调用TaskService实现查询所有Task
        List<Task> tasks = taskService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", tasks) ;
    }

    /**
     * 根据ID查询Task数据
     * @param id Task id
     * @return 查询成功
     */
    @ApiOperation(value = "Task根据ID查询", notes = "根据ID查询Task方法详情", tags = {"TaskController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Task> findById(@PathVariable Integer id){
        // 调用TaskService实现根据主键查询Task
        Task task = taskService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", task);
    }

    /**
     * 新增task数据
     * @param task Task实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Task添加", notes = "添加Task方法详情", tags = {"TaskController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Task对象",value = "传入JSON数据",required = true) Task task){
        // 调用TaskService实现添加Task
        taskService.add(task);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Task数据
     * @param task Task实体类
     * @param id Task id
     * @return 修改成功
     */
    @ApiOperation(value = "Task根据ID修改", notes = "根据ID修改Task方法详情", tags = {"TaskController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "Task对象",value = "传入JSON数据",required = false) Task task,@PathVariable Integer id){
        // 设置主键值
        task.setId(id);
        // 调用taskService实现修改Task
        taskService.update(task);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Task id
     * @return 删除成功
     */
    @ApiOperation(value = "Task根据ID删除", notes = "根据ID删除Task方法详情", tags = {"TaskController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用TaskService实现根据主键删除
        taskService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param task 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Task条件查询", notes = "条件查询Task方法详情", tags = {"TaskController"})
    @PostMapping("/search")
    public Result<List<Task>> findList(@RequestBody(required = false) @ApiParam(name = "Task对象",value = "传入JSON数据",required = false) Task task){
        // 调用TaskService实现条件查询Task
        List<Task> tasks = taskService.findList(task);
        return new Result<>(true, StatusCode.OK, "查询成功", tasks);
    }

    /**
     * Task分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Task分页查询", notes = "分页查询Task方法详情", tags = {"TaskController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Task>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用TaskService实现分页查询Task
        PageInfo<Task> pageInfo = taskService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Task分页条件搜索
     * @param task 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Task条件分页查询",notes = "分页条件查询Task方法详情",tags = {"TaskController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Task>> findPage(@RequestBody(required = false) @ApiParam(name = "Task对象",value = "传入JSON数据",required = false) Task task, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用TaskService实现分页条件查询Task
        PageInfo<Task> pageInfo = taskService.findPage(task, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
