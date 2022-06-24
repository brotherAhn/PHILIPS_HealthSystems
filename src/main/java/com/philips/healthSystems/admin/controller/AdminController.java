package com.philips.healthSystems.admin.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.philips.healthSystems.admin.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.philips.healthSystems.admin.service.PhilipsAdminService;
import com.philips.healthSystems.client.domain.agreeParam;
import com.philips.healthSystems.util.AES256Util;


@Controller
public class AdminController {
	@Autowired
	private AES256Util aes256Util;
	
	private Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private PhilipsAdminService philipsAdminService;
	
	
	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest req) {
		log.info("/admin/admin");
		ModelAndView mav = new ModelAndView("view/admin/admin");
		return mav;
	}
	@RequestMapping("/sign")
	public ModelAndView sign(HttpServletRequest req) {
		log.info("/admin/sign");
		ModelAndView mav = new ModelAndView("view/admin/sign");
		return mav;
	}
	
	@RequestMapping("/admin_home")
	public ModelAndView admin_home(HttpServletRequest req,agreeParam param) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		log.info("/admin/admin_home");
		ModelAndView mav = new ModelAndView("view/admin/home");
		//mav.addObject("prescription",philipsAdminService.prescriptionSelect(null).size());
		//mav.addObject("private",philipsAdminService.privateSelect(null).size());
		//mav.addObject("agree",philipsAdminService.agreeSelect(null).size());
		//mav.addObject("install",philipsAdminService.installSelect(null).size());
		//mav.addObject("filter",philipsAdminService.filterSelect(null).size());
		//mav.addObject("coach",philipsAdminService.sleepcoachSelect(null).size());
		//mav.addObject("return",philipsAdminService.returnSelect(null).size());
		//mav.addObject("qna",philipsAdminService.qnaSelect(null).size());
		return mav;
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req) {
		log.info("/admin/login");
		ModelAndView mav = new ModelAndView("view/admin/login");
		return mav;
	}
	@RequestMapping("/admin_usergroup")
	public ModelAndView usergroup(HttpServletRequest req,adminTargetGroupParam param) {
		log.info("/admin/admin_usergroup");
		ModelAndView mav = new ModelAndView("view/admin/admin_usergroup");
		mav.addObject("param",param);
		return mav;
	}
	@RequestMapping("/admin_targetRegister")
	public ModelAndView targetRegister(HttpServletRequest req) {
		log.info("/admin/admin_targetRegister");
		ModelAndView mav = new ModelAndView("view/admin/admin_targetRegister");
		return mav;
	}
	@RequestMapping("/admin_groupInfo")
	public ModelAndView groupInfo(HttpServletRequest req,adminGroupInfoParam param) {
		log.info("/admin/admin_groupInfo");
		
		ModelAndView mav = new ModelAndView("view/admin/admin_groupInfo");
		mav.addObject("param", param);
		
		return mav;
	}
	
	@RequestMapping("/admin_send")
	public ModelAndView admin_send(HttpServletRequest req, adminSendParam param ) {
		log.info("/admin/admin_send");
		ModelAndView mav = new ModelAndView("view/admin/admin_send");
		mav.addObject("param", param);
		return mav;
	}

	@RequestMapping("/admin_agreement")
	public ModelAndView admin_agreement(HttpServletRequest req, adminAgreeMentSelectParam param) {
		log.info("/admin/admin_agreement");
		ModelAndView mav = new ModelAndView("view/admin/admin_agreement");
		mav.addObject("param", param);
		return mav;
	}
}
