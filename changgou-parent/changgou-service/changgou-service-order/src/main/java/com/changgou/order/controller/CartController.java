package com.changgou.order.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.common.util.TokenDecode;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/19 10:36 上午
 */
@Api("CartController")
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 加入购物车
     * @param id 规格id
     * @param num 数量
     * @return 添加购物车成功
     */
    @ApiOperation(value = "添加购物车", notes = "添加购物车", tags = {"CartController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "id", name = "id", value = "商品规格id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "num", name = "num", value = "商品数量", required = true, dataType = "Integer")
    })
    @GetMapping("/add")
    public Result add(Integer id, Integer num) {
        Map<String, Object> userInfo = TokenDecode.getUserInfo();
        Integer userId = (Integer) userInfo.get("id");
        cartService.add(id, num, userId);
        return new Result(true, StatusCode.OK, "加入购物车成功");
    }

    /**
     * 查询购物车
     * @return 查询购物车成功
     */
    @ApiOperation(value = "查询购物车", notes = "查询购物车", tags = {"CartController"})
    @GetMapping("/list")
    public Result<List<OrderItem>> list() {
        // 获取用户id
        Map<String, Object> userInfo = TokenDecode.getUserInfo();
        Integer userId = (Integer) userInfo.get("id");
        List<OrderItem> items = cartService.list(userId);
        return new Result<>(true, StatusCode.OK, "查询购物车成功", items);
    }
}
