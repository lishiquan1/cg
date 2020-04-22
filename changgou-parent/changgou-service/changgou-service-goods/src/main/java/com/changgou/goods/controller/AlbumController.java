package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Album表现层
 *
 * @author lishiquan
 */
@Api(value = "AlbumController")
@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {

    /**
     * album业务层接口
     */
    @Autowired
    private AlbumService albumService;

    /**
     * 查询Album全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Album",notes = "查询所Album有方法详情",tags = {"AlbumController"})
    @GetMapping
    public Result<List<Album>> findAll(){
        // 调用AlbumService实现查询所有Album
        List<Album> list = albumService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list) ;
    }

    /**
     * 根据ID查询Album数据
     * @param id Album id
     * @return 查询成功
     */
    @ApiOperation(value = "Album根据ID查询",notes = "根据ID查询Album方法详情",tags = {"AlbumController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Integer id){
        // 调用AlbumService实现根据主键查询Album
        Album album = albumService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", album);
    }

    /**
     * 新增album数据
     * @param album Album实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Album添加",notes = "添加Album方法详情",tags = {"AlbumController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Album对象",value = "传入JSON数据",required = true) Album album){
        // 调用AlbumService实现添加Album
        albumService.add(album);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Album数据
     * @param album Album实体类
     * @param id Album id
     * @return 修改成功
     */
    @ApiOperation(value = "Album根据ID修改",notes = "根据ID修改Album方法详情",tags = {"AlbumController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Album对象",value = "传入JSON数据",required = false) Album album,@PathVariable Integer id){
        // 设置主键值
        album.setId(id);
        // 调用albumService实现修改Album
        albumService.update(album);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Album id
     * @return 删除成功
     */
    @ApiOperation(value = "Album根据ID删除",notes = "根据ID删除Album方法详情",tags = {"AlbumController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        // 调用AlbumService实现根据主键删除
        albumService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param album 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Album条件查询",notes = "条件查询Album方法详情",tags = {"AlbumController"})
    @PostMapping(value = "/search" )
    public Result<List<Album>> findList(@RequestBody(required = false) @ApiParam(name = "Album对象",value = "传入JSON数据",required = false) Album album){
        // 调用AlbumService实现条件查询Album
        List<Album> list = albumService.findList(album);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Album分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Album分页查询",notes = "分页查询Album方法详情",tags = {"AlbumController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}" )
    public Result<PageInfo<Album>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用AlbumService实现分页查询Album
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Album分页条件搜索
     * @param album 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Album条件分页查询",notes = "分页条件查询Album方法详情",tags = {"AlbumController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}" )
    public Result<PageInfo<Album>> findPage(@RequestBody(required = false) @ApiParam(name = "Album对象",value = "传入JSON数据",required = false) Album album, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用AlbumService实现分页条件查询Album
        PageInfo<Album> pageInfo = albumService.findPage(album, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
