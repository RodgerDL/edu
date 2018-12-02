/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 会议预定表Entity
 * @author Roger
 * @version 2018-12-02
 */
@Table(name="ed_meeting", alias="a", columns={
		@Column(name="meeting_code", attrName="meetingCode.scheduleCode", label="会议编号", isPK=true),
		@Column(name="count", attrName="count", label="参加人数"),
		@Column(name="invite_code", attrName="inviteCode", label="邀请码"),
		@Column(name="plan_start_time", attrName="planStartTime", label="计划开始时间"),
		@Column(name="plan_end_time", attrName="planEndTime", label="计划结束时间"),
		@Column(name="actual_start_time", attrName="actualStartTime", label="实际开始时间"),
		@Column(name="actual_end_time", attrName="actualEndTime", label="实际结束时间"),
		@Column(name="category", attrName="category", label="会议类别"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="deleted", attrName="deleted", label="是否删除"),
	}, orderBy="a.create_date ASC"
)
public class EdMeeting extends DataEntity<EdMeeting> {
	
	private static final long serialVersionUID = 1L;
	private EdMeetingSchedule meetingCode;		// 会议编号 父类
	private String count;		// 参加人数
	private String inviteCode;		// 邀请码
	private Date planStartTime;		// 计划开始时间
	private Date planEndTime;		// 计划结束时间
	private Date actualStartTime;		// 实际开始时间
	private Date actualEndTime;		// 实际结束时间
	private String category;		// 会议类别
	private Integer deleted;		// 是否删除
	
	public EdMeeting() {
		this(null);
	}


	public EdMeeting(EdMeetingSchedule meetingCode){
		this.meetingCode = meetingCode;
	}
	
	public EdMeetingSchedule getMeetingCode() {
		return meetingCode;
	}

	public void setMeetingCode(EdMeetingSchedule meetingCode) {
		this.meetingCode = meetingCode;
	}
	
	@NotBlank(message="参加人数不能为空")
	@Length(min=0, max=64, message="参加人数长度不能超过 64 个字符")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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
	
	@NotNull(message="是否删除不能为空")
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
}