/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.edu.entity.EdAccount;

/**
 * 供应商表DAO接口
 * @author Roger
 * @version 2018-12-04
 */
@MyBatisDao
public interface EdAccountDao extends CrudDao<EdAccount> {

}