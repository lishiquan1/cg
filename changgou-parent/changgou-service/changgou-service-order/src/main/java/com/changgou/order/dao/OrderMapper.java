package com.changgou.order.dao;

import com.changgou.order.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

/**
 * Demo Order持久层接口
 *
 * @author lishiquan
 */
public interface OrderMapper extends Mapper<Order> {
    /**
     * 修改订单状态
     * @param outTradeNo 订单号
     * @param payTime 支付时间
     * @param transactionId 交易流水号
     */
    @Update("update tb_order set pay_status=1, pay_type=1, pay_time=#{payTime}, update_time=#{payTime}, transaction_id=#{transactionId} where order=#{outTradeNo}")
    void updateStatus(@Param("outTradeNo") String outTradeNo, @Param("payTime") Date payTime, @Param("transactionId") String transactionId);

    /**
     * 删除订单
     * @param outTradeNo 订单号
     * @param updateTime 修改时间
     */
    @Update("update tb_order set pay_status=2, update_time=#{updateTime}, order=#{outTradeNo}")
    void deleteOrder(@Param("outTradeNo") String outTradeNo, @Param("updateTime") Date updateTime);
}
