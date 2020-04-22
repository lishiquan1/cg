package com.changgou.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo Province实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Province",value = "Province")
@Table(name="tb_province")
public class Province implements Serializable{

	@ApiModelProperty("省份id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("省份名称")
	private String name;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
