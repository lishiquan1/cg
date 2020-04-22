package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo UndoLog实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "UndoLog",value = "UndoLog")
@Table(name="undo_log")
public class UndoLog implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("分支id")
	private Integer branchId;

	@ApiModelProperty("")
	private String xid;

	@ApiModelProperty("回滚")
	private String rollbackInfo;

	@ApiModelProperty("创建时间")
	private Date logCreated;

	@ApiModelProperty("")
	private Date logModified;

	@ApiModelProperty("")
	private String ext;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getXid() {
		return xid;
	}
	public void setXid(String xid) {
		this.xid = xid;
	}
	public String getRollbackInfo() {
		return rollbackInfo;
	}
	public void setRollbackInfo(String rollbackInfo) {
		this.rollbackInfo = rollbackInfo;
	}
	public Date getLogCreated() {
		return logCreated;
	}
	public void setLogCreated(Date logCreated) {
		this.logCreated = logCreated;
	}
	public Date getLogModified() {
		return logModified;
	}
	public void setLogModified(Date logModified) {
		this.logModified = logModified;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}


}
