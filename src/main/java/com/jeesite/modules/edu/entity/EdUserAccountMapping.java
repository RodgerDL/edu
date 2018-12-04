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
 * 会议表Entity
 * @author Roger
 * @version 2018-12-04
 */
@Table(name="ed_user_account_mapping", alias="a", columns={
		@Column(name="mapping_code", attrName="mappingCode", label="学员视频账号关系表", isPK=true),
		@Column(name="meeting_code", attrName="meetingCode.meetingCode", label="会议编号"),
		@Column(name="user_code", attrName="userCode", label="学员编号"),
		@Column(name="account_code", attrName="accountCode", label="视频账号编号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class EdUserAccountMapping extends DataEntity<EdUserAccountMapping> {
	
	private static final long serialVersionUID = 1L;
	private String mappingCode;		// 学员视频账号关系表
	private EdMeeting meetingCode;		// 会议编号 父类
	private String userCode;		// 学员编号
	private String accountCode;		// 视频账号编号
	
	public EdUserAccountMapping() {
		this(null);
	}


	public EdUserAccountMapping(EdMeeting meetingCode){
		this.meetingCode = meetingCode;
	}
	
	public String getMappingCode() {
		return mappingCode;
	}

	public void setMappingCode(String mappingCode) {
		this.mappingCode = mappingCode;
	}
	
	@NotBlank(message="会议编号不能为空")
	@Length(min=0, max=64, message="会议编号长度不能超过 64 个字符")
	public EdMeeting getMeetingCode() {
		return meetingCode;
	}

	public void setMeetingCode(EdMeeting meetingCode) {
		this.meetingCode = meetingCode;
	}
	
	@NotBlank(message="学员编号不能为空")
	@Length(min=0, max=64, message="学员编号长度不能超过 64 个字符")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@NotBlank(message="视频账号编号不能为空")
	@Length(min=0, max=64, message="视频账号编号长度不能超过 64 个字符")
	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
}