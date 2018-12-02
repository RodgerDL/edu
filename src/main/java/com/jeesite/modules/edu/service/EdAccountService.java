/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.edu.entity.EdAccount;
import com.jeesite.modules.edu.dao.EdAccountDao;

/**
 * 视频账户表Service
 * @author Roger
 * @version 2018-12-02
 */
@Service
@Transactional(readOnly=true)
public class EdAccountService extends CrudService<EdAccountDao, EdAccount> {
	
	/**
	 * 获取单条数据
	 * @param edAccount
	 * @return
	 */
	@Override
	public EdAccount get(EdAccount edAccount) {
		return super.get(edAccount);
	}
	
	/**
	 * 查询分页数据
	 * @param edAccount 查询条件
	 * @param edAccount.page 分页对象
	 * @return
	 */
	@Override
	public Page<EdAccount> findPage(EdAccount edAccount) {
		return super.findPage(edAccount);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param edAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EdAccount edAccount) {
		super.save(edAccount);
	}
	
	/**
	 * 更新状态
	 * @param edAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EdAccount edAccount) {
		super.updateStatus(edAccount);
	}
	
	/**
	 * 删除数据
	 * @param edAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EdAccount edAccount) {
		super.delete(edAccount);
	}
	
}