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
 * 会议预定Entity
 * @author Roger
 * @version 2018-11-26
 */
@Table(name="ed_meeting_schedule", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="学员编号"),
		@Column(name="meeting_id", attrName="meetingId", label="会议编号"),
		@Column(name="video_account_id", attrName="videoAccountId", label="实际开始时间"),
		@Column(name="schedule_start_time", attrName="scheduleStartTime", label="计划开始时间"),
		@Column(name="schedule_end_time", attrName="scheduleEndTime", label="计划结束时间"),
		@Column(includeEntity=DataEntity.class)
	}, orderBy="a.id DESC"
)
public class EdMeetingSchedule extends DataEntity<EdMeetingSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private String meetingId;
	private String videoAccountId;		// 实际开始时间
	private Date scheduleStartTime;		// 计划开始时间
	private Date scheduleEndTime;		// 计划结束时间
	
	public EdMeetingSchedule() {
		this(null);
	}

	public EdMeetingSchedule(String id){
		super(id);
	}
	
	@NotNull(message="实际结束时间不能为空")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotNull(message="人数不能为空")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@NotNull(message="实际开始时间不能为空")
	public String getVideoAccountId() {
		return videoAccountId;
	}

	public void setVideoAccountId(String videoAccountId) {
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

	
}