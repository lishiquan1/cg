package com.changgou.user.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.user.pojo.Province;
import com.changgou.user.service.ProvinceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Province表现层
 *
 * @author lishiquan
 */
@Api("ProvinceController")
@RestController
@RequestMapping("/province")
@CrossOrigin
public class ProvinceController {

    /**
     * province业务层接口
     */
    @Autowired
    private ProvinceService provinceService;

    /**
     * 查询Province全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Province", notes = "查询所Province有方法详情", tags = {"ProvinceController"})
    @GetMapping
    public Result<List<Province>> findAll(){
        // 调用ProvinceService实现查询所有Province
        List<Province> provinces = provinceService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", provinces) ;
    }

    /**
     * 根据ID查询Province数据
     * @param id Province id
     * @return 查询成功
     */
    @ApiOperation(value = "Province根据ID查询", notes = "根据ID查询Province方法详情", tags = {"ProvinceController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Province> findById(@PathVariable Integer id){
        // 调用ProvinceService实现根据主键查询Province
        Province province = provinceService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", province);
    }

    /**
     * 新增province数据
     * @param province Province实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Province添加", notes = "添加Province方法详情", tags = {"ProvinceController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Province对象",value = "传入JSON数据",required = true) Province province){
        // 调用ProvinceService实现添加Province
        provinceService.add(province);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Province数据
     * @param province Province实体类
     * @param id Province id
     * @return 修改成功
     */
    @ApiOperation(value = "Province根据ID修改", notes = "根据ID修改Province方法详情", tags = {"ProvinceController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "Province对象",value = "传入JSON数据",required = false) Province province,@PathVariable Integer id){
        // 设置主键值
        province.setId(id);
        // 调用provinceService实现修改Province
        provinceService.update(province);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Province id
     * @return 删除成功
     */
    @ApiOperation(value = "Province根据ID删除", notes = "根据ID删除Province方法详情", tags = {"ProvinceController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用ProvinceService实现根据主键删除
        provinceService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param province 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Province条件查询", notes = "条件查询Province方法详情", tags = {"ProvinceController"})
    @PostMapping("/search")
    public Result<List<Province>> findList(@RequestBody(required = false) @ApiParam(name = "Province对象",value = "传入JSON数据",required = false) Province province){
        // 调用ProvinceService实现条件查询Province
        List<Province> provinces = provinceService.findList(province);
        return new Result<>(true, StatusCode.OK, "查询成功", provinces);
    }

    /**
     * Province分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Province分页查询", notes = "分页查询Province方法详情", tags = {"ProvinceController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Province>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用ProvinceService实现分页查询Province
        PageInfo<Province> pageInfo = provinceService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Province分页条件搜索
     * @param province 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Province条件分页查询",notes = "分页条件查询Province方法详情",tags = {"ProvinceController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Province>> findPage(@RequestBody(required = false) @ApiParam(name = "Province对象",value = "传入JSON数据",required = false) Province province, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用ProvinceService实现分页条件查询Province
        PageInfo<Province> pageInfo = provinceService.findPage(province, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
