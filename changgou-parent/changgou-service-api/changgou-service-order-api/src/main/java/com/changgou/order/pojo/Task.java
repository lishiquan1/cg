package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo Task实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Task",value = "Task")
@Table(name="tb_task")
public class Task implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("创建时间")
	private Date createTime;

	@ApiModelProperty("修改时间")
	private Date updateTime;

	@ApiModelProperty("删除时间")
	private Date deleteTime;

	@ApiModelProperty("任务类型")
	private String taskType;

	@ApiModelProperty("交换机")
	private String mqExchange;

	@ApiModelProperty("路由键")
	private String mqRoutingkey;

	@ApiModelProperty("消息体")
	private String requestBody;

	@ApiModelProperty("状态")
	private String status;

	@ApiModelProperty("错误信息")
	private String errormsg;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getMqExchange() {
		return mqExchange;
	}
	public void setMqExchange(String mqExchange) {
		this.mqExchange = mqExchange;
	}
	public String getMqRoutingkey() {
		return mqRoutingkey;
	}
	public void setMqRoutingkey(String mqRoutingkey) {
		this.mqRoutingkey = mqRoutingkey;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}


}
