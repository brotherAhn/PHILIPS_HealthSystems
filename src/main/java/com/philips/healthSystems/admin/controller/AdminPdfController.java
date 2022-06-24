package com.philips.healthSystems.admin.controller;

import com.philips.healthSystems.admin.response.AdminUserResultForPDFResponse;
import com.philips.healthSystems.admin.service.PhilipsAdminPdfService;
import com.philips.healthSystems.admin.service.PhilipsAdminService;
import com.philips.healthSystems.util.AES256Util;
import com.philips.healthSystems.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class AdminPdfController {

	@Autowired
	private PhilipsAdminPdfService philipsAdminPdfService;



	@Autowired
	private AES256Util aes256Util;
	
	private Log log = LogFactory.getLog(AdminPdfController.class);
	
	@Autowired
	private PhilipsAdminService philipsAdminService;

	@RequestMapping(value = "/admin/user_result_view",method = RequestMethod.POST)
	public ModelAndView admin_private(HttpServletRequest req, HttpServletResponse res, int UserPkid) {
		log.info("/admin/user_result_view");
		ModelAndView mav = new ModelAndView("view/admin/pdf/user_result_view");

		if(UserPkid == 0) {
			try {
				return WebUtil.alertAndBack("잘못된 접근입니다.", res);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if(philipsAdminPdfService.countUserInfoResult(UserPkid) == 0){
			try {
				return WebUtil.alertAndBack("완료되지 않은 데이터 입니다.", res);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			AdminUserResultForPDFResponse response = philipsAdminPdfService.getUserInfoResult(UserPkid);

			String marketing2_dis = "N";
			if(response.getMarketing2_all().equals("N")) {
				if(response.getMarketing2_dm().equals("N") && response.getMarketing2_email().equals("N") && response.getMarketing2_sms().equals("N") && response.getMarketing2_tel().equals("N")) {
					marketing2_dis = "Y";
				}
			}

			String decryptName = aes256Util.decrypt(response.getName());
			String decryptMobile = aes256Util.decrypt(response.getMobile());
			mav.addObject("name",decryptName);
			mav.addObject("mobile",decryptMobile);
			mav.addObject("Marketing1_yn",response.getMarketing1_yn());
			mav.addObject("Marketing2_all",response.getMarketing2_all());
			mav.addObject("Marketing2_dm",response.getMarketing2_dm());
			mav.addObject("Marketing2_email",response.getMarketing2_email());
			mav.addObject("Marketing2_sms",response.getMarketing2_sms());
			mav.addObject("Marketing2_tel",response.getMarketing2_tel());
			mav.addObject("marketing2_dis",marketing2_dis);
			mav.addObject("agree_dt",response.getAgree_dt());
			mav.addObject("file_name",response.getFile_name()+"_"+decryptName);


		} catch (Exception e) {
			try {
				WebUtil.alertAndBack("잘못된 접근입니다.", res);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return mav;
	}

	@RequestMapping(value = "/admin/user_result_view2",method = RequestMethod.POST)
	public ModelAndView admin_private2(HttpServletRequest req, HttpServletResponse res, int UserPkid) {
		log.info("/admin/user_result_view2");
		ModelAndView mav = new ModelAndView("view/admin/pdf/user_result_view2");

		if(UserPkid == 0) {
			try {
				return WebUtil.alertAndBack("잘못된 접근입니다.", res);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if(philipsAdminPdfService.countUserInfoResult(UserPkid) == 0){
			try {
				return WebUtil.alertAndBack("완료되지 않은 데이터 입니다.", res);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			AdminUserResultForPDFResponse response = philipsAdminPdfService.getUserInfoResult(UserPkid);

			String marketing2_dis = "N";
			if(response.getMarketing2_all().equals("N")) {
				if(response.getMarketing2_dm().equals("N") && response.getMarketing2_email().equals("N") && response.getMarketing2_sms().equals("N") && response.getMarketing2_tel().equals("N")) {
					marketing2_dis = "Y";
				}
			}

			String decryptName = aes256Util.decrypt(response.getName());
			String decryptMobile = aes256Util.decrypt(response.getMobile());
			mav.addObject("name",decryptName);
			mav.addObject("mobile",decryptMobile);
			mav.addObject("Marketing1_yn",response.getMarketing1_yn());
			mav.addObject("Marketing2_all",response.getMarketing2_all());
			mav.addObject("Marketing2_dm",response.getMarketing2_dm());
			mav.addObject("Marketing2_email",response.getMarketing2_email());
			mav.addObject("Marketing2_sms",response.getMarketing2_sms());
			mav.addObject("Marketing2_tel",response.getMarketing2_tel());
			mav.addObject("marketing2_dis",marketing2_dis);
			mav.addObject("agree_dt",response.getAgree_dt());
			mav.addObject("file_name",response.getFile_name()+"_"+decryptName);


		} catch (Exception e) {
			try {
				WebUtil.alertAndBack("잘못된 접근입니다.", res);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return mav;
	}
	
}
