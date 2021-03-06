package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Demo Spec实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Spec",value = "Spec")
@Table(name="tb_spec")
public class Spec implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "规格名称",required = false)
    @Column(name = "name")
	private String name;//规格名称

	@ApiModelProperty(value = "规格参数",required = false)
    @Column(name = "options")
	private String options;//规格参数

	@ApiModelProperty(value = "排序",required = false)
    @Column(name = "seq")
	private Integer seq;//排序

	@ApiModelProperty(value = "模板id",required = false)
    @Column(name = "template_id")
	private Integer templateId;//模板id



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getOptions() {
		return options;
	}

	//set方法
	public void setOptions(String options) {
		this.options = options;
	}
	//get方法
	public Integer getSeq() {
		return seq;
	}

	//set方法
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	//get方法
	public Integer getTemplateId() {
		return templateId;
	}

	//set方法
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}


}
