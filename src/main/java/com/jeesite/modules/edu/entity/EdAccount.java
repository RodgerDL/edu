/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 供应商表Entity
 * @author Roger
 * @version 2018-12-04
 */
@Table(name="ed_account", alias="a", columns={
		@Column(name="acount_code", attrName="acountCode", label="账号编号", isPK=true),
		@Column(name="vendor_code", attrName="vendorCode.venderCode", label="供应商编号"),
		@Column(name="name", attrName="name", label="账号名称", queryType=QueryType.LIKE),
		@Column(name="password", attrName="password", label="账号密码"),
		@Column(name="category", attrName="category", label="账号类别"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class EdAccount extends DataEntity<EdAccount> {
	
	private static final long serialVersionUID = 1L;
	private String acountCode;		// 账号编号
	private EdVendor vendorCode;		// 供应商编号 父类
	private String name;		// 账号名称
	private String password;		// 账号密码
	private String category;		// 账号类别
	
	public EdAccount() {
		this(null);
	}


	public EdAccount(EdVendor vendorCode){
		this.vendorCode = vendorCode;
	}
	
	public String getAcountCode() {
		return acountCode;
	}

	public void setAcountCode(String acountCode) {
		this.acountCode = acountCode;
	}
	
	@NotBlank(message="供应商编号不能为空")
	@Length(min=0, max=64, message="供应商编号长度不能超过 64 个字符")
	public EdVendor getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(EdVendor vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	@NotBlank(message="账号名称不能为空")
	@Length(min=0, max=100, message="账号名称长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="账号密码不能为空")
	@Length(min=0, max=100, message="账号密码长度不能超过 100 个字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotBlank(message="账号类别不能为空")
	@Length(min=0, max=64, message="账号类别长度不能超过 64 个字符")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}