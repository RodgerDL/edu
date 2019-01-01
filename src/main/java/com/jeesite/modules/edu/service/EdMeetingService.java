/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeesite.modules.edu.dao.EdAccountDao;
import com.jeesite.modules.edu.entity.EdAccount;
import com.jeesite.modules.edu.entity.EdStudentMeeting;
import com.jeesite.modules.edu.utils.RequestUtils;
import org.dom4j.DocumentException;
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

    @Autowired
    private EdAccountDao edAccountDao;

    @Autowired
    private EdMeetingDao edMeetingDao;

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
     * 获取账号列表
     * @return
     */
	public List<EdAccount> getAccountList() {
        EdAccount edAccount = new EdAccount();
        edAccount.setStatus(EdAccount.STATUS_NORMAL);
	    return edAccountDao.findList(edAccount);
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
     * 查询学生课程
     * @param params 查询条件
     * @return
     */
    public Page<EdStudentMeeting> findListForStudent(Map params) {

//		pageMap.setList(dao.findListForMap(params));
        List<EdStudentMeeting> edMeetingList = edMeetingDao.findListForStudent(params);
        Page<EdStudentMeeting> edMeetingPage = new Page<>();
        edMeetingPage.setList(edMeetingList);
        return edMeetingPage;
    }
	
	/**
	 * 保存数据（插入或更新）
	 * @param edMeeting
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EdMeeting edMeeting) {

	    try {
            EdAccount edAccount = new EdAccount();
            edAccount.setAccountCode(edMeeting.getAccountCode());
            edAccount = edAccountDao.get(edAccount);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String planDate = sdf.format(edMeeting.getPlanStartTime());

            String meetingId = RequestUtils.creatMeeting(edAccount.getName(), edAccount.getPassword(), planDate, edMeeting.getDuration().toString());
            edMeeting.setInviteCode(meetingId);

        } catch (IOException ie) {

        } catch (DocumentException ed) {

        }

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