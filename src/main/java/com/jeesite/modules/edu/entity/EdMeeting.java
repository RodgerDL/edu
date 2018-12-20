/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.entity;

import javax.validation.constraints.NotBlank;

import com.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 会议表Entity
 * @author Roger
 * @version 2018-12-04
 */
@Table(name="ed_meeting", alias="a", columns={
		@Column(name="meeting_code", attrName="meetingCode", label="会议编号", isPK=true),
		@Column(name="name", attrName="name", label="会议名称", queryType=QueryType.LIKE),
        @Column(name="teacher_code", attrName="testUser.userCode", label="老师编号"),
        @Column(name="account_code", attrName="accountCode", label="视频账号编号"),
		@Column(name="count", attrName="count", label="参加人数"),
		@Column(name="invite_code", attrName="inviteCode", label="邀请码"),
		@Column(name="plan_start_time", attrName="planStartTime", label="计划开始时间"),
		@Column(name="plan_end_time", attrName="planEndTime", label="计划结束时间"),
		@Column(name="actual_start_time", attrName="actualStartTime", label="实际开始时间"),
		@Column(name="actual_end_time", attrName="actualEndTime", label="实际结束时间"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable={
        @JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= User.class, attrName="testUser", alias="u12",
                on="u12.user_code = a.teacher_code", columns={
                @Column(name="user_code", attrName="userCode", label="用户编码", isPK=true),
                @Column(name="user_name", attrName="userName", label="用户名称", isQuery=false),
        })
    }, orderBy="a.plan_start_time DESC"
)
public class EdMeeting extends DataEntity<EdMeeting> {
	
	private static final long serialVersionUID = 1L;
	private String meetingCode;		// 会议编号
	private String name;		// 会议名称
    private User testUser;		// 老师编号
    private String AccountCode;  // 视频账号
	private Integer count;		// 参加人数
	private String inviteCode;		// 邀请码
	private Date planStartTime;		// 计划开始时间
	private Date planEndTime;		// 计划结束时间
	private Date actualStartTime;		// 实际开始时间
	private Date actualEndTime;		// 实际结束时间
    private String hostMeetingURL;
    private List<EdAccount> edAccountList = ListUtils.newArrayList(); // 账号列表
	private List<EdUserAccountMapping> edUserAccountMappingList = ListUtils.newArrayList();		// 子表列表
	
	public EdMeeting() {
		this(null);
	}

	public EdMeeting(String id){
		super(id);
	}
	
	public String getMeetingCode() {
		return meetingCode;
	}

	public void setMeetingCode(String meetingCode) {
		this.meetingCode = meetingCode;
	}

    public User getTestUser() {
        return testUser;
    }

    public void setTestUser(User testUser) {
        this.testUser = testUser;
    }

    public String getAccountCode() {
        return AccountCode;
    }

    public void setAccountCode(String accountCode) {
        AccountCode = accountCode;
    }

    @NotBlank(message="会议名称不能为空")
	@Length(min=0, max=100, message="会议名称长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @NotNull(message="参加人数不能为空")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
//	@NotBlank(message="邀请码不能为空")
	@Length(min=0, max=100, message="邀请码长度不能超过 100 个字符")
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="计划开始时间不能为空")
	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="计划结束时间不能为空")
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

    public String getHostMeetingURL() {
        return hostMeetingURL;
    }

    public void setHostMeetingURL(String hostMeetingURL) {
        this.hostMeetingURL = hostMeetingURL;
    }

    public List<EdAccount> getEdAccountList() {
        return edAccountList;
    }

    public void setEdAccountList(List<EdAccount> edAccountList) {
        this.edAccountList = edAccountList;
    }

    public List<EdUserAccountMapping> getEdUserAccountMappingList() {
		return edUserAccountMappingList;
	}

	public void setEdUserAccountMappingList(List<EdUserAccountMapping> edUserAccountMappingList) {
		this.edUserAccountMappingList = edUserAccountMappingList;
	}
	
}