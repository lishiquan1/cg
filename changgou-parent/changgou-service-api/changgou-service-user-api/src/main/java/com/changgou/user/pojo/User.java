package com.changgou.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/**
 * Demo User实体类
 *
 * @author lishiquan
 */
@ApiModel(description = "User",value = "User")
@Table(name="tb_user")
public class User implements Serializable{

	@ApiModelProperty("用户id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("密码, 加密存储")
	private String password;

	@ApiModelProperty("注册手机号")
	private String phone;

	@ApiModelProperty("注册邮箱")
	private String email;

	@ApiModelProperty("创建时间")
	private Date createTime;

	@ApiModelProperty("修改时间")
	private Date updateTime;

	@ApiModelProperty("会员来源: 1 PC, 2 H5, 3 Android, 4 IOS")
	private String sourceType;

	@ApiModelProperty("昵称")
	private String nickName;

	@ApiModelProperty("真实姓名")
	private String name;

	@ApiModelProperty("使用状态: 1 正常, 0 非正常")
	private String status;

	@ApiModelProperty("头像地址")
	private String headPic;

	@ApiModelProperty("QQ号码")
	private String qq;

	@ApiModelProperty("手机是否验证: 0 否, 1 是")
	private String isMobileCheck;

	@ApiModelProperty("邮箱是否验证: 0 否, 1 是")
	private String isEmailCheck;

	@ApiModelProperty("性别: 1 男, 0 女")
	private String sex;

	@ApiModelProperty("会员等级")
	private Integer userLevel;

	@ApiModelProperty("积分")
	private Integer points;

	@ApiModelProperty("生日")
	private Date birthday;

	@ApiModelProperty("上次登录时间")
	private Date lastLoginTime;

	@ApiModelProperty("权限")
	private String permissions;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getIsMobileCheck() {
		return isMobileCheck;
	}
	public void setIsMobileCheck(String isMobileCheck) {
		this.isMobileCheck = isMobileCheck;
	}
	public String getIsEmailCheck() {
		return isEmailCheck;
	}
	public void setIsEmailCheck(String isEmailCheck) {
		this.isEmailCheck = isEmailCheck;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

}
