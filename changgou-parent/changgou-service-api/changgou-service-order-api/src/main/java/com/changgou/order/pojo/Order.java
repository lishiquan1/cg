package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
import java.util.List;

/**
 * Demo Order实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Order",value = "Order")
@Table(name="tb_order")
public class Order implements Serializable{

	@ApiModelProperty("订单ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("订单")
	private String order;

	@ApiModelProperty("数量合计")
	private Integer totalNum;

	@ApiModelProperty("金额合计")
	private Integer totalMoney;

	@ApiModelProperty("优惠金额")
	private Integer preMoney;

	@ApiModelProperty("邮费")
	private Integer poetFee;

	@ApiModelProperty("实付金额")
	private Integer payMoney;

	@ApiModelProperty("支付类型: 1 在线支付, 2 货到付款")
	private String payType;

	@ApiModelProperty("订单创建时间")
	private Date createTime;

	@ApiModelProperty("订单更新时间")
	private Date updateTime;

	@ApiModelProperty("付款时间")
	private Date payTime;

	@ApiModelProperty("发货时间")
	private Date consignTime;

	@ApiModelProperty("交易完成时间")
	private Date endTime;

	@ApiModelProperty("交易关闭时间")
	private Date closeTime;

	@ApiModelProperty("物流名称")
	private String shippingName;

	@ApiModelProperty("物流单号")
	private String shippingCode;

	@ApiModelProperty("用户名称")
	private Integer userId;

	@ApiModelProperty("买家留言")
	private String buyerMessage;

	@ApiModelProperty("是否评价: 0 未评价, 1 已评价")
	private String buyerRate;

	@ApiModelProperty("收货人")
	private String receiverContact;

	@ApiModelProperty("收货人手机号")
	private String receiverMobile;

	@ApiModelProperty("收货人地址")
	private String receiverAddress;

	@ApiModelProperty("订单来源: 1 web, 2 IOS, 3 Android, 4 微信小程序, 5 H5")
	private String sourceType;

	@ApiModelProperty("交易流水号")
	private String transactionId;

	@ApiModelProperty("订单状态")
	private String orderStatus;

	@ApiModelProperty("支付状态")
	private String payStatus;

	@ApiModelProperty("发货状态")
	private String consignStatus;

	@ApiModelProperty("是否删除: 0 未删除, 1 已删除")
	private String isDelete;

	@ApiModelProperty("订单的商品id")
	private List<Integer> skuIds;

	public List<Integer> getSkuIds() {
		return skuIds;
	}
	public void setSkuIds(List<Integer> skuIds) {
		this.skuIds = skuIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getPreMoney() {
		return preMoney;
	}
	public void setPreMoney(Integer preMoney) {
		this.preMoney = preMoney;
	}
	public Integer getPoetFee() {
		return poetFee;
	}
	public void setPoetFee(Integer poetFee) {
		this.poetFee = poetFee;
	}
	public Integer getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getConsignTime() {
		return consignTime;
	}
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingCode() {
		return shippingCode;
	}
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getBuyerRate() {
		return buyerRate;
	}
	public void setBuyerRate(String buyerRate) {
		this.buyerRate = buyerRate;
	}
	public String getReceiverContact() {
		return receiverContact;
	}
	public void setReceiverContact(String receiverContact) {
		this.receiverContact = receiverContact;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


}
