/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * ed_video_accountEntity
 * @author Roger
 * @version 2018-11-26
 */
@Table(name="ed_video_account", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="vendor_id", attrName="vendorId", label="vendor_id"),
		@Column(name="account", attrName="account", label="会员身份"),
		@Column(name="password", attrName="password", label="账号密码"),
		@Column(name="category", attrName="category", label="会员类别"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="create_time", attrName="createTime", label="create_time"),
		@Column(name="update_time", attrName="updateTime", label="update_time"),
	}, orderBy="a.id DESC"
)
public class EdVideoAccount extends DataEntity<EdVideoAccount> {
	
	private static final long serialVersionUID = 1L;
	private Long vendorId;		// vendor_id
	private String account;		// 会员身份
	private String password;		// 账号密码
	private String category;		// 会员类别
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	
	public EdVideoAccount() {
		this(null);
	}

	public EdVideoAccount(String id){
		super(id);
	}
	
	@NotNull(message="vendor_id不能为空")
	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
	@NotBlank(message="会员身份不能为空")
	@Length(min=0, max=100, message="会员身份长度不能超过 100 个字符")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@NotBlank(message="账号密码不能为空")
	@Length(min=0, max=100, message="账号密码长度不能超过 100 个字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotBlank(message="会员类别不能为空")
	@Length(min=0, max=64, message="会员类别长度不能超过 64 个字符")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="update_time不能为空")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}