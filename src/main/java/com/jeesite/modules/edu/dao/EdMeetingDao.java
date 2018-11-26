/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.edu.entity.EdMeeting;

/**
 * ed_meetingDAO接口
 * @author Roger
 * @version 2018-11-26
 */
@MyBatisDao
public interface EdMeetingDao extends CrudDao<EdMeeting> {
	
}