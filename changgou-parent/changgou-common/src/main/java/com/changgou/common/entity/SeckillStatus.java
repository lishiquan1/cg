package com.changgou.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Demo 用户排队抢单信息封装
 *
 * @author lishiquan
 * @date 2020/4/27 2:10 下午
 */
public class SeckillStatus implements Serializable {
    /**
     * 秒杀用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 秒杀状态:
     *   1.排队中
     *   2.秒杀等待支付
     *   3.支付超时
     *   4.支付失败
     *   5.支付完成
     */
    private Integer status;
    /**
     * 秒杀的商品id, 用于订单库存回滚
     */
    private Integer goodsId;
    /**
     * 应付金额
     */
    private Double money;
    /**
     * 订单号
     */
    private String order;
    /**
     * 时间段
     */
    private String time;

    public SeckillStatus() {
    }

    public SeckillStatus(Integer userId, Date createTime, Integer status, Integer goodsId, String time) {
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
        this.goodsId = goodsId;
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
