/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.edu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.edu.entity.EdAccount;
import com.jeesite.modules.edu.entity.EdStudentMeeting;
import com.jeesite.modules.edu.entity.EdUserAccountMapping;
import com.jeesite.modules.edu.service.EdVendorService;
import com.jeesite.modules.edu.utils.RequestUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.DocumentException;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

	@Autowired
    private EdVendorService edVendorService;
	
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
     * 查询老师课程列表
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = {"teacherMyList", ""})
    public String teacherMyList(EdMeeting edMeeting, Model model) {
        model.addAttribute("edMeeting", edMeeting);
        return "modules/edu/edTeacherMyMeetingList";
    }

    /**
     * 查询学生课程列表
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = {"studentMyList", ""})
    public String studentMyList(EdMeeting edMeeting, Model model) {
        model.addAttribute("edMeeting", edMeeting);
        return "modules/edu/edStudentMyMeetingList";
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
    @RequestMapping(value = "teacherMyListData")
    @ResponseBody
    public Page<EdStudentMeeting> teacherMyListData(EdMeeting edMeeting, HttpServletRequest request, HttpServletResponse response) {
//        edMeeting.setPage(new Page<>(request, response));
//        edMeeting.getSqlMap().getWhere().disableAutoAddCorpCodeWhere()
//                .and("teacher_code", QueryType.EQ, UserUtils.getUser().getUserCode());
//        Page<EdMeeting> page = edMeetingService.findPage(edMeeting);
//        return page;

        Map<String, Object> params = MapUtils.newHashMap();
        params.put("userCode", UserUtils.getUser().getUserCode());
        Page<EdStudentMeeting> page = edMeetingService.findListForStudent(params);

        return page;
    }

    /**
     * 查询当前用户的列表数据
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = "studentMyListData")
    @ResponseBody
    public Page<EdStudentMeeting> studentMyListData(EdMeeting edMeeting, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = MapUtils.newHashMap();
		params.put("userCode", UserUtils.getUser().getUserCode());
        Page<EdStudentMeeting> page = edMeetingService.findListForStudent(params);
        return page;
    }

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("edu:edMeeting:view")
	@RequestMapping(value = "form")
	public String form(EdMeeting edMeeting, Model model) {
        edMeeting.setEdAccountList(edMeetingService.getAccountList());
		model.addAttribute("edMeeting", edMeeting);
		return "modules/edu/edMeetingForm";
	}

    /**
     * 查看老师课程表单
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = "teacherMyForm")
    public String teacherMyForm(EdMeeting edMeeting, Model model) {
        EdAccount edAccount = new EdAccount();
        edAccount.setAccountCode(edMeeting.getAccountCode());
        edAccount = edVendorService.getAccount(edAccount);

        // 学生视图
        if (edMeeting.getTestUser() != null && !UserUtils.getUser().getUserCode().equals(edMeeting.getTestUser().getUserCode())) {
            edAccount.setAccountCode(edMeeting.getAccountCode());
            edAccount = edVendorService.getAccount(edAccount);
            String joinURL = "";
            try {
                joinURL = RequestUtils.getjoinurlMeeting(edAccount.getName(), edAccount.getPassword(), edMeeting.getInviteCode());
            } catch (IOException ie) {

            } catch (DocumentException de) {

            }
            edMeeting.setJoinMeetingURL(joinURL);
            model.addAttribute("edMeeting", edMeeting);
            return "modules/edu/edStudentMyMeetingForm";

        // 老师视图
        } else {
            String hostURL = "";
            try {
                hostURL = RequestUtils.gethosturlMeeting(edAccount.getName(), edAccount.getPassword(), edMeeting.getInviteCode());
            } catch (IOException ie) {

            } catch (DocumentException de) {

            }
            edMeeting.setHostMeetingURL(hostURL);
            model.addAttribute("edMeeting", edMeeting);
            return "modules/edu/edTeacherMyMeetingForm";
        }

    }

    /**
     * 查看学生课程表单
     */
    @RequiresPermissions("edu:edMeeting:view")
    @RequestMapping(value = "studentMyForm")
    public String studentMyForm(EdMeeting edMeeting, Model model) {
        EdAccount edAccount = new EdAccount();
//        for (EdUserAccountMapping edUserAccountMapping : edMeeting.getEdUserAccountMappingList()) {
//            if (UserUtils.getUser().getUserCode().equals(edUserAccountMapping.getTestUser().getUserCode())) {
//                edAccount.setAccountCode(edUserAccountMapping.getAccountCode());
//            }
//        }
        edAccount.setAccountCode(edMeeting.getAccountCode());
        edAccount = edVendorService.getAccount(edAccount);
        String joinURL = "";
        try {
            joinURL = RequestUtils.getjoinurlMeeting(edAccount.getName(), edAccount.getPassword(), edMeeting.getInviteCode());
        } catch (IOException ie) {

        } catch (DocumentException de) {

        }
        edMeeting.setJoinMeetingURL(joinURL);
        model.addAttribute("edMeeting", edMeeting);
        return "modules/edu/edStudentMyMeetingForm";
    }

	/**
	 * 保存会议表
	 */
	@RequiresPermissions("edu:edMeeting:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EdMeeting edMeeting) {
		for (EdUserAccountMapping edUserAccountMapping : edMeeting.getEdUserAccountMappingList()) {
            if (edUserAccountMapping.getTestUser() != null && edUserAccountMapping.getTestUser().getUserCode().equals(edMeeting.getTestUser().getUserCode())) {
                return renderResult(Global.FALSE, text("授课老师和学员不可重复！"));
            }
        }
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

    /**
     * 获取主持人开会地址
     */
    @PostMapping(value = "getHostURL")
    @ResponseBody
    public String getHostURL(String meetingCode) {
        EdMeeting edMeeting = new EdMeeting();
        edMeeting.setMeetingCode(meetingCode);
        edMeeting = edMeetingService.get(edMeeting);
        EdAccount edAccount = new EdAccount();
        edAccount.setAccountCode(edMeeting.getAccountCode());
        edAccount = edVendorService.getAccount(edAccount);
        String hostURL = "";
        try {
            hostURL = RequestUtils.gethosturlMeeting(edAccount.getName(), edAccount.getPassword(), edMeeting.getInviteCode());
        } catch (IOException ie) {

        } catch (DocumentException de) {

        }
        if (!hostURL.isEmpty()) {
            return renderResult(Global.TRUE, hostURL);
        }
        return renderResult(Global.FALSE, "获取开会地址失败！");
    }
	
}