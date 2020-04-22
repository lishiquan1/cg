package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Para表现层
 *
 * @author lishiquan
 */
@Api(value = "ParaController")
@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {

    /**
     * para业务层接口
     */
    @Autowired
    private ParaService paraService;

    /**
     * 分类id查询模板id, 根据模板id查询参数集合
     * @param cid 分类di
     * @return 参数集合
     */
    @ApiOperation(value = "根据模板ID查询参数集合", notes = "分类id查询模板id, 根据模板id查询参数集合", tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "cid", value = "分类id", required = true, dataType = "Integer")
    @GetMapping("/category/{cid}")
    public Result<List<Para>> findByCategory(@PathVariable Integer cid) {
        List<Para> list = paraService.findByCategory(cid);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 查询Para全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Para", notes = "查询所Para有方法详情", tags = {"ParaController"})
    @GetMapping
    public Result<List<Para>> findAll() {
        // 调用ParaService实现查询所有Para
        List<Para> list = paraService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID查询Para数据
     * @param id Para id
     * @return 查询成功
     */
    @ApiOperation(value = "Para根据ID查询", notes = "根据ID查询Para方法详情", tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable Integer id) {
        // 调用ParaService实现根据主键查询Para
        Para para = paraService.findById(id);
        return new Result<Para>(true, StatusCode.OK, "查询成功", para);
    }

    /**
     * 新增para数据
     * @param para Para实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Para添加", notes = "添加Para方法详情", tags = {"ParaController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Para对象", value = "传入JSON数据", required = true) Para para) {
        // 调用ParaService实现添加Para
        paraService.add(para);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Para数据
     * @param para Para实体类
     * @param id Para id
     * @return 修改成功
     */
    @ApiOperation(value = "Para根据ID修改", notes = "根据ID修改Para方法详情", tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Para对象", value = "传入JSON数据", required = false) Para para, @PathVariable Integer id) {
        // 设置主键值
        para.setId(id);
        // 调用paraService实现修改Para
        paraService.update(para);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Para id
     * @return 删除成功
     */
    @ApiOperation(value = "Para根据ID删除", notes = "根据ID删除Para方法详情", tags = {"ParaController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        // 调用ParaService实现根据主键删除
        paraService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param para 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Para条件查询", notes = "条件查询Para方法详情", tags = {"ParaController"})
    @PostMapping(value = "/search")
    public Result<List<Para>> findList(@RequestBody(required = false) @ApiParam(name = "Para对象", value = "传入JSON数据", required = false) Para para) {
        // 调用ParaService实现条件查询Para
        List<Para> list = paraService.findList(para);
        return new Result<List<Para>>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Para分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Para分页查询", notes = "分页查询Para方法详情", tags = {"ParaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Para>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用ParaService实现分页查询Para
        PageInfo<Para> pageInfo = paraService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Para分页条件搜索
     * @param para 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Para条件分页查询", notes = "分页条件查询Para方法详情", tags = {"ParaController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Para>> findPage(@RequestBody(required = false) @ApiParam(name = "Para对象", value = "传入JSON数据", required = false) Para para, @PathVariable Integer page, @PathVariable Integer size) {
        //调用ParaService实现分页条件查询Para
        PageInfo<Para> pageInfo = paraService.findPage(para, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
