package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Integer;
/**
 * Demo OrderConfig实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "OrderConfig",value = "OrderConfig")
@Table(name="tb_order_config")
public class OrderConfig implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("正常订单超时时间")
	private Integer orderTimeout;

	@ApiModelProperty("秒杀订单超时时间")
	private Integer seckillTimeout;

	@ApiModelProperty("自动收货")
	private Integer takeTimeout;

	@ApiModelProperty("售后期限")
	private Integer serviceTimeout;

	@ApiModelProperty("自动五星好评")
	private Integer commentTimeout;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderTimeout() {
		return orderTimeout;
	}
	public void setOrderTimeout(Integer orderTimeout) {
		this.orderTimeout = orderTimeout;
	}
	public Integer getSeckillTimeout() {
		return seckillTimeout;
	}
	public void setSeckillTimeout(Integer seckillTimeout) {
		this.seckillTimeout = seckillTimeout;
	}
	public Integer getTakeTimeout() {
		return takeTimeout;
	}
	public void setTakeTimeout(Integer takeTimeout) {
		this.takeTimeout = takeTimeout;
	}
	public Integer getServiceTimeout() {
		return serviceTimeout;
	}
	public void setServiceTimeout(Integer serviceTimeout) {
		this.serviceTimeout = serviceTimeout;
	}
	public Integer getCommentTimeout() {
		return commentTimeout;
	}
	public void setCommentTimeout(Integer commentTimeout) {
		this.commentTimeout = commentTimeout;
	}


}
