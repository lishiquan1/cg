package com.changgou.seckill.service.impl;

import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.service.SeckillGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo SeckillGoods业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    /**
     * SeckillGoods持久层接口
     */
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据时间区间查询秒杀商品频道列表数据
     * @param time 秒杀频道时间
     * @return 秒杀商品列表
     */
    @Override
    public List<SeckillGoods> list(String time) {
        List<SeckillGoods> seckillGoodsList = redisTemplate.boundHashOps("SeckillGoods_" + time).values();
        return seckillGoodsList;
    }

    /**
     * 查询秒杀商品详情
     * @param time 秒杀频道时间
     * @param id 商品id
     * @return 商品详情
     */
    @Override
    public SeckillGoods item(String time, Integer id) {
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps("SeckillGoods_" + time).get(id);
        return seckillGoods;
    }

    /**
     * 查询SeckillGoods全部数据
     * @return 查询结果
     */
    @Override
    public List<SeckillGoods> findAll() {
        return seckillGoodsMapper.selectAll();
    }

    /**
     * 根据ID查询SeckillGoods
     * @param id SeckillGoods id
     * @return 查询结果
     */
    @Override
    public SeckillGoods findById(Integer id) {
        return seckillGoodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加SeckillGoods
     * @param seckillGoods SeckillGoods实体类
     */
    @Override
    public void add(SeckillGoods seckillGoods) {
        seckillGoodsMapper.insertSelective(seckillGoods);
    }

    /**
     * 修改SeckillGoods
     * @param seckillGoods SeckillGoods实体类
     */
    @Override
    public void update(SeckillGoods seckillGoods) {
        seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
    }

    /**
     * 删除
     * @param id SeckillGoods id
     */
    @Override
    public void delete(Integer id) {
        seckillGoodsMapper.deleteByPrimaryKey(id);
    }

    /**
     * SeckillGoods构建查询对象
     * @param seckillGoods 查询条件
     * @return 条件构造器
     */
    public Example createExample(SeckillGoods seckillGoods) {
        Example example = new Example(SeckillGoods.class);
        Example.Criteria criteria = example.createCriteria();
        if (seckillGoods != null) {
            // 
            if (!StringUtils.isEmpty(seckillGoods.getId())) {
                criteria.andEqualTo("id", seckillGoods.getId());
            }
            // spu id
            if (!StringUtils.isEmpty(seckillGoods.getSpuId())) {
                criteria.andEqualTo("spuId", seckillGoods.getSpuId());
            }
            // sku id
            if (!StringUtils.isEmpty(seckillGoods.getSkuId())) {
                criteria.andEqualTo("skuId", seckillGoods.getSkuId());
            }
            // 标题
            if (!StringUtils.isEmpty(seckillGoods.getName())) {
                criteria.andLike("name", "%" + seckillGoods.getName() + "%");
            }
            // 商品图片
            if (!StringUtils.isEmpty(seckillGoods.getSmallPic())) {
                criteria.andEqualTo("smallPic", seckillGoods.getSmallPic());
            }
            // 原价格
            if (!StringUtils.isEmpty(seckillGoods.getPrice())) {
                criteria.andEqualTo("price", seckillGoods.getPrice());
            }
            // 秒杀价格
            if (!StringUtils.isEmpty(seckillGoods.getCostPrice())) {
                criteria.andEqualTo("costPrice", seckillGoods.getCostPrice());
            }
            // 添加日期
            if (!StringUtils.isEmpty(seckillGoods.getCreateTime())) {
                criteria.andEqualTo("createTime", seckillGoods.getCreateTime());
            }
            // 审核日期
            if (!StringUtils.isEmpty(seckillGoods.getCheckTime())) {
                criteria.andEqualTo("checkTime", seckillGoods.getCheckTime());
            }
            // 审核状态: 0 未审核, 1 审核通过, 2审核不通过
            if (!StringUtils.isEmpty(seckillGoods.getStatus())) {
                criteria.andEqualTo("status", seckillGoods.getStatus());
            }
            // 开始时间
            if (!StringUtils.isEmpty(seckillGoods.getStartTime())) {
                criteria.andEqualTo("startTime", seckillGoods.getStartTime());
            }
            // 结束时间
            if (!StringUtils.isEmpty(seckillGoods.getEndTime())) {
                criteria.andEqualTo("endTime", seckillGoods.getEndTime());
            }
            // 秒杀商品数
            if (!StringUtils.isEmpty(seckillGoods.getNum())) {
                criteria.andEqualTo("num", seckillGoods.getNum());
            }
            // 剩余库存数
            if (!StringUtils.isEmpty(seckillGoods.getStockCount())) {
                criteria.andEqualTo("stockCount", seckillGoods.getStockCount());
            }
            // 描述
            if (!StringUtils.isEmpty(seckillGoods.getIntroduction())) {
                criteria.andEqualTo("introduction", seckillGoods.getIntroduction());
            }
        }
        return example;
    }

    /**
     * SeckillGoods条件查询
     * @param seckillGoods 查询条件
     * @return 查询结果
     */
    @Override
    public List<SeckillGoods> findList(SeckillGoods seckillGoods) {
        // 构建查询条件
        Example example = createExample(seckillGoods);
        // 根据构建的条件查询数据
        return seckillGoodsMapper.selectByExample(example);
    }

    /**
     * SeckillGoods分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SeckillGoods> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(seckillGoodsMapper.selectAll());
    }

    /**
     * SeckillGoods条件 + 分页查询
     * @param seckillGoods 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SeckillGoods> findPage(SeckillGoods seckillGoods, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(seckillGoods);
        // 执行搜索
        return new PageInfo<>(seckillGoodsMapper.selectByExample(example));
    }

}
