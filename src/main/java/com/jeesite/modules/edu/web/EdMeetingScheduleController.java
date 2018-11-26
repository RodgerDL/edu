/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.edu.entity.EdMeetingSchedule;
import com.jeesite.modules.edu.service.EdMeetingScheduleService;

/**
 * ed_meeting_scheduleController
 * @author Roger
 * @version 2018-11-26
 */
@Controller
@RequestMapping(value = "${adminPath}/edu/edMeetingSchedule")
public class EdMeetingScheduleController extends BaseController {

	@Autowired
	private EdMeetingScheduleService edMeetingScheduleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
//	public EdMeetingSchedule get(Long id, boolean isNewRecord) {
//		return edMeetingScheduleService.get(id, isNewRecord);
//	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("edu:edMeetingSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdMeetingSchedule edMeetingSchedule, Model model) {
		model.addAttribute("edMeetingSchedule", edMeetingSchedule);
		return "modules/edu/edMeetingScheduleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("edu:edMeetingSchedule:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EdMeetingSchedule> listData(EdMeetingSchedule edMeetingSchedule, HttpServletRequest request, HttpServletResponse response) {
		edMeetingSchedule.setPage(new Page<>(request, response));
		Page<EdMeetingSchedule> page = edMeetingScheduleService.findPage(edMeetingSchedule); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("edu:edMeetingSchedule:view")
	@RequestMapping(value = "form")
	public String form(EdMeetingSchedule edMeetingSchedule, Model model) {
		model.addAttribute("edMeetingSchedule", edMeetingSchedule);
		return "modules/edu/edMeetingScheduleForm";
	}

	/**
	 * 保存ed_meeting_schedule
	 */
	@RequiresPermissions("edu:edMeetingSchedule:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EdMeetingSchedule edMeetingSchedule) {
		edMeetingScheduleService.save(edMeetingSchedule);
		return renderResult(Global.TRUE, text("保存ed_meeting_schedule成功！"));
	}
	
	/**
	 * 删除ed_meeting_schedule
	 */
	@RequiresPermissions("edu:edMeetingSchedule:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EdMeetingSchedule edMeetingSchedule) {
		edMeetingScheduleService.delete(edMeetingSchedule);
		return renderResult(Global.TRUE, text("删除ed_meeting_schedule成功！"));
	}
	
}