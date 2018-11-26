/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.edu.entity.EdVideoAccount;
import com.jeesite.modules.edu.dao.EdVideoAccountDao;

/**
 * ed_video_accountService
 * @author Roger
 * @version 2018-11-26
 */
@Service
@Transactional(readOnly=true)
public class EdVideoAccountService extends CrudService<EdVideoAccountDao, EdVideoAccount> {
	
	/**
	 * 获取单条数据
	 * @param edVideoAccount
	 * @return
	 */
	@Override
	public EdVideoAccount get(EdVideoAccount edVideoAccount) {
		return super.get(edVideoAccount);
	}
	
	/**
	 * 查询分页数据
	 * @param edVideoAccount 查询条件
	 * @return
	 */
	@Override
	public Page<EdVideoAccount> findPage(EdVideoAccount edVideoAccount) {
		return super.findPage(edVideoAccount);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param edVideoAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EdVideoAccount edVideoAccount) {
		super.save(edVideoAccount);
	}
	
	/**
	 * 更新状态
	 * @param edVideoAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EdVideoAccount edVideoAccount) {
		super.updateStatus(edVideoAccount);
	}
	
	/**
	 * 删除数据
	 * @param edVideoAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EdVideoAccount edVideoAccount) {
		super.delete(edVideoAccount);
	}
	
}