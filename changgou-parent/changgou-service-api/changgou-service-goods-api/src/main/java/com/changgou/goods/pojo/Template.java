package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Demo Template实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Template",value = "Template")
@Table(name="tb_template")
public class Template implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "模板名称",required = false)
    @Column(name = "name")
	private String name;//模板名称

	@ApiModelProperty(value = "规格数量",required = false)
    @Column(name = "spec_num")
	private Integer specNum;//规格数量

	@ApiModelProperty(value = "参数数量",required = false)
    @Column(name = "par_num")
	private Integer parNum;//参数数量



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
	public Integer getSpecNum() {
		return specNum;
	}

	//set方法
	public void setSpecNum(Integer specNum) {
		this.specNum = specNum;
	}
	//get方法
	public Integer getParNum() {
		return parNum;
	}

	//set方法
	public void setParNum(Integer parNum) {
		this.parNum = parNum;
	}


}
