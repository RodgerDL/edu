/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.utils.EmpUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.edu.entity.EdMeeting;
import com.jeesite.modules.edu.service.EdMeetingService;

/**
 * 会议表Controller
 * @author Roger
 * @version 2018-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/edu/edMeeting")
public class EdMeetingController extends BaseController {

	@Autowired
	private EdMeetingService edMeetingService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EdMeeting get(String meetingCode, boolean isNewRecord) {
		return edMeetingService.get(meetingCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("edu:edMeeting:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdMeeting edMeeting, Model model) {
		model.addAttribute("edMeeting", edMeeting);
		return "modules/edu/edMeetingList";
	}

    /**
     * 查询列表
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = {"myList", ""})
    public String myList(EdMeeting edMeeting, Model model) {
        edMeeting.setTestUser(UserUtils.getUser());
        model.addAttribute("edMeeting", edMeeting);
        return "modules/edu/edMyMeetingList";
    }

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("edu:edMeeting:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EdMeeting> listData(EdMeeting edMeeting, HttpServletRequest request, HttpServletResponse response) {
		edMeeting.setPage(new Page<>(request, response));
		Page<EdMeeting> page = edMeetingService.findPage(edMeeting); 
		return page;
	}

    /**
     * 查询当前用户的列表数据
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = "myListData")
    @ResponseBody
    public Page<EdMeeting> myListData(EdMeeting edMeeting, HttpServletRequest request, HttpServletResponse response) {
        edMeeting.setPage(new Page<>(request, response));
        edMeeting.getSqlMap().getWhere().disableAutoAddCorpCodeWhere()
                .and("teacher_code", QueryType.EQ, UserUtils.getUser().getUserCode());
        Page<EdMeeting> page = edMeetingService.findPage(edMeeting);
        return page;
    }

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("edu:edMeeting:view")
	@RequestMapping(value = "form")
	public String form(EdMeeting edMeeting, Model model) {
		model.addAttribute("edMeeting", edMeeting);
		return "modules/edu/edMeetingForm";
	}

	/**
	 * 保存会议表
	 */
	@RequiresPermissions("edu:edMeeting:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EdMeeting edMeeting) {
		edMeetingService.save(edMeeting);
		return renderResult(Global.TRUE, text("保存会议表成功！"));
	}
	
	/**
	 * 删除会议表
	 */
	@RequiresPermissions("edu:edMeeting:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EdMeeting edMeeting) {
		edMeetingService.delete(edMeeting);
		return renderResult(Global.TRUE, text("删除会议表成功！"));
	}
	
}