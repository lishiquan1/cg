package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Demo Pref实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Pref",value = "Pref")
@Table(name="tb_pref")
public class Pref implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "分类id",required = false)
    @Column(name = "category_id")
	private Integer categoryId;//分类id

	@ApiModelProperty(value = "消费金额",required = false)
    @Column(name = "buy_money")
	private Double buyMoney;//消费金额

	@ApiModelProperty(value = "优惠金额",required = false)
    @Column(name = "per_money")
	private Double perMoney;//优惠金额

	@ApiModelProperty(value = "获取开始日期",required = false)
    @Column(name = "start_time")
	private Date startTime;//获取开始日期

	@ApiModelProperty(value = "活动结束日期",required = false)
    @Column(name = "end_time")
	private Date endTime;//活动结束日期

	@ApiModelProperty(value = "类型",required = false)
    @Column(name = "type")
	private String type;//类型

	@ApiModelProperty(value = "状态",required = false)
    @Column(name = "state")
	private String state;//状态



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public Integer getCategoryId() {
		return categoryId;
	}

	//set方法
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	//get方法
	public Double getBuyMoney() {
		return buyMoney;
	}

	//set方法
	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
	}
	//get方法
	public Double getPerMoney() {
		return perMoney;
	}

	//set方法
	public void setPerMoney(Double perMoney) {
		this.perMoney = perMoney;
	}
	//get方法
	public Date getStartTime() {
		return startTime;
	}

	//set方法
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	//get方法
	public Date getEndTime() {
		return endTime;
	}

	//set方法
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	//get方法
	public String getType() {
		return type;
	}

	//set方法
	public void setType(String type) {
		this.type = type;
	}
	//get方法
	public String getState() {
		return state;
	}

	//set方法
	public void setState(String state) {
		this.state = state;
	}


}
