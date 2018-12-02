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
@Table(name="ed_meeting_schedule", alias="a", columns={
		@Column(name="schedule_code", attrName="scheduleCode", label="会议预定编号", isPK=true),
		@Column(name="meeting_code", attrName="meetingCode", label="会议编号"),
		@Column(name="schedule_start_time", attrName="scheduleStartTime", label="计划开始时间"),
		@Column(name="schedule_end_time", attrName="scheduleEndTime", label="计划结束时间"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="deleted", attrName="deleted", label="是否删除"),
	}, orderBy="a.update_date DESC"
)
public class EdMeetingSchedule extends DataEntity<EdMeetingSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String scheduleCode;		// 会议预定编号
	private String meetingCode;		// 会议编号
	private Date scheduleStartTime;		// 计划开始时间
	private Date scheduleEndTime;		// 计划结束时间
	private Integer deleted;		// 是否删除
	
	public EdMeetingSchedule() {
		this(null);
	}

	public EdMeetingSchedule(String id){
		super(id);
	}
	
	public String getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	
	@NotBlank(message="会议编号不能为空")
	@Length(min=0, max=64, message="会议编号长度不能超过 64 个字符")
	public String getMeetingCode() {
		return meetingCode;
	}

	public void setMeetingCode(String meetingCode) {
		this.meetingCode = meetingCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="计划开始时间不能为空")
	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="计划结束时间不能为空")
	public Date getScheduleEndTime() {
		return scheduleEndTime;
	}

	public void setScheduleEndTime(Date scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}
	
	@NotNull(message="是否删除不能为空")
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
}