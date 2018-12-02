/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.edu.entity.EdVendor;
import com.jeesite.modules.edu.dao.EdVendorDao;

/**
 * 供应商表Service
 * @author Roger
 * @version 2018-12-02
 */
@Service
@Transactional(readOnly=true)
public class EdVendorService extends CrudService<EdVendorDao, EdVendor> {
	
	/**
	 * 获取单条数据
	 * @param edVendor
	 * @return
	 */
	@Override
	public EdVendor get(EdVendor edVendor) {
		return super.get(edVendor);
	}
	
	/**
	 * 查询分页数据
	 * @param edVendor 查询条件
	 * @param edVendor.page 分页对象
	 * @return
	 */
	@Override
	public Page<EdVendor> findPage(EdVendor edVendor) {
		return super.findPage(edVendor);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param edVendor
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EdVendor edVendor) {
		super.save(edVendor);
	}
	
	/**
	 * 更新状态
	 * @param edVendor
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EdVendor edVendor) {
		super.updateStatus(edVendor);
	}
	
	/**
	 * 删除数据
	 * @param edVendor
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EdVendor edVendor) {
		super.delete(edVendor);
	}
	
}