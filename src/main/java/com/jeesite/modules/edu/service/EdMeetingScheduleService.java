/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.edu.entity.EdMeetingSchedule;
import com.jeesite.modules.edu.dao.EdMeetingScheduleDao;

/**
 * ed_meeting_scheduleService
 * @author Roger
 * @version 2018-11-26
 */
@Service
@Transactional(readOnly=true)
public class EdMeetingScheduleService extends CrudService<EdMeetingScheduleDao, EdMeetingSchedule> {
	
	/**
	 * 获取单条数据
	 * @param edMeetingSchedule
	 * @return
	 */
	@Override
	public EdMeetingSchedule get(EdMeetingSchedule edMeetingSchedule) {
		return super.get(edMeetingSchedule);
	}
	
	/**
	 * 查询分页数据
	 * @param edMeetingSchedule 查询条件
	 * @return
	 */
	@Override
	public Page<EdMeetingSchedule> findPage(EdMeetingSchedule edMeetingSchedule) {
		return super.findPage(edMeetingSchedule);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param edMeetingSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EdMeetingSchedule edMeetingSchedule) {
		super.save(edMeetingSchedule);
	}
	
	/**
	 * 更新状态
	 * @param edMeetingSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EdMeetingSchedule edMeetingSchedule) {
		super.updateStatus(edMeetingSchedule);
	}
	
	/**
	 * 删除数据
	 * @param edMeetingSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EdMeetingSchedule edMeetingSchedule) {
		super.delete(edMeetingSchedule);
	}
	
}