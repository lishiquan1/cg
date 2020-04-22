package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Demo Album实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Album",value = "Album")
@Table(name="tb_album")
public class Album implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "相册名称",required = false)
    @Column(name = "title")
	private String title;

	@ApiModelProperty(value = "相册封面",required = false)
    @Column(name = "image")
	private String image;

	@ApiModelProperty(value = "图片列表",required = false)
    @Column(name = "image_items")
	private String imageItems;



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getTitle() {
		return title;
	}

	//set方法
	public void setTitle(String title) {
		this.title = title;
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
	public String getImageItems() {
		return imageItems;
	}

	//set方法
	public void setImageItems(String imageItems) {
		this.imageItems = imageItems;
	}


}
