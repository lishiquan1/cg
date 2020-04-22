package com.changgou.search.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.service.SkuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Demo 搜索
 *
 * @author lishiquan
 * @date 2020/4/10 12:00 下午
 */
@RestController
@RequestMapping("/search")
@CrossOrigin
public class SkuController {
    @Autowired
    private SkuService skuService;

    /**
     * 搜索商品
     * @param searchMap 搜索条件
     * @return 查询结果集
     */
    @ApiOperation(value = "ES搜索商品",notes = "ES条件搜索商品",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "param", name = "searchMap", value = "条件集合", required = true, dataType = "Map")
    @GetMapping
    public Map<String, Object> search(@RequestParam(required = false) Map<String, String> searchMap) {
        return skuService.search(searchMap);
    }

    /**
     * 导入所有sku到ES
     * @return 导入成功
     */
    @ApiOperation(value = "导入所有Sku", notes = "将Sku导入到ES中", tags = {"SkuController"})
    @GetMapping("/import")
    public Result impostData() {
        // 导入条件status=1
        Sku sku = new Sku();
        sku.setStatus("1");
        skuService.importData(sku);
        return new Result<>(true, StatusCode.OK, "导入成功");
    }
}
