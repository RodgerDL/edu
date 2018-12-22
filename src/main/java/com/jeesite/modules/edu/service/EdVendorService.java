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
import com.jeesite.modules.edu.entity.EdVendor;
import com.jeesite.modules.edu.dao.EdVendorDao;
import com.jeesite.modules.edu.entity.EdAccount;
import com.jeesite.modules.edu.dao.EdAccountDao;

/**
 * 供应商表Service
 * @author Roger
 * @version 2018-12-04
 */
@Service
@Transactional(readOnly=true)
public class EdVendorService extends CrudService<EdVendorDao, EdVendor> {
	
	@Autowired
	private EdAccountDao edAccountDao;
	
	/**
	 * 获取单条数据
	 * @param edVendor
	 * @return
	 */
	@Override
	public EdVendor get(EdVendor edVendor) {
		EdVendor entity = super.get(edVendor);
		if (entity != null){
			EdAccount edAccount = new EdAccount(entity);
			edAccount.setStatus(EdAccount.STATUS_NORMAL);
			entity.setEdAccountList(edAccountDao.findList(edAccount));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param edVendor 查询条件
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
		// 保存 EdVendor子表
		for (EdAccount edAccount : edVendor.getEdAccountList()){
			if (!EdAccount.STATUS_DELETE.equals(edAccount.getStatus())){
				edAccount.setVendorCode(edVendor);
				if (edAccount.getIsNewRecord()){
					edAccount.preInsert();
					edAccountDao.insert(edAccount);
				}else{
					edAccount.preUpdate();
					edAccountDao.update(edAccount);
				}
			}else{
				edAccountDao.delete(edAccount);
			}
		}
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
		EdAccount edAccount = new EdAccount();
		edAccount.setVendorCode(edVendor);
		edAccountDao.delete(edAccount);
	}

    /**
     * 获取账号单条数据
     * @param edAccount
     * @return
     */
    public EdAccount getAccount(EdAccount edAccount) {
        EdAccount entity = edAccountDao.get(edAccount);
        return entity;
    }
	
}