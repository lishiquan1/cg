package com.changgou.order.feign;

import com.changgou.common.entity.Result;
import com.changgou.order.pojo.ReturnCause;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo ReturnCause Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "order")
@RequestMapping("/returnCause")
public interface ReturnCauseFeign {

    /**
     * 查询ReturnCause全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<ReturnCause>> findAll();

    /**
     * 根据ID查询ReturnCause数据
     * @param id ReturnCause id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<ReturnCause> findById(@PathVariable Integer id);

    /**
     * 新增ReturnCause数据
     * @param returnCause ReturnCause实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody ReturnCause returnCause);

    /**
     * 修改ReturnCause数据
     * @param returnCause ReturnCause实体类
     * @param id ReturnCause id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody ReturnCause returnCause,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id ReturnCause id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<ReturnCause> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param returnCause 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<ReturnCause>> findList(@RequestBody(required = false) ReturnCause returnCause);

    /**
     * ReturnCause分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<ReturnCause>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * ReturnCause条件搜索 + 分页
     * @param returnCause 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<ReturnCause>> findPage(@RequestBody(required = false) ReturnCause returnCause, @PathVariable Integer page, @PathVariable Integer size);
}