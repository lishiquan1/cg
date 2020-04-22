package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Demo CategoryBrand实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "CategoryBrand",value = "CategoryBrand")
@Table(name="tb_category_brand")
public class CategoryBrand implements Serializable{

	@ApiModelProperty(value = "分类id",required = false)
    @Column(name = "category_id")
    @Id
    private Integer categoryId;

	@ApiModelProperty(value = "品牌id",required = false)
    @Column(name = "brand_id")
    @Id
	private Integer brandId;



	//get方法
	public Integer getCategoryId() {
		return categoryId;
	}

	//set方法
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	//get方法
	public Integer getBrandId() {
		return brandId;
	}

	//set方法
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}


}
