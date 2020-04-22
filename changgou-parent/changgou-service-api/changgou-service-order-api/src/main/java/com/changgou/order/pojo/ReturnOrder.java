package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo ReturnOrder实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "ReturnOrder",value = "ReturnOrder")
@Table(name="tb_return_order")
public class ReturnOrder implements Serializable{

	@ApiModelProperty("服务单号")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("订单id")
	private Integer orderId;

	@ApiModelProperty("申请时间")
	private Date applyTime;

	@ApiModelProperty("用户id")
	private Integer userId;

	@ApiModelProperty("用户账号")
	private String userAccount;

	@ApiModelProperty("联系人")
	private String likeman;

	@ApiModelProperty("联系人手机号")
	private String likemanMobile;

	@ApiModelProperty("类型")
	private String type;

	@ApiModelProperty("退款金额")
	private Integer returnMoney;

	@ApiModelProperty("是否退运费")
	private String isReturnFreight;

	@ApiModelProperty("申请状态")
	private String status;

	@ApiModelProperty("处理时间")
	private Date disposeTime;

	@ApiModelProperty("退款原因")
	private Integer returnCause;

	@ApiModelProperty("凭证图片")
	private String evidence;

	@ApiModelProperty("问题描述")
	private String description;

	@ApiModelProperty("处理备注")
	private String remark;

	@ApiModelProperty("管理员id")
	private Integer adminId;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getLikeman() {
		return likeman;
	}
	public void setLikeman(String likeman) {
		this.likeman = likeman;
	}
	public String getLikemanMobile() {
		return likemanMobile;
	}
	public void setLikemanMobile(String likemanMobile) {
		this.likemanMobile = likemanMobile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(Integer returnMoney) {
		this.returnMoney = returnMoney;
	}
	public String getIsReturnFreight() {
		return isReturnFreight;
	}
	public void setIsReturnFreight(String isReturnFreight) {
		this.isReturnFreight = isReturnFreight;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDisposeTime() {
		return disposeTime;
	}
	public void setDisposeTime(Date disposeTime) {
		this.disposeTime = disposeTime;
	}
	public Integer getReturnCause() {
		return returnCause;
	}
	public void setReturnCause(Integer returnCause) {
		this.returnCause = returnCause;
	}
	public String getEvidence() {
		return evidence;
	}
	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


}
