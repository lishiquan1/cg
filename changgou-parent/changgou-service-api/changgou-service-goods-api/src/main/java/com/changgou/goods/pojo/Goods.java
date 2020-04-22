package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Demo Goods实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Goods",value = "商品组合类")
public class Goods implements Serializable{

	@ApiModelProperty(value = "spu",required = false)
	@Id
	private Spu spu;

	@ApiModelProperty(value = "sku集合",required = false)
	private List<Sku> skuList;



	//get方法
	public Spu getSpu() {
		return spu;
	}

	//set方法
	public void setSpu(Spu spu) {
		this.spu = spu;
	}
	//get方法
	public List<Sku> getSkuList() {
		return skuList;
	}

	//set方法
	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}


}
