package com.changgou.user.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.common.util.BCrypt;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.common.util.JwtUtil;
import com.changgou.user.pojo.User;
import com.changgou.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Demo User表现层
 *
 * @author lishiquan
 */
@Api("UserController")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    /**
     * user业务层接口
     */
    @Autowired
    private UserService userService;

    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @PostMapping("/login")
    public Result login(String username, String password, HttpServletResponse response) {
        // 查询用户信息
        User user = userService.login(username);
        // 对比密码
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // 创建用户令牌信息
            Map<String, Object> tokenMap = new HashMap<>();
            tokenMap.put("role", "USER");
            tokenMap.put("success", "SUCCESS");
            tokenMap.put("username", username);
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(tokenMap), null);
            // 把令牌信息存入到Cookie
            Cookie cookie = new Cookie("Authorization", token);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            // 把令牌作为参数给用户
            return new Result<>(true, StatusCode.OK, "登录成功", token);
        }
        return new Result<>(false, StatusCode.LOGINERROR, "账号或密码错误");
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/name/{username}")
    public Result<User> findByName(@PathVariable String username) {
        User user = userService.findByName(username);
        return new Result<>(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * 查询User全部数据
     * @return 查询成功
     */
    // @PreAuthorize("hasAnyRole('user')")
    @ApiOperation(value = "查询所有User", notes = "查询所User有方法详情", tags = {"UserController"})
    @GetMapping
    public Result<List<User>> findAll() {
        // 调用UserService实现查询所有User
        List<User> users = userService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", users);
    }

    /**
     * 根据ID查询User数据
     * @param id User id
     * @return 查询成功
     */
    @ApiOperation(value = "User根据ID查询", notes = "根据ID查询User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Integer id) {
        // 调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * 新增user数据
     * @param user User实体类
     * @return 添加成功
     */
    @ApiOperation(value = "User添加", notes = "添加User方法详情", tags = {"UserController"})
    @PostMapping("/add")
    public Result add(@RequestBody @ApiParam(name = "User对象", value = "传入JSON数据", required = true) User user) {
        // 将密码加密
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashpw);
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        user.setCreateTime(date);
        user.setUpdateTime(date);
        // 调用UserService实现添加User
        userService.add(user);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改User数据
     * @param user User实体类
     * @param id User id
     * @return 修改成功
     */
    @ApiOperation(value = "User根据ID修改", notes = "根据ID修改User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable Integer id) {
        // 设置主键值
        user.setId(id);
        // 调用userService实现修改User
        userService.update(user);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id User id
     * @return 删除成功
     */
    @PreAuthorize("hasAnyRole('admin')")
    @ApiOperation(value = "User根据ID删除", notes = "根据ID删除User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        // 调用UserService实现根据主键删除
        userService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param user 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "User条件查询", notes = "条件查询User方法详情", tags = {"UserController"})
    @PostMapping("/search")
    public Result<List<User>> findList(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user) {
        // 调用UserService实现条件查询User
        List<User> users = userService.findList(user);
        return new Result<>(true, StatusCode.OK, "查询成功", users);
    }

    /**
     * User分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "User分页查询", notes = "分页查询User方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<User>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用UserService实现分页查询User
        PageInfo<User> pageInfo = userService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * User分页条件搜索
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "User条件分页查询", notes = "分页条件查询User方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<User>> findPage(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable Integer page, @PathVariable Integer size) {
        //调用UserService实现分页条件查询User
        PageInfo<User> pageInfo = userService.findPage(user, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
