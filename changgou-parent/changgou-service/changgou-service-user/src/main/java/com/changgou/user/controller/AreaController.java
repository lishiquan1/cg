package com.changgou.user.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.user.pojo.Area;
import com.changgou.user.service.AreaService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Area表现层
 *
 * @author lishiquan
 */
@Api("AreaController")
@RestController
@RequestMapping("/area")
@CrossOrigin
public class AreaController {

    /**
     * area业务层接口
     */
    @Autowired
    private AreaService areaService;

    /**
     * 查询Area全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Area", notes = "查询所Area有方法详情", tags = {"AreaController"})
    @GetMapping
    public Result<List<Area>> findAll(){
        // 调用AreaService实现查询所有Area
        List<Area> areas = areaService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", areas) ;
    }

    /**
     * 根据ID查询Area数据
     * @param id Area id
     * @return 查询成功
     */
    @ApiOperation(value = "Area根据ID查询", notes = "根据ID查询Area方法详情", tags = {"AreaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Area> findById(@PathVariable Integer id){
        // 调用AreaService实现根据主键查询Area
        Area area = areaService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", area);
    }

    /**
     * 新增area数据
     * @param area Area实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Area添加", notes = "添加Area方法详情", tags = {"AreaController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Area对象",value = "传入JSON数据",required = true) Area area){
        // 调用AreaService实现添加Area
        areaService.add(area);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Area数据
     * @param area Area实体类
     * @param id Area id
     * @return 修改成功
     */
    @ApiOperation(value = "Area根据ID修改", notes = "根据ID修改Area方法详情", tags = {"AreaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "Area对象",value = "传入JSON数据",required = false) Area area,@PathVariable Integer id){
        // 设置主键值
        area.setId(id);
        // 调用areaService实现修改Area
        areaService.update(area);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Area id
     * @return 删除成功
     */
    @ApiOperation(value = "Area根据ID删除", notes = "根据ID删除Area方法详情", tags = {"AreaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用AreaService实现根据主键删除
        areaService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param area 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Area条件查询", notes = "条件查询Area方法详情", tags = {"AreaController"})
    @PostMapping("/search")
    public Result<List<Area>> findList(@RequestBody(required = false) @ApiParam(name = "Area对象",value = "传入JSON数据",required = false) Area area){
        // 调用AreaService实现条件查询Area
        List<Area> areas = areaService.findList(area);
        return new Result<>(true, StatusCode.OK, "查询成功", areas);
    }

    /**
     * Area分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Area分页查询", notes = "分页查询Area方法详情", tags = {"AreaController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Area>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用AreaService实现分页查询Area
        PageInfo<Area> pageInfo = areaService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Area分页条件搜索
     * @param area 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Area条件分页查询",notes = "分页条件查询Area方法详情",tags = {"AreaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Area>> findPage(@RequestBody(required = false) @ApiParam(name = "Area对象",value = "传入JSON数据",required = false) Area area, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用AreaService实现分页条件查询Area
        PageInfo<Area> pageInfo = areaService.findPage(area, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
