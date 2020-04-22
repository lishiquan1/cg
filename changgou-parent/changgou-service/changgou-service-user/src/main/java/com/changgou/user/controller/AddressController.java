package com.changgou.user.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.common.util.TokenDecode;
import com.changgou.user.pojo.Address;
import com.changgou.user.service.AddressService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Demo Address表现层
 *
 * @author lishiquan
 */
@Api("AddressController")
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    /**
     * address业务层接口
     */
    @Autowired
    private AddressService addressService;

    /**
     * 根据用户id查询收件地址列表
     * @return 地址结果集
     */
    @ApiOperation(value = "根据用户id查询地址", notes = "根据用户id查询地址", tags = {"AddressController"})
    @GetMapping("/list")
    public Result<List<Address>> list() {
        // 获取用户id
        Map<String, Object> userInfo = TokenDecode.getUserInfo();
        Integer userId = (Integer) userInfo.get("id");
        List<Address> addresses = addressService.list(userId);
        return new Result<>(true, StatusCode.OK, "查询成功", addresses);
    }

    /**
     * 查询Address全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Address", notes = "查询所Address有方法详情", tags = {"AddressController"})
    @GetMapping
    public Result<List<Address>> findAll() {
        // 调用AddressService实现查询所有Address
        List<Address> addresss = addressService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", addresss);
    }

    /**
     * 根据ID查询Address数据
     * @param id Address id
     * @return 查询成功
     */
    @ApiOperation(value = "Address根据ID查询", notes = "根据ID查询Address方法详情", tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Address> findById(@PathVariable Integer id) {
        // 调用AddressService实现根据主键查询Address
        Address address = addressService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", address);
    }

    /**
     * 新增address数据
     * @param address Address实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Address添加", notes = "添加Address方法详情", tags = {"AddressController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Address对象", value = "传入JSON数据", required = true) Address address) {
        // 调用AddressService实现添加Address
        addressService.add(address);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Address数据
     * @param address Address实体类
     * @param id Address id
     * @return 修改成功
     */
    @ApiOperation(value = "Address根据ID修改", notes = "根据ID修改Address方法详情", tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "Address对象", value = "传入JSON数据", required = false) Address address, @PathVariable Integer id) {
        // 设置主键值
        address.setId(id);
        // 调用addressService实现修改Address
        addressService.update(address);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Address id
     * @return 删除成功
     */
    @ApiOperation(value = "Address根据ID删除", notes = "根据ID删除Address方法详情", tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        // 调用AddressService实现根据主键删除
        addressService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param address 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Address条件查询", notes = "条件查询Address方法详情", tags = {"AddressController"})
    @PostMapping("/search")
    public Result<List<Address>> findList(@RequestBody(required = false) @ApiParam(name = "Address对象", value = "传入JSON数据", required = false) Address address) {
        // 调用AddressService实现条件查询Address
        List<Address> addresss = addressService.findList(address);
        return new Result<>(true, StatusCode.OK, "查询成功", addresss);
    }

    /**
     * Address分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Address分页查询", notes = "分页查询Address方法详情", tags = {"AddressController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Address>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用AddressService实现分页查询Address
        PageInfo<Address> pageInfo = addressService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Address分页条件搜索
     * @param address 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Address条件分页查询", notes = "分页条件查询Address方法详情", tags = {"AddressController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Address>> findPage(@RequestBody(required = false) @ApiParam(name = "Address对象", value = "传入JSON数据", required = false) Address address, @PathVariable Integer page, @PathVariable Integer size) {
        //调用AddressService实现分页条件查询Address
        PageInfo<Address> pageInfo = addressService.findPage(address, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
