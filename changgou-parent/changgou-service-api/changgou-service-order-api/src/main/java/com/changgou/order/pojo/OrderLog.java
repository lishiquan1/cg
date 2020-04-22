package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo OrderLog实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "OrderLog",value = "OrderLog")
@Table(name="tb_order_log")
public class OrderLog implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("操作员")
	private String operater;

	@ApiModelProperty("操作时间")
	private Date operateTime;

	@ApiModelProperty("订单ID")
	private Integer orderId;

	@ApiModelProperty("订单状态")
	private String orderStatus;

	@ApiModelProperty("付款状态")
	private String payStatus;

	@ApiModelProperty("发货状态")
	private String consignStatus;

	@ApiModelProperty("备注")
	private String remarks;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getConsignStatus() {
		return consignStatus;
	}
	public void setConsignStatus(String consignStatus) {
		this.consignStatus = consignStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
