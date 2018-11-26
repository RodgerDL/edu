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
 * ed_meetingEntity
 * @author Roger
 * @version 2018-11-26
 */
@Table(name="ed_meeting", alias="a", columns={
		@Column(name="id", attrName="id", label="会议Id", isPK=true),
		@Column(name="number", attrName="number", label="人数"),
		@Column(name="invite_code", attrName="inviteCode", label="邀请码"),
		@Column(name="plan_start_time", attrName="planStartTime", label="计划开始时间"),
		@Column(name="plan_end_time", attrName="planEndTime", label="计划结束时间"),
		@Column(name="actual_start_time", attrName="actualStartTime", label="实际开始时间"),
		@Column(name="actual_end_time", attrName="actualEndTime", label="实际结束时间"),
		@Column(name="category", attrName="category", label="会议类别"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="create_time", attrName="createTime", label="create_time"),
		@Column(name="update_time", attrName="updateTime", label="update_time"),
	}, orderBy="a.id DESC"
)
public class EdMeeting extends DataEntity<EdMeeting> {
	
	private static final long serialVersionUID = 1L;
	private Long number;		// 人数
	private String inviteCode;		// 邀请码
	private Date planStartTime;		// 计划开始时间
	private Date planEndTime;		// 计划结束时间
	private Date actualStartTime;		// 实际开始时间
	private Date actualEndTime;		// 实际结束时间
	private String category;		// 会议类别
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	
	public EdMeeting() {
		this(null);
	}

	public EdMeeting(String id){
		super(id);
	}
	
	@NotNull(message="人数不能为空")
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	@NotBlank(message="邀请码不能为空")
	@Length(min=0, max=100, message="邀请码长度不能超过 100 个字符")
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	
	@NotBlank(message="会议类别不能为空")
	@Length(min=0, max=64, message="会议类别长度不能超过 64 个字符")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}