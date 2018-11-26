/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * ed_meeting_scheduleEntity
 * @author Roger
 * @version 2018-11-26
 */
@Table(name="ed_meeting_schedule", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="实际结束时间"),
		@Column(name="meeting_id", attrName="meetingId", label="人数"),
		@Column(name="video_account_id", attrName="videoAccountId", label="实际开始时间"),
		@Column(name="schedule_start_time", attrName="scheduleStartTime", label="计划开始时间"),
		@Column(name="schedule_end_time", attrName="scheduleEndTime", label="计划结束时间"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="create_time", attrName="createTime", label="create_time"),
		@Column(name="update_time", attrName="updateTime", label="update_time"),
	}, orderBy="a.id DESC"
)
public class EdMeetingSchedule extends DataEntity<EdMeetingSchedule> {
	
	private static final long serialVersionUID = 1L;
	private Long userId;		// 实际结束时间
	private Long meetingId;		// 人数
	private Long videoAccountId;		// 实际开始时间
	private Date scheduleStartTime;		// 计划开始时间
	private Date scheduleEndTime;		// 计划结束时间
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	
	public EdMeetingSchedule() {
		this(null);
	}

	public EdMeetingSchedule(String id){
		super(id);
	}
	
	@NotNull(message="实际结束时间不能为空")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@NotNull(message="人数不能为空")
	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	
	@NotNull(message="实际开始时间不能为空")
	public Long getVideoAccountId() {
		return videoAccountId;
	}

	public void setVideoAccountId(Long videoAccountId) {
		this.videoAccountId = videoAccountId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScheduleEndTime() {
		return scheduleEndTime;
	}

	public void setScheduleEndTime(Date scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
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