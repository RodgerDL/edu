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
import com.jeesite.modules.edu.entity.EdVendor;
import com.jeesite.modules.edu.service.EdVendorService;

/**
 * 供应商表Controller
 * @author Roger
 * @version 2018-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/edu/edVendor")
public class EdVendorController extends BaseController {

	@Autowired
	private EdVendorService edVendorService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EdVendor get(String venderCode, boolean isNewRecord) {
		return edVendorService.get(venderCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("edu:edVendor:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdVendor edVendor, Model model) {
		model.addAttribute("edVendor", edVendor);
		return "modules/edu/edVendorList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("edu:edVendor:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EdVendor> listData(EdVendor edVendor, HttpServletRequest request, HttpServletResponse response) {
		edVendor.setPage(new Page<>(request, response));
		Page<EdVendor> page = edVendorService.findPage(edVendor); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("edu:edVendor:view")
	@RequestMapping(value = "form")
	public String form(EdVendor edVendor, Model model) {
		model.addAttribute("edVendor", edVendor);
		return "modules/edu/edVendorForm";
	}

	/**
	 * 保存供应商表
	 */
	@RequiresPermissions("edu:edVendor:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EdVendor edVendor) {
		edVendorService.save(edVendor);
		return renderResult(Global.TRUE, text("保存供应商表成功！"));
	}
	
	/**
	 * 删除供应商表
	 */
	@RequiresPermissions("edu:edVendor:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EdVendor edVendor) {
		edVendorService.delete(edVendor);
		return renderResult(Global.TRUE, text("删除供应商表成功！"));
	}
	
}