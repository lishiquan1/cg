package com.changgou.order.service.impl;

import com.changgou.goods.feign.SkuFeign;
import com.changgou.order.dao.OrderItemMapper;
import com.changgou.order.dao.OrderMapper;
import com.changgou.order.pojo.Order;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.OrderService;
import com.changgou.user.feign.UserFeign;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Demo Order业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * Order持久层接口
     */
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserFeign userFeign;
    /**
     * 修改订单状态
     * @param outTradeNo 订单号
     * @param payTime 支付时间
     * @param transactionId 交易流水号
     */
    @Override
    public void updateStatus(String outTradeNo, String payTime, String transactionId) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换支付时间
        Date payTimeInfo = sdf.parse(payTime);
        orderMapper.updateStatus(outTradeNo, payTimeInfo, transactionId);
    }

    /**
     * 删除订单
     * @param outTradeNo 订单号
     */
    @Override
    public void deleteOrder(String outTradeNo) {
        orderMapper.deleteOrder(outTradeNo, new Date());
        // 回滚库存

    }

    /**
     * 查询Order全部数据
     * @return 查询结果
     */
    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }

    /**
     * 根据ID查询Order
     * @param id Order id
     * @return 查询结果
     */
    @Override
    public Order findById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Order
     * @param order Order实体类
     */
    @GlobalTransactional // 分布式事务注解
    // @Transactional // 本地事务
    @Override
    public void add(Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        // 获取勾选的商品
        for (Integer skuId : order.getSkuIds()) {
            // 将购物车中已下单的商品信息添加到集合中
            orderItems.add((OrderItem) redisTemplate.boundHashOps("Cart_" + order.getUserId()).get(skuId));
            // 删除已下单的商品
            redisTemplate.boundHashOps("Cart_" + order.getUserId()).delete(skuId);
        }
        // 总数量
        int totalNum = 0;
        // 总金额
        double totalMoney = 0;
        for (OrderItem orderItem : orderItems) {
            totalNum += orderItem.getNum();
            totalMoney += orderItem.getMoney();
        }
        // 订单数量
        order.setTotalNum(totalNum);
        // 订单总金额
        order.setTotalMoney(totalMoney);
        // 判断是否有优惠金额
        if (order.getPreMoney() != null && order.getPreMoney() > 0) {
            totalMoney -= order.getPreMoney();
        }
        // 实际支付金额
        order.setPayMoney(totalMoney);
        // 组成订单号 SH + 创建时间(精确到毫秒) + 六位随机数
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        order.setOrder("SH" + sdf.format(date) + (int) ((Math.random() * 9 + 1) * 100000));
        // 创建时间
        order.setCreateTime(date);
        // 修改时间
        order.setUpdateTime(date);
        // 添加订单信息
        orderMapper.insertSelective(order);
        Map<String, Integer> decrMap = new HashMap<>();
        // 添加订单明细
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
            orderItemMapper.insertSelective(orderItem);
            decrMap.put(String.valueOf(orderItem.getSkuId()), orderItem.getNum());
        }
        // 库存递减
        skuFeign.decrCount(decrMap);
        // 添加积分
        userFeign.addPoints((int) totalMoney);
        // 添加订单
        rabbitTemplate.convertAndSend("orderDelayMessage",  order.getOrder(), message -> {
            // 设置延时时间(30分钟)
            message.getMessageProperties().setExpiration("1800000");
            return message;
        });
    }

    /**
     * 修改Order
     * @param order Order实体类
     */
    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除
     * @param id Order id
     */
    @Override
    public void delete(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    /**
     * Order构建查询对象
     * @param order 查询条件
     * @return 条件构造器
     */
    public Example createExample(Order order) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if (order != null) {
            // 订单ID
            if (!StringUtils.isEmpty(order.getId())) {
                criteria.andEqualTo("id", order.getId());
            }
            // 订单ID
            if (!StringUtils.isEmpty(order.getOrder())) {
                criteria.andEqualTo("orderId", order.getOrder());
            }
            // 数量合计
            if (!StringUtils.isEmpty(order.getTotalNum())) {
                criteria.andEqualTo("totalNum", order.getTotalNum());
            }
            // 金额合计
            if (!StringUtils.isEmpty(order.getTotalMoney())) {
                criteria.andEqualTo("totalMoney", order.getTotalMoney());
            }
            // 优惠金额
            if (!StringUtils.isEmpty(order.getPreMoney())) {
                criteria.andEqualTo("preMoney", order.getPreMoney());
            }
            // 邮费
            if (!StringUtils.isEmpty(order.getPoetFee())) {
                criteria.andEqualTo("poetFee", order.getPoetFee());
            }
            // 实付金额
            if (!StringUtils.isEmpty(order.getPayMoney())) {
                criteria.andEqualTo("payMoney", order.getPayMoney());
            }
            // 支付类型: 1 在线支付, 2 货到付款
            if (!StringUtils.isEmpty(order.getPayType())) {
                criteria.andEqualTo("payType", order.getPayType());
            }
            // 订单创建时间
            if (!StringUtils.isEmpty(order.getCreateTime())) {
                criteria.andEqualTo("createTime", order.getCreateTime());
            }
            // 订单更新时间
            if (!StringUtils.isEmpty(order.getUpdateTime())) {
                criteria.andEqualTo("updateTime", order.getUpdateTime());
            }
            // 付款时间
            if (!StringUtils.isEmpty(order.getPayTime())) {
                criteria.andEqualTo("payTime", order.getPayTime());
            }
            // 发货时间
            if (!StringUtils.isEmpty(order.getConsignTime())) {
                criteria.andEqualTo("consignTime", order.getConsignTime());
            }
            // 交易完成时间
            if (!StringUtils.isEmpty(order.getEndTime())) {
                criteria.andEqualTo("endTime", order.getEndTime());
            }
            // 交易关闭时间
            if (!StringUtils.isEmpty(order.getCloseTime())) {
                criteria.andEqualTo("closeTime", order.getCloseTime());
            }
            // 物流名称
            if (!StringUtils.isEmpty(order.getShippingName())) {
                criteria.andEqualTo("shippingName", order.getShippingName());
            }
            // 物流单号
            if (!StringUtils.isEmpty(order.getShippingCode())) {
                criteria.andEqualTo("shippingCode", order.getShippingCode());
            }
            // 用户名称
            if (!StringUtils.isEmpty(order.getUserId())) {
                criteria.andLike("username", "%" + order.getUserId() + "%");
            }
            // 买家留言
            if (!StringUtils.isEmpty(order.getBuyerMessage())) {
                criteria.andEqualTo("buyerMessage", order.getBuyerMessage());
            }
            // 是否评价: 0 未评价, 1 已评价
            if (!StringUtils.isEmpty(order.getBuyerRate())) {
                criteria.andEqualTo("buyerRate", order.getBuyerRate());
            }
            // 收货人
            if (!StringUtils.isEmpty(order.getReceiverContact())) {
                criteria.andEqualTo("receiverContact", order.getReceiverContact());
            }
            // 收货人手机号
            if (!StringUtils.isEmpty(order.getReceiverMobile())) {
                criteria.andEqualTo("receiverMobile", order.getReceiverMobile());
            }
            // 收货人地址
            if (!StringUtils.isEmpty(order.getReceiverAddress())) {
                criteria.andEqualTo("receiverAddress", order.getReceiverAddress());
            }
            // 订单来源: 1 web, 2 IOS, 3 Android, 4 微信小程序, 5 H5
            if (!StringUtils.isEmpty(order.getSourceType())) {
                criteria.andEqualTo("sourceType", order.getSourceType());
            }
            // 交易流水号
            if (!StringUtils.isEmpty(order.getTransactionId())) {
                criteria.andEqualTo("transactionId", order.getTransactionId());
            }
            // 订单状态
            if (!StringUtils.isEmpty(order.getOrderStatus())) {
                criteria.andEqualTo("orderStatus", order.getOrderStatus());
            }
            // 支付状态
            if (!StringUtils.isEmpty(order.getPayStatus())) {
                criteria.andEqualTo("payStatus", order.getPayStatus());
            }
            // 发货状态
            if (!StringUtils.isEmpty(order.getConsignStatus())) {
                criteria.andEqualTo("consignStatus", order.getConsignStatus());
            }
            // 是否删除: 0 未删除, 1 已删除
            if (!StringUtils.isEmpty(order.getIsDelete())) {
                criteria.andEqualTo("isDelete", order.getIsDelete());
            }
        }
        return example;
    }

    /**
     * Order条件查询
     * @param order 查询条件
     * @return 查询结果
     */
    @Override
    public List<Order> findList(Order order) {
        // 构建查询条件
        Example example = createExample(order);
        // 根据构建的条件查询数据
        return orderMapper.selectByExample(example);
    }

    /**
     * Order分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Order> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(orderMapper.selectAll());
    }

    /**
     * Order条件 + 分页查询
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Order> findPage(Order order, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(order);
        // 执行搜索
        return new PageInfo<>(orderMapper.selectByExample(example));
    }

}
