package com.changgou.seckill.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo SeckillGoods实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "SeckillGoods",value = "SeckillGoods")
@Table(name="tb_seckill_goods")
public class SeckillGoods implements Serializable{

	@ApiModelProperty("")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("spu id")
	private Integer spuId;

	@ApiModelProperty("sku id")
	private Integer skuId;

	@ApiModelProperty("标题")
	private String name;

	@ApiModelProperty("商品图片")
	private String smallPic;

	@ApiModelProperty("原价格")
	private Double price;

	@ApiModelProperty("秒杀价格")
	private Double costPrice;

	@ApiModelProperty("添加日期")
	private Date createTime;

	@ApiModelProperty("审核日期")
	private Date checkTime;

	@ApiModelProperty("审核状态: 0 未审核, 1 审核通过, 2审核不通过")
	private String status;

	@ApiModelProperty("开始时间")
	private Date startTime;

	@ApiModelProperty("结束时间")
	private Date endTime;

	@ApiModelProperty("秒杀商品数")
	private Integer num;

	@ApiModelProperty("剩余库存数")
	private Integer stockCount;

	@ApiModelProperty("描述")
	private String introduction;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


}
