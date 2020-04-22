package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Demo Brand实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Brand",value = "Brand")
@Table(name="tb_brand")
public class Brand implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

	@ApiModelProperty(value = "品牌名称",required = false)
    @Column(name = "name")
	private String name;//品牌名称

	@ApiModelProperty(value = "品牌图片",required = false)
    @Column(name = "image")
	private String image;//品牌图片

	@ApiModelProperty(value = "品牌首字母",required = false)
    @Column(name = "letter")
	private String letter;//品牌首字母

	@ApiModelProperty(value = "品牌排序",required = false)
    @Column(name = "seq")
	private Integer seq;//品牌排序



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
	public String getImage() {
		return image;
	}

	//set方法
	public void setImage(String image) {
		this.image = image;
	}
	//get方法
	public String getLetter() {
		return letter;
	}

	//set方法
	public void setLetter(String letter) {
		this.letter = letter;
	}
	//get方法
	public Integer getSeq() {
		return seq;
	}

	//set方法
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


}
