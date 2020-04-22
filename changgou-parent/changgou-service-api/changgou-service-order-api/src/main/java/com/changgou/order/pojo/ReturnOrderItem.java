package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo ReturnOrderItem实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "ReturnOrderItem",value = "ReturnOrderItem")
@Table(name="tb_return_order_item")
public class ReturnOrderItem implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("分类id")
	private Integer categoryId;

	@ApiModelProperty("SPU_ID")
	private Integer spuId;

	@ApiModelProperty("SKU_ID")
	private Integer skuId;

	@ApiModelProperty("订单id")
	private Integer orderId;

	@ApiModelProperty("订单明细id")
	private Integer orderItemId;

	@ApiModelProperty("退货订单id")
	private Integer returnOrderId;

	@ApiModelProperty("标题")
	private String title;

	@ApiModelProperty("单价")
	private Integer price;

	@ApiModelProperty("数量")
	private Integer num;

	@ApiModelProperty("总金额")
	private Integer money;

	@ApiModelProperty("支付金额")
	private Integer payMoney;

	@ApiModelProperty("图片地址")
	private String image;

	@ApiModelProperty("重量")
	private Integer weight;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSpuId() {
		return spuId;
	}
	public void setSpuId(Integer spuId) {
		this.spuId = spuId;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getReturnOrderId() {
		return returnOrderId;
	}
	public void setReturnOrderId(Integer returnOrderId) {
		this.returnOrderId = returnOrderId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}


}
