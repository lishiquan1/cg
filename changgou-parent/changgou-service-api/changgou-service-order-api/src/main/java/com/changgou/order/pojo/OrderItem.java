package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Demo OrderItem实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "OrderItem",value = "OrderItem")
@Table(name="tb_order_item")
public class OrderItem implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("1级分类")
	private Integer categoryId1;

	@ApiModelProperty("2级分类")
	private Integer categoryId2;

	@ApiModelProperty("3级分类")
	private Integer categoryId3;

	@ApiModelProperty("SPU_ID")
	private Integer supId;

	@ApiModelProperty("SKU_ID")
	private Integer skuId;

	@ApiModelProperty("订单ID")
	private Integer orderId;

	@ApiModelProperty("商品名称")
	private String name;

	@ApiModelProperty("单价")
	private Integer price;

	@ApiModelProperty("数量")
	private Integer num;

	@ApiModelProperty("总金额")
	private Integer money;

	@ApiModelProperty("实付金额")
	private Integer payMoney;

	@ApiModelProperty("图片地址")
	private String image;

	@ApiModelProperty("重量")
	private Integer weight;

	@ApiModelProperty("运费")
	private Integer postFee;

	@ApiModelProperty("是否退后")
	private String isReturn;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId1() {
		return categoryId1;
	}
	public void setCategoryId1(Integer categoryId1) {
		this.categoryId1 = categoryId1;
	}
	public Integer getCategoryId2() {
		return categoryId2;
	}
	public void setCategoryId2(Integer categoryId2) {
		this.categoryId2 = categoryId2;
	}
	public Integer getCategoryId3() {
		return categoryId3;
	}
	public void setCategoryId3(Integer categoryId3) {
		this.categoryId3 = categoryId3;
	}
	public Integer getSupId() {
		return supId;
	}
	public void setSupId(Integer supId) {
		this.supId = supId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getPostFee() {
		return postFee;
	}
	public void setPostFee(Integer postFee) {
		this.postFee = postFee;
	}
	public String getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}


}
