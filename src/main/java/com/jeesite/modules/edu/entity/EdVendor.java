/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 供应商表Entity
 * @author Roger
 * @version 2018-12-04
 */
@Table(name="ed_vendor", alias="a", columns={
		@Column(name="vender_code", attrName="venderCode", label="供应商编号", isPK=true),
		@Column(name="name", attrName="name", label="供应商名称", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class EdVendor extends DataEntity<EdVendor> {
	
	private static final long serialVersionUID = 1L;
	private String venderCode;		// 供应商编号
	private String name;		// 供应商名称
	private List<EdAccount> edAccountList = ListUtils.newArrayList();		// 子表列表
	
	public EdVendor() {
		this(null);
	}

	public EdVendor(String id){
		super(id);
	}
	
	public String getVenderCode() {
		return venderCode;
	}

	public void setVenderCode(String venderCode) {
		this.venderCode = venderCode;
	}
	
	@NotBlank(message="供应商名称不能为空")
	@Length(min=0, max=100, message="供应商名称长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<EdAccount> getEdAccountList() {
		return edAccountList;
	}

	public void setEdAccountList(List<EdAccount> edAccountList) {
		this.edAccountList = edAccountList;
	}
	
}