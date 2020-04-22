package com.changgou.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo Address实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "Address",value = "Address")
@Table(name="tb_address")
public class Address implements Serializable{

	@ApiModelProperty("主键id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("用户id")
	private Integer userId;

	@ApiModelProperty("省")
	private String provinceId;

	@ApiModelProperty("市")
	private String cityId;

	@ApiModelProperty("区/县")
	private String areaId;

	@ApiModelProperty("详细地址")
	private String address;

	@ApiModelProperty("电话")
	private String phone;

	@ApiModelProperty("联系人")
	private String conract;

	@ApiModelProperty("是否默认: 1 是, 0否 ")
	private String isDefault;

	@ApiModelProperty("别名")
	private String aliac;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getConract() {
		return conract;
	}
	public void setConract(String conract) {
		this.conract = conract;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getAliac() {
		return aliac;
	}
	public void setAliac(String aliac) {
		this.aliac = aliac;
	}


}
