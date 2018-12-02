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
import com.jeesite.modules.edu.entity.EdAccount;
import com.jeesite.modules.edu.service.EdAccountService;

/**
 * 视频账户表Controller
 * @author Roger
 * @version 2018-12-02
 */
@Controller
@RequestMapping(value = "${adminPath}/edu/edAccount")
public class EdAccountController extends BaseController {

	@Autowired
	private EdAccountService edAccountService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EdAccount get(String acountCode, boolean isNewRecord) {
		return edAccountService.get(acountCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("edu:edAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdAccount edAccount, Model model) {
		model.addAttribute("edAccount", edAccount);
		return "modules/edu/edAccountList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("edu:edAccount:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EdAccount> listData(EdAccount edAccount, HttpServletRequest request, HttpServletResponse response) {
		edAccount.setPage(new Page<>(request, response));
		Page<EdAccount> page = edAccountService.findPage(edAccount); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("edu:edAccount:view")
	@RequestMapping(value = "form")
	public String form(EdAccount edAccount, Model model) {
		model.addAttribute("edAccount", edAccount);
		return "modules/edu/edAccountForm";
	}

	/**
	 * 保存视频账户表
	 */
	@RequiresPermissions("edu:edAccount:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EdAccount edAccount) {
		edAccountService.save(edAccount);
		return renderResult(Global.TRUE, text("保存视频账户表成功！"));
	}
	
	/**
	 * 删除视频账户表
	 */
	@RequiresPermissions("edu:edAccount:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EdAccount edAccount) {
		edAccountService.delete(edAccount);
		return renderResult(Global.TRUE, text("删除视频账户表成功！"));
	}
	
}