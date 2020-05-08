package com.changgou.seckill.service.impl;

import com.changgou.common.entity.SeckillStatus;
import com.changgou.common.entity.StatusCode;
import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.dao.SeckillOrderMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.pojo.SeckillOrder;
import com.changgou.seckill.service.SeckillOrderService;
import com.changgou.seckill.task.MultiThreadingCreateOrder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Demo SeckillOrder业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    /**
     * SeckillOrder持久层接口
     */
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MultiThreadingCreateOrder multiThreadingCreateOrder;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    /**
     * 订单状态查询
     * @param id 用户id
     * @return 状态信息
     */
    @Override
    public SeckillStatus queryStatus(Integer id) {
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus").get(id);
        return seckillStatus;
    }

    /**
     * 删除订单
     * @param id 用户id
     */
    @Override
    public void deleteOrder(Integer id) {
        // 删除订单
        redisTemplate.boundHashOps("SeckilOrder").delete(id);
        // 查询用户排队信息
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus").get(id);
        // 删除排队信息
        clearUserQueue(id);
        // 回滚库存, Redis递增
        String nameSpace = "SeckillGoods_" + seckillStatus.getTime();
        // 查询秒杀商品
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(nameSpace).get(seckillStatus.getGoodsId());
        if (seckillGoods == null) {
            // 查询数据库
            seckillGoods = seckillGoodsMapper.selectByPrimaryKey(seckillStatus.getGoodsId());
            // 更新数据库库存
            seckillGoods.setStockCount(1);
            seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
        }else {
            seckillGoods.setStockCount(seckillGoods.getStockCount() + 1);
        }
        // 同步到Redis中
        redisTemplate.boundHashOps(nameSpace).put(seckillGoods.getId(), seckillGoods);
        // 增加队列数据
        redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillGoods.getId()).leftPush(seckillGoods.getId());
    }

    /**
     * 秒杀订单修改状态
     * @param id 用户id
     * @param transactionId 微信支付流水号
     * @param endTime 交易结束时间
     */
    @Override
    public void updatePayStatus(String id, String transactionId, String endTime) {
        // 查询订单
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("SeckillOrder").get(id);
        if (seckillOrder != null) {
            try {
                // 修改订单状态信息
                seckillOrder.setStatus("1");
                seckillOrder.setTransactionId(transactionId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date parseTimeInfo = sdf.parse(endTime);
                seckillOrder.setPayTime(parseTimeInfo);
                // 同步到数据库
                seckillOrderMapper.insertSelective(seckillOrder);
                // 删除Redis中的订单
                redisTemplate.boundHashOps("SeckilOrder").delete(id);
                // 删除用户排队信息
                this.clearUserQueue(Integer.valueOf(id));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 清除用户排队抢单信息
     * @param id 用户id
     */
    public void clearUserQueue(Integer id) {
        // 清除排队标识
        redisTemplate.boundHashOps("UserQueueCount").delete(id);
        // 清除抢单状态
        redisTemplate.boundHashOps("UserQueueStatus").delete(id);
    }

    /**
     * 秒杀下单排队
     * @param time 秒杀频道时间
     * @param id 商品id
     * @param userId 用户id
     */
    @Override
    public void add(String time, Integer id, Integer userId) {
        // 记录用户排队的次数
        Long count = redisTemplate.boundHashOps("UserQueueCount").increment(userId, 1);
        if (count > 1) {
            throw new RuntimeException(String.valueOf(StatusCode.REPERROR));
        }
        // 创建排队对象
        SeckillStatus seckillStatus = new SeckillStatus(userId, new Date(), 1, id, time);
        // 用户抢单排队, List是有序的队列类型
        redisTemplate.boundListOps("SeckillOrderQueue").leftPush(seckillStatus);
        // 用户抢单状态, 主要用户查询
        redisTemplate.boundHashOps("UserQueueStatus").put(userId, seckillStatus);
        // 异步执行
        multiThreadingCreateOrder.createOrder();
    }

    /**
     * 查询SeckillOrder全部数据
     * @return 查询结果
     */
    @Override
    public List<SeckillOrder> findAll() {
        return seckillOrderMapper.selectAll();
    }


    /**
     * 修改SeckillOrder
     * @param seckillOrder SeckillOrder实体类
     */
    @Override
    public void update(SeckillOrder seckillOrder) {
        seckillOrderMapper.updateByPrimaryKeySelective(seckillOrder);
    }

    /**
     * SeckillOrder构建查询对象
     * @param seckillOrder 查询条件
     * @return 条件构造器
     */
    public Example createExample(SeckillOrder seckillOrder) {
        Example example = new Example(SeckillOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if (seckillOrder != null) {
            // 主键
            if (!StringUtils.isEmpty(seckillOrder.getId())) {
                criteria.andEqualTo("id", seckillOrder.getId());
            }
            // 秒杀商品id
            if (!StringUtils.isEmpty(seckillOrder.getSeckillId())) {
                criteria.andEqualTo("seckillId", seckillOrder.getSeckillId());
            }
            // 支付金额
            if (!StringUtils.isEmpty(seckillOrder.getMoney())) {
                criteria.andEqualTo("money", seckillOrder.getMoney());
            }
            // 用户id
            if (!StringUtils.isEmpty(seckillOrder.getUserId())) {
                criteria.andEqualTo("userId", seckillOrder.getUserId());
            }
            // 创建时间
            if (!StringUtils.isEmpty(seckillOrder.getCreateTime())) {
                criteria.andEqualTo("createTime", seckillOrder.getCreateTime());
            }
            // 支付时间
            if (!StringUtils.isEmpty(seckillOrder.getPayTime())) {
                criteria.andEqualTo("payTime", seckillOrder.getPayTime());
            }
            // 状态: 0 未支付, 1 已支付
            if (!StringUtils.isEmpty(seckillOrder.getStatus())) {
                criteria.andEqualTo("status", seckillOrder.getStatus());
            }
            // 收货人地址
            if (!StringUtils.isEmpty(seckillOrder.getReceiverAddress())) {
                criteria.andEqualTo("receiverAddress", seckillOrder.getReceiverAddress());
            }
            // 收货人电话
            if (!StringUtils.isEmpty(seckillOrder.getReceiverMobile())) {
                criteria.andEqualTo("receiverMobile", seckillOrder.getReceiverMobile());
            }
            // 收货人
            if (!StringUtils.isEmpty(seckillOrder.getReceiver())) {
                criteria.andEqualTo("receiver", seckillOrder.getReceiver());
            }
            // 交易流水号
            if (!StringUtils.isEmpty(seckillOrder.getTransactionId())) {
                criteria.andEqualTo("transactionId", seckillOrder.getTransactionId());
            }
        }
        return example;
    }

    /**
     * SeckillOrder条件查询
     * @param seckillOrder 查询条件
     * @return 查询结果
     */
    @Override
    public List<SeckillOrder> findList(SeckillOrder seckillOrder) {
        // 构建查询条件
        Example example = createExample(seckillOrder);
        // 根据构建的条件查询数据
        return seckillOrderMapper.selectByExample(example);
    }

    /**
     * SeckillOrder分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SeckillOrder> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(seckillOrderMapper.selectAll());
    }

    /**
     * SeckillOrder条件 + 分页查询
     * @param seckillOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SeckillOrder> findPage(SeckillOrder seckillOrder, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(seckillOrder);
        // 执行搜索
        return new PageInfo<>(seckillOrderMapper.selectByExample(example));
    }


}
