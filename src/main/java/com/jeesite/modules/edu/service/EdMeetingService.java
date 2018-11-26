/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.edu.entity.EdMeeting;
import com.jeesite.modules.edu.dao.EdMeetingDao;

/**
 * ed_meetingService
 * @author Roger
 * @version 2018-11-26
 */
@Service
@Transactional(readOnly=true)
public class EdMeetingService extends CrudService<EdMeetingDao, EdMeeting> {
	
	/**
	 * 获取单条数据
	 * @param edMeeting
	 * @return
	 */
	@Override
	public EdMeeting get(EdMeeting edMeeting) {
		return super.get(edMeeting);
	}
	
	/**
	 * 查询分页数据
	 * @param edMeeting 查询条件
	 * @return
	 */
	@Override
	public Page<EdMeeting> findPage(EdMeeting edMeeting) {
		return super.findPage(edMeeting);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param edMeeting
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EdMeeting edMeeting) {
		super.save(edMeeting);
	}
	
	/**
	 * 更新状态
	 * @param edMeeting
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EdMeeting edMeeting) {
		super.updateStatus(edMeeting);
	}
	
	/**
	 * 删除数据
	 * @param edMeeting
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EdMeeting edMeeting) {
		super.delete(edMeeting);
	}
	
}