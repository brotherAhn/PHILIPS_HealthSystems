package com.philips.healthSystems.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.philips.healthSystems.client.domain.agreeResultParam;
import com.philips.healthSystems.client.response.agreeUserResponse;
import com.philips.healthSystems.client.response.agreeUserResultResponse;
import com.philips.healthSystems.client.service.PhilipsClientService;
import com.philips.healthSystems.util.AES256Util;

@Controller
public class ClientController {
	
	private Log log = LogFactory.getLog(ClientController.class);
	
	@Autowired
	private PhilipsClientService philipsClientService;
	
	@Autowired
	private AES256Util aes256Util;
	
	

	@RequestMapping("/userCom")
	public ModelAndView userCom(HttpServletRequest req) {
		log.info("/healthsystems/qna");
		ModelAndView mav = new ModelAndView("view/healthsystems/userCom");
		return mav;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest req, @ModelAttribute("user") String user) {
		log.info("/healthsystems/index");
		
		user = user.replace("&", "%26").replace("+", "%2B");
		String auth = philipsClientService.getUserPkId(user);
		ModelAndView mav = new ModelAndView();
		if(auth == null) {
			mav.setViewName("view/healthsystems/userAuth");
		}else if(auth.equals("COM")) {
			mav.setViewName("view/healthsystems/userCom");
		}else {
			mav.setViewName("view/healthsystems/index");
		}
		
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("/agreeResult")
	public ModelAndView agreeResult(HttpServletRequest req, @ModelAttribute("user") String user) {
		log.info("/healthsystems/agreeResult");
		
		user = user.replace("&", "%26").replace("+", "%2B");
		String auth = philipsClientService.getUserPkIdForResult(user);
		
		ModelAndView mav = new ModelAndView();
		if(auth == null) {
			mav.setViewName("view/healthsystems/userAuth");
		}else {
			mav.setViewName("view/healthsystems/agreeResult");
		}
		
		
		mav.addObject("user", user);
		return mav;
	}


	@RequestMapping("/userAuth")
	public ModelAndView userAuth(HttpServletRequest req) {
		log.info("/healthsystems/userAuth");
		ModelAndView mav = new ModelAndView("view/healthsystems/userAuth");
		return mav;
	}
	


	@RequestMapping(method = RequestMethod.POST, path = "/user/result")
	public ModelAndView userResult(HttpServletRequest req, agreeResultParam param) {
		log.info("/healthsystems/user/result");

		ModelAndView mav = new ModelAndView();

		//String user = param.getUrl().replace("&", "%26").replace("+", "%2B");

		List<agreeUserResultResponse> response = null;

		/**
		 * 유저 확인
		 */
		try {
			param.setName(aes256Util.encrypt(param.getName()));
			param.setMobile(aes256Util.encrypt(param.getMobile()));
			//param.setUrl(user);
			response = philipsClientService.getUserInfoResult(param);

		} catch (Exception e) {
			mav.setViewName("view/healthsystems/userAuth");
			log.info(e.toString());
			return mav;
		}

		/**
		 * 유저 정보가 있으면 보여주기
		 */
		if(response == null) {
			mav.setViewName("view/healthsystems/userAuth");
		}else {
			mav.setViewName("view/healthsystems/result");
			try {
				String marketing2_dis = "N";
				if(response.get(0).getMarketing2_all().equals("N")) {
					if(response.get(0).getMarketing2_dm().equals("N") && response.get(0).getMarketing2_email().equals("N") && response.get(0).getMarketing2_sms().equals("N") && response.get(0).getMarketing2_tel().equals("N")) {
						marketing2_dis = "Y";
					}
				}

				String decryptName = aes256Util.decrypt(response.get(0).getName());
				String decryptMobile = aes256Util.decrypt(response.get(0).getMobile());
				mav.addObject("name",decryptName);
				mav.addObject("mobile",decryptMobile);
				mav.addObject("Marketing1_yn",response.get(0).getMarketing1_yn());
				mav.addObject("Marketing2_all",response.get(0).getMarketing2_all());
				mav.addObject("Marketing2_dm",response.get(0).getMarketing2_dm());
				mav.addObject("Marketing2_email",response.get(0).getMarketing2_email());
				mav.addObject("Marketing2_sms",response.get(0).getMarketing2_sms());
				mav.addObject("Marketing2_tel",response.get(0).getMarketing2_tel());
				mav.addObject("marketing2_dis",marketing2_dis);
				mav.addObject("agree_year",response.get(0).getAgree_year());
				mav.addObject("agree_month",response.get(0).getAgree_month());
				mav.addObject("agree_day",response.get(0).getAgree_day());
			} catch (Exception e) {
				mav.setViewName("view/healthsystems/userAuth");
				return mav;
			}

		}


		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/user/result")
	public ModelAndView userResultGet2(HttpServletRequest req) {
		log.info("/healthsystems/user/resultGET");

		ModelAndView mav = new ModelAndView("view/healthsystems/userAuth");
		return mav;
	}


	/**
	 * 동의서 2 에대한 동의 내역
	 * @param req
	 * @param user
	 * @return
	 */
	@RequestMapping("/co_agreeUser")
	public ModelAndView co_Agree(HttpServletRequest req, @ModelAttribute("user") String user) {
		log.info("/healthsystems/co_agreeUser");


		user = user.replace("&", "%26").replace("+", "%2B");

		List<agreeUserResponse> response = philipsClientService.getUserInfo(user);

		ModelAndView mav = new ModelAndView();

		if(response == null) {
			mav.setViewName("view/healthsystems/userAuth");
		}else {
			mav.setViewName("view/healthsystems/co_agreeUser");
			try {
				String decryptName = aes256Util.decrypt(response.get(0).getName());
				String decryptMobile = aes256Util.decrypt(response.get(0).getMobile());
				mav.addObject("name",decryptName);
				mav.addObject("mobile",decryptMobile);

			} catch (Exception e) {
				mav.setViewName("view/healthsystems/userAuth");
				return mav;
			}

		}

		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping("/resultMsg")
	public ModelAndView resultMsg(HttpServletRequest req) {
		log.info("/healthsystems/resultMsg");
		ModelAndView mav = new ModelAndView("view/healthsystems/resultMsg");
		return mav;
	}




	
}
