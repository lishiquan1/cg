package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo Preferential实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Preferential",value = "Preferential")
@Table(name="tb_preferential")
public class Preferential implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("消费金额")
	private Integer buyMoney;

	@ApiModelProperty("优惠金额")
	private Integer preMoney;

	@ApiModelProperty("分类ID")
	private Integer categoryId;

	@ApiModelProperty("活动开始时间")
	private Date startTime;

	@ApiModelProperty("活动结束时间")
	private Date endTime;

	@ApiModelProperty("状态")
	private String state;

	@ApiModelProperty("类型: 1 不翻倍, 2 翻倍")
	private String type;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Integer buyMoney) {
		this.buyMoney = buyMoney;
	}
	public Integer getPreMoney() {
		return preMoney;
	}
	public void setPreMoney(Integer preMoney) {
		this.preMoney = preMoney;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
