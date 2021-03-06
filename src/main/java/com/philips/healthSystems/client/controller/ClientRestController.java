package com.philips.healthSystems.client.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.philips.healthSystems.client.domain.*;
import com.philips.healthSystems.client.response.*;
import com.philips.healthSystems.surem.template.philipsNPSTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.philips.healthSystems.admin.domain.adminSendLogParam;
import com.philips.healthSystems.admin.response.userState;
import com.philips.healthSystems.admin.service.PhilipsAdminService;
import com.philips.healthSystems.client.service.PhilipsClientService;
import com.philips.healthSystems.surem.model.request.reqKakaoAlimtalk;
import com.philips.healthSystems.surem.service.SuremKaKaoService;
import com.philips.healthSystems.surem.service.SuremTextService;
import com.philips.healthSystems.surem.template.philipsResultTemplate;
import com.philips.healthSystems.util.AES256Util;
import com.philips.healthSystems.util.Encoding;
import com.philips.healthSystems.util.NameSplit;

@Controller
public class ClientRestController {
	
	@Autowired
	private PhilipsClientService philipsClientService;
	
	@Autowired
	private Encoding encoding;
	
	@Autowired
	private NameSplit nameSplit;
	
	@Autowired
	private AES256Util aes256Util;
	
	
	@Autowired
	private PhilipsAdminService philipsAdminService;
	
	@Autowired
	private SuremTextService suremTextService;
	
	
	@Autowired
	private SuremKaKaoService suremKaKaoService;
	
	private Log log = LogFactory.getLog(ClientRestController.class);
	

	/**
	 * ?????? ??????
	 * @param req
	 * @param param
	 * @return
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 */
	
	@RequestMapping(value = "/member/json/confirm" , method = RequestMethod.POST)
	public ResponseEntity jsonConfirm(HttpServletRequest req, HttpServletResponse res , @RequestBody confirmParam param) throws IOException  {
		log.info("/member/json/confirm");
		String msg = "";
		try {
			param.setMobile(aes256Util.encrypt(param.getMobile()));
			param.setName(aes256Util.encrypt(param.getName()));
			param.setUserURL(param.getUserURL().replace("&", "%26").replace("+", "%2B"));
			List<confirmResponse> data = philipsClientService.getConfiramUser(param);
			
			if(data.size() != 1 ) {
				return new ResponseEntity(new BaseResponse("9999","????????? ????????? ?????????."), HttpStatus.OK);
			}

			msg = "/co_agreeUser?user="+param.getUserURL();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			log.info(e.toString());
			return new ResponseEntity(new BaseResponse("9999","????????? ????????? ?????????."), HttpStatus.OK);
		}
		
		
		

		param.setAuth(req.getSession().getId()+"userAuth");
		param.setState(userState.COF);
		int code = philipsClientService.setUserAuth(param);
		if(code!=0) {
			HttpSession session = (HttpSession)req.getSession();
			session.setMaxInactiveInterval(10*60);
			session.setAttribute("userAuth", param);
		}

		return new ResponseEntity(new BaseResponse("0001",msg), HttpStatus.OK);
		
	}


	/**
	 * ????????? ?????? ????????? 2
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/complete/json/agree2" , method = RequestMethod.POST)
	@Transactional
	public ResponseEntity jsonAgree2(HttpServletRequest req, HttpServletResponse res , @RequestBody jsonAgreeParam param) throws IOException  {
		log.info("/complete/json/agree2");
		int result = 0;
		try {
			param.setMobile(aes256Util.encrypt(param.getMobile()));
			param.setName(aes256Util.encrypt(param.getName()));
			param.setState(userState.COM);
			param.setUrl(param.getUrl().replace("&", "%26").replace("+", "%2B"));
			//result = philipsClientService.userAgreeUpdate(param);

			result = philipsClientService.userAgreeUpdateAgreement2(param);

			List<sendResultResponse> memberList = philipsClientService.getSendResultMember(param);

			for(sendResultResponse item : memberList) {

				/*
				StringBuffer MsgTxt = new StringBuffer();
				MsgTxt.append("??????????????? ????????? ????????? ???????????? ????????? ?????????.\n");
				MsgTxt.append("\n");
				MsgTxt.append(item.getAgreeMonth()+"??? ???????????? ????????? ??????????????? ????????????????????? ?????? ?????? ????????????.\n");
				MsgTxt.append("\n");
				MsgTxt.append("\n");
				MsgTxt.append("\n");
				MsgTxt.append("[URL ??????]\n");
				MsgTxt.append("https://sleepcare.tckdigital.com/agreeResult?user="+item.getUrl());
				*/


				String mobile = aes256Util.decrypt(item.getMobile());

				//????????? ?????? --- ????????? ??????
				reqKakaoAlimtalk ReqKakao = new reqKakaoAlimtalk();
				philipsResultTemplate template = new philipsResultTemplate();
				ReqKakao.AddMessage(template.createMessage(mobile,item.getAgreeMonth(),"https://healthsystems.tckdigital.com/agreeResult?user="+item.getUrl()));



				// ????????? ??????
				//suremTextService.Send(mobile, MsgTxt.toString(), "????????? ????????? ????????????");
				suremKaKaoService.Send(ReqKakao, mobile);



				//????????? ??????
				reqKakaoAlimtalk ReqKakao2 = new reqKakaoAlimtalk();
				philipsNPSTemplate template2 = new philipsNPSTemplate();
				ReqKakao2.AddMessage(template2.createMessage(mobile,item.getCase_number(),item.getEquipment_name()));


				// ?????? ?????? ?????? ??????
				adminSendLogParam sendLogParam = new adminSendLogParam();
				sendLogParam.setState(item.getState());
				sendLogParam.setUserPkId(item.getUserPkId());
				int logInsert = philipsAdminService.insertSendLog(sendLogParam);



				//????????? ??????
				suremKaKaoService.Send(ReqKakao2, mobile);


			}



		} catch (Exception e) {
			return new ResponseEntity(new BaseResponse("9999","????????? ?????????????????????. ?????? ??????????????????."), HttpStatus.OK);
		}


		if(result != 1) {
			return new ResponseEntity(new BaseResponse("9999","????????? ?????????????????????. ?????? ??????????????????."), HttpStatus.OK);

		}else {
			return new ResponseEntity(new BaseResponse("0001","????????? ??????????????????."), HttpStatus.OK);
		}


	}

	
	
	
	
	/**
	 * ?????? ?????? ?????? ??????
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/complete/json/result" , method = RequestMethod.POST)
	public ResponseEntity jsonResult(HttpServletRequest req, HttpServletResponse res , @RequestBody agreeResultParam param) throws IOException  {
		log.info("/complete/json/result");
		int result = 0;
		
		String user = param.getUrl().replace("&", "%26").replace("+", "%2B");
		/**
		 * ?????? ??????
		 */
		List<agreeUserResultResponse> response = null;
		try {
			param.setName(aes256Util.encrypt(param.getName()));
			param.setMobile(aes256Util.encrypt(param.getMobile()));
			param.setUrl(user);
			response = philipsClientService.getUserInfoResult(param);
			
		} catch (Exception e) {
			return new ResponseEntity(new BaseResponse("9999","????????? ??????????????? ??????????????????."), HttpStatus.OK);
		}
		
		if(response == null || response.size() ==0 ) {
			return new ResponseEntity(new BaseResponse("9999","????????? ??????????????? ??????????????????."), HttpStatus.OK);
		}else {

			return new ResponseEntity(new BaseResponse("0001","/user/result"), HttpStatus.OK);


		}

		
	}

	/**
	 * ?????? ????????? ???????????? - ??????
	 * @param req
	 * @param res
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/member/json/hospital/list")
	public List<hostpitalListResponse> hospitalList(HttpServletRequest req, HttpServletResponse res , hospitalListParam param){
		log.info("/member/json/hospital/list");
		if(param.getName().trim().equals("") && param.getName().isEmpty()){
			return null;
		}

		List<hostpitalListResponse> resultList = philipsClientService.getHospitalList(param);
		return resultList;
	}

}
