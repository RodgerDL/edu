/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.edu.entity.EdMeeting;
import com.jeesite.modules.edu.dao.EdMeetingDao;
import com.jeesite.modules.edu.entity.EdUserAccountMapping;
import com.jeesite.modules.edu.dao.EdUserAccountMappingDao;

/**
 * 会议表Service
 * @author Roger
 * @version 2018-12-04
 */
@Service
@Transactional(readOnly=true)
public class EdMeetingService extends CrudService<EdMeetingDao, EdMeeting> {
	
	@Autowired
	private EdUserAccountMappingDao edUserAccountMappingDao;
	
	/**
	 * 获取单条数据
	 * @param edMeeting
	 * @return
	 */
	@Override
	public EdMeeting get(EdMeeting edMeeting) {
		EdMeeting entity = super.get(edMeeting);
		if (entity != null){
			EdUserAccountMapping edUserAccountMapping = new EdUserAccountMapping(entity);
			edUserAccountMapping.setStatus(EdUserAccountMapping.STATUS_NORMAL);
			entity.setEdUserAccountMappingList(edUserAccountMappingDao.findList(edUserAccountMapping));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param edMeeting 查询条件
	 * @param edMeeting.page 分页对象
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
		// 保存 EdMeeting子表
		for (EdUserAccountMapping edUserAccountMapping : edMeeting.getEdUserAccountMappingList()){
			if (!EdUserAccountMapping.STATUS_DELETE.equals(edUserAccountMapping.getStatus())){
				edUserAccountMapping.setMeetingCode(edMeeting);
				if (edUserAccountMapping.getIsNewRecord()){
					edUserAccountMapping.preInsert();
					edUserAccountMappingDao.insert(edUserAccountMapping);
				}else{
					edUserAccountMapping.preUpdate();
					edUserAccountMappingDao.update(edUserAccountMapping);
				}
			}else{
				edUserAccountMappingDao.delete(edUserAccountMapping);
			}
		}
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
		EdUserAccountMapping edUserAccountMapping = new EdUserAccountMapping();
		edUserAccountMapping.setMeetingCode(edMeeting);
		edUserAccountMappingDao.delete(edUserAccountMapping);
	}
	
}