package com.changgou.order.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo ReturnCause实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "ReturnCause",value = "ReturnCause")
@Table(name="tb_return_cause")
public class ReturnCause implements Serializable{

	@ApiModelProperty("ID")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("原因")
	private String cause;

	@ApiModelProperty("排序")
	private Integer seq;

	@ApiModelProperty("是否启用")
	private String status;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
