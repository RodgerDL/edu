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
import com.jeesite.modules.edu.entity.EdVideoAccount;
import com.jeesite.modules.edu.service.EdVideoAccountService;

/**
 * ed_video_accountController
 * @author Roger
 * @version 2018-11-26
 */
@Controller
@RequestMapping(value = "${adminPath}/edu/edVideoAccount")
public class EdVideoAccountController extends BaseController {

	@Autowired
	private EdVideoAccountService edVideoAccountService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EdVideoAccount get(String id, boolean isNewRecord) {
		return edVideoAccountService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("edu:edVideoAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdVideoAccount edVideoAccount, Model model) {
		model.addAttribute("edVideoAccount", edVideoAccount);
		return "modules/edu/edVideoAccountList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("edu:edVideoAccount:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EdVideoAccount> listData(EdVideoAccount edVideoAccount, HttpServletRequest request, HttpServletResponse response) {
		edVideoAccount.setPage(new Page<>(request, response));
		Page<EdVideoAccount> page = edVideoAccountService.findPage(edVideoAccount); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("edu:edVideoAccount:view")
	@RequestMapping(value = "form")
	public String form(EdVideoAccount edVideoAccount, Model model) {
		model.addAttribute("edVideoAccount", edVideoAccount);
		return "modules/edu/edVideoAccountForm";
	}

	/**
	 * 保存ed_video_account
	 */
	@RequiresPermissions("edu:edVideoAccount:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EdVideoAccount edVideoAccount) {
		edVideoAccountService.save(edVideoAccount);
		return renderResult(Global.TRUE, text("保存ed_video_account成功！"));
	}
	
	/**
	 * 删除ed_video_account
	 */
	@RequiresPermissions("edu:edVideoAccount:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EdVideoAccount edVideoAccount) {
		edVideoAccountService.delete(edVideoAccount);
		return renderResult(Global.TRUE, text("删除ed_video_account成功！"));
	}
	
}