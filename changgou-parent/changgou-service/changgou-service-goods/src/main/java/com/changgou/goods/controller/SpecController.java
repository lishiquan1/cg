package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Spec表现层
 *
 * @author lishiquan
 */
@Api(value = "SpecController")
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    /**
     * spec业务层接口
     */
    @Autowired
    private SpecService specService;

    /**
     * 分类id查询模板id, 根据模板id查询规格集合
     * @param cid Category分类id
     * @return 查询成功
     */
    @ApiOperation(value = "Spec根据分类ID查询规格集合", notes = "分类id查询模板id, 根据模板id查询规格集合", tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "cid", value = "分类id", required = true, dataType = "Integer")
    @GetMapping("/category/{cid}")
    public Result<List<Spec>> findByCategory(@PathVariable Integer cid) {
        List<Spec> list = specService.findByCategory(cid);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 查询Spec全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Spec", notes = "查询所Spec有方法详情", tags = {"SpecController"})
    @GetMapping
    public Result<List<Spec>> findAll() {
        // 调用SpecService实现查询所有Spec
        List<Spec> list = specService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID查询Spec数据
     * @param id Spec id
     * @return 查询成功
     */
    @ApiOperation(value = "Spec根据ID查询", notes = "根据ID查询Spec方法详情", tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id) {
        // 调用SpecService实现根据主键查询Spec
        Spec spec = specService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", spec);
    }

    /**
     * 新增spec数据
     * @param spec Spec实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Spec添加", notes = "添加Spec方法详情", tags = {"SpecController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Spec对象", value = "传入JSON数据", required = true) Spec spec) {
        // 调用SpecService实现添加Spec
        specService.add(spec);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Spec数据
     * @param spec Spec实体类
     * @param id Spec id
     * @return 修改成功
     */
    @ApiOperation(value = "Spec根据ID修改", notes = "根据ID修改Spec方法详情", tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Spec对象", value = "传入JSON数据", required = false) Spec spec, @PathVariable Integer id) {
        // 设置主键值
        spec.setId(id);
        // 调用specService实现修改Spec
        specService.update(spec);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Spec id
     * @return 删除成功
     */
    @ApiOperation(value = "Spec根据ID删除", notes = "根据ID删除Spec方法详情", tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        // 调用SpecService实现根据主键删除
        specService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param spec 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Spec条件查询", notes = "条件查询Spec方法详情", tags = {"SpecController"})
    @PostMapping(value = "/search")
    public Result<List<Spec>> findList(@RequestBody(required = false) @ApiParam(name = "Spec对象", value = "传入JSON数据", required = false) Spec spec) {
        // 调用SpecService实现条件查询Spec
        List<Spec> list = specService.findList(spec);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Spec分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Spec分页查询", notes = "分页查询Spec方法详情", tags = {"SpecController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spec>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用SpecService实现分页查询Spec
        PageInfo<Spec> pageInfo = specService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Spec分页条件搜索
     * @param spec 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Spec条件分页查询", notes = "分页条件查询Spec方法详情", tags = {"SpecController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spec>> findPage(@RequestBody(required = false) @ApiParam(name = "Spec对象", value = "传入JSON数据", required = false) Spec spec, @PathVariable Integer page, @PathVariable Integer size) {
        //调用SpecService实现分页条件查询Spec
        PageInfo<Spec> pageInfo = specService.findPage(spec, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
