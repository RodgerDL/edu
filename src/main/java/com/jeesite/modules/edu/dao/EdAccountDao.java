/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.edu.entity.EdAccount;

/**
 * 视频账户表DAO接口
 * @author Roger
 * @version 2018-12-02
 */
@MyBatisDao
public interface EdAccountDao extends CrudDao<EdAccount> {
	
}