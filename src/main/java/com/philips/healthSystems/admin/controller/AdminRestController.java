package com.philips.healthSystems.admin.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.philips.healthSystems.surem.template.philipsNPSTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

import com.philips.healthSystems.admin.domain.DataTable;
import com.philips.healthSystems.admin.domain.Login;
import com.philips.healthSystems.admin.domain.SurverUserData;
import com.philips.healthSystems.admin.domain.adminGroupInfoParam;
import com.philips.healthSystems.admin.domain.adminGroupParam;
import com.philips.healthSystems.admin.domain.adminReSendMemberParam;
import com.philips.healthSystems.admin.domain.adminSendGroupParam;
import com.philips.healthSystems.admin.domain.adminSendListParam;
import com.philips.healthSystems.admin.domain.adminSendLogParam;
import com.philips.healthSystems.admin.domain.adminTargetGroupParam;
import com.philips.healthSystems.admin.domain.adminTargetUserSelefctParam;
import com.philips.healthSystems.admin.domain.adminUpdateSendParam;
import com.philips.healthSystems.admin.domain.adminUserDetailParam;
import com.philips.healthSystems.admin.response.ExcelResult;
import com.philips.healthSystems.admin.response.adminGetGroupResponse;
import com.philips.healthSystems.admin.response.adminGroupInfoResponse;
import com.philips.healthSystems.admin.response.adminReSendMemberResponse;
import com.philips.healthSystems.admin.response.adminSendGroupResponse;
import com.philips.healthSystems.admin.response.adminSendListResponse;
import com.philips.healthSystems.admin.response.adminSendResultResponse;
import com.philips.healthSystems.admin.response.adminTargetGroupResponse;
import com.philips.healthSystems.admin.response.adminTargetUserSelectResponse;
import com.philips.healthSystems.admin.response.adminUserDetailResponse;
import com.philips.healthSystems.admin.response.userState;
import com.philips.healthSystems.admin.service.PhilipsAdminService;
import com.philips.healthSystems.client.response.BaseResponse;
import com.philips.healthSystems.surem.model.request.reqKakaoAlimtalk;
import com.philips.healthSystems.surem.service.SuremKaKaoService;
import com.philips.healthSystems.surem.service.SuremTextService;
import com.philips.healthSystems.surem.template.philipsResultTemplate;
import com.philips.healthSystems.surem.template.philipsSendTemplate;
import com.philips.healthSystems.util.AES256Util;
import com.philips.healthSystems.util.ExcelRead;
import com.philips.healthSystems.util.SHA256;



@Controller
public class AdminRestController {
	@Autowired
	private AES256Util aes256Util;
	
	private Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private PhilipsAdminService philipsAdminService;
	
	@Autowired
	private SuremTextService suremTextService;
	
	@Autowired
	private SuremKaKaoService suremKaKaoService;
	
	@ResponseBody
	@RequestMapping(value = "/do_login" , method = RequestMethod.POST)
	public String do_login(HttpServletRequest req,Login param) {
		SHA256 sh = new SHA256();
		try {
			param.setPw(sh.password_SHA(param.getPw()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		param.setAuth(req.getSession().getId());
		String code = philipsAdminService.login(param);
		if(code.equals("success")) {
			HttpSession session = (HttpSession)req.getSession();
			session.setAttribute("auth", param);
			session.setMaxInactiveInterval(60*30);
			return "success";
		}
		return "fail";
	}
	@ResponseBody
	@RequestMapping(value = "/do_logout" , method = RequestMethod.GET)
	public String do_loout(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = (HttpSession)req.getSession();
		
		Login auth = (Login) session.getAttribute("auth");
		if(auth != null) {
			int str =philipsAdminService.logout(auth);
			if(str == 1) {
				session.removeAttribute("auth");
				return "success";
			}else {
				return "fail";
			}
		}
		return "fail";
	}
	@ResponseBody
	@RequestMapping(value = "/do_sign" , method = RequestMethod.POST)
	public String do_sign(HttpServletRequest req,HttpServletResponse res) {
		return "fail";
	}


	/**
	 * 개인정보 활용 동의서 NPS 용 그룹 업로드
	 * @param req
	 * @param res
	 * @param group_name
	 * @param agreement_type
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/excel_upload2", method = RequestMethod.POST)
	@Transactional
	public ExcelResult ExcelUploadForNPS(HttpServletRequest req, HttpServletResponse res, String group_name, String agreement_type) throws InvalidFormatException, IOException {
		log.debug("/adm/user/ajax/excel_upload2");
		ExcelResult Result = new ExcelResult();

		if (group_name == null || group_name.isEmpty()) {
			Result.setResultCode("9999");
			Result.setResultMsg("그룹 이름을 입력해 주세요.");
			return Result;
		}

		if (agreement_type == null || agreement_type.isEmpty()) {
			Result.setResultCode("9999");
			Result.setResultMsg("동의서 종류를 선택해 주세요.");
			return Result;
		}

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("realfile");
		if (multipartFile.getSize() == 0) {
			Result.setResultCode("9999");
			Result.setResultMsg("파일 업로드 해주세요.");
			return Result;
		}

		String originalFileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		originalFileExtension = originalFileExtension.toLowerCase();

		if (!originalFileExtension.equals(".xls") && !originalFileExtension.equals(".xlsx")) {
			Result.setResultCode("9998");
			Result.setResultMsg("엑셀 파일만 업로드 가능합니다.");
			return Result;
		}
		List<List<Object>> listData = null;

		if (originalFileExtension.equals(".xls")) {
			listData = new ExcelRead().readExcelToListXlS(multipartFile);
		}

		if (originalFileExtension.equals(".xlsx")) {
			listData = new ExcelRead().readExcelToListXlsx(multipartFile);
		}

		adminGroupParam groupParam = new adminGroupParam();
		groupParam.setGroupName(group_name);
		groupParam.setAgreement_type(agreement_type);
		philipsAdminService.insertGroup(groupParam);
		if(groupParam.getGroupPkId() ==0) {
			Result.setResultCode("9998");
			Result.setResultMsg("그룹생성에 실패하였습니다.");
			return Result;
		}

		if (listData.size() != 0) {
			StringBuffer ExcelMsgBuffer = new StringBuffer();
			int index = 1;
			int SuccessCnt = 0;
			int FailureCnt = 0;

			SurverUserData surverUserData ;
			String MobileNum = "";
			String SurveyUserName = "";
			String caseNumber = "";
			String EquipmentName = "";
			String customerNo = "";


			String regExp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$";
			for (List<Object> Data : listData) {
				index++;

				MobileNum = "";
				SurveyUserName = "";
				customerNo = "";

				caseNumber = "";
				EquipmentName = "";

				SurveyUserName = Data.get(0).toString().trim(); //고객명
				MobileNum = Data.get(1).toString().replace("-", "").trim();// 전화번호 1
				caseNumber = Data.get(2).toString().trim(); //case
				EquipmentName =  Data.get(3).toString().trim(); //equiment
				customerNo = Data.get(4).toString().trim(); //고객 번호



				if (!MobileNum.matches(regExp)) {

					ExcelMsgBuffer.append("[실패] 휴대폰 번호가 올바르지 않습니다. - " + index + "행(" + MobileNum + ")<br/>");
					FailureCnt++;
					continue;

				}

				// 이름 정보가 없다면 저장을 하지 않는다.
				if (SurveyUserName == null || SurveyUserName.isEmpty()) {
					ExcelMsgBuffer.append("[실패] 이름이 입력되지 않았습니다. - " + index + "행<br/>");
					FailureCnt++;
					continue;
				}


				// 연락처 정보가 없다면 저장을 하지 않는다.
				if (MobileNum == null || MobileNum.isEmpty()) {
					ExcelMsgBuffer.append("[실패] 연락처가 입력되지 않았습니다. - " + index + "행<br/>");
					FailureCnt++;
					continue;
				}

				// 연락처 정보가 없다면 저장을 하지 않는다.
				if (caseNumber == null || caseNumber.isEmpty()) {
					ExcelMsgBuffer.append("[실패] caseNumber가 입력되지 않았습니다. - " + index + "행<br/>");
					FailureCnt++;
					continue;
				}

				// 연락처 정보가 없다면 저장을 하지 않는다.
				if (EquipmentName == null || EquipmentName.isEmpty()) {
					ExcelMsgBuffer.append("[실패] EquipmentName가 입력되지 않았습니다. - " + index + "행<br/>");
					FailureCnt++;
					continue;
				}
				// 고객 정보가 없다면 저장을 하지 않는다.
				if (MobileNum == null || MobileNum.isEmpty()) {
					ExcelMsgBuffer.append("[실패] 고객번호가 입력되지 않았습니다. - " + index + "행<br/>");
					FailureCnt++;
					continue;
				}
				surverUserData = new SurverUserData();

				//이름 암호화
				try {

					String SurveyUserName_Encrypt = aes256Util.encrypt(SurveyUserName);
					surverUserData.setSurveyUserName(SurveyUserName_Encrypt);


				} catch (Exception ex) {
					ExcelMsgBuffer.append("[실패] 이름 암호화 오류입니다 - " + index + "행(" + SurveyUserName + ")<br/>");
					FailureCnt++;
					continue;
				}


				//연락처를 암호화 및 유효성 검사
				try {
					String MobileNum_Encrypt = aes256Util.encrypt(MobileNum);
					surverUserData.setSurveyUserTel(MobileNum_Encrypt);

				} catch (Exception ex) {
					ExcelMsgBuffer.append("[실패] 연락처 오류입니다 - " + index + "행(" + MobileNum + ")<br/>");
					FailureCnt++;
					continue;
				}
				surverUserData.setCustomerNo(customerNo);
				surverUserData.setGroupPkId(groupParam.getGroupPkId());
				surverUserData.setState(userState.NSE);
				surverUserData.setCaseNumber(caseNumber);
				surverUserData.setEquipmentName(EquipmentName);
				philipsAdminService.insertTargetUserForNPS(surverUserData);



				//urveyUserPkid 암호화 하여 Url로 구분 한다.
				try {
					surverUserData.setUrl(aes256Util.encrypt(surverUserData.getUserPkid().toString()));
					String url = surverUserData.getUrl();
					surverUserData.setUrl(url);
					surverUserData.setSurveyUrl("https://healthsystems.tckdigital.com/index?user=" + surverUserData.getUrl());
				} catch (Exception ex) {
					ExcelMsgBuffer.append("[실패] 저장 중 오류 발생 - " + index + "행(" + MobileNum + ")<br/>");
					FailureCnt++;
					continue;
				}

				int urlResult = philipsAdminService.updateUrl(surverUserData);
				SuccessCnt++;

			} // 반복문 종료

			Result.setResultCode("0000");
			Result.setResultMsg("정상");
			Result.setSuccessCnt(SuccessCnt);
			Result.setFailureCnt(FailureCnt);
			Result.setExcelMsg(ExcelMsgBuffer.toString());

		} else {
			Result.setExcelMsg("데이터가 존재하지 않습니다.");
		}


		return Result;
	}


	@ResponseBody
	@RequestMapping(value = "/ajax/get/group/sendList" , method = RequestMethod.POST)
	public List<adminGetGroupResponse> getGroupSendList(HttpServletRequest req) {
		List<adminGetGroupResponse> data = philipsAdminService.getGroupSendList(null);
		return data;
	}
	@ResponseBody
	@RequestMapping(value = "/ajax/get/groupby2" , method = RequestMethod.POST)
	public List<adminGetGroupResponse> getGroupby2(HttpServletRequest req) {
		List<adminGetGroupResponse> data = philipsAdminService.getGroupListby2(null);
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/ajax/get/user/detail")
	public List<adminUserDetailResponse> getUserDetail(HttpServletRequest req, adminUserDetailParam param) {
		
		List<adminUserDetailResponse> data = philipsAdminService.userDetailSelect(param);
		List<adminUserDetailResponse> result = new ArrayList<adminUserDetailResponse>();
		for(adminUserDetailResponse item : data) {
			adminUserDetailResponse temp = item;
			
			try {
				temp.setStateString(item.getState().getDesc());
				temp.setName(aes256Util.decrypt(item.getName()));
				temp.setMobile(aes256Util.decrypt(item.getMobile()));
				if(item.getState()==userState.COM) {
					temp.setResponseUrl("https://healthsystems.tckdigital.com/agreeResult?user="+item.getUrl());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			result.add(temp);
			
		}
		
		return result;
	}


	@ResponseBody
	@RequestMapping("/ajax/agreement/list")
	public DataTable<adminTargetUserSelectResponse> admin_agreement(HttpServletRequest req,adminTargetUserSelefctParam param) {
		log.info("/ajax/agreement/list");
		param.setTargetId("2");
		try {
			if(param != null) {
				if(param.getMobile()!=null) {
					param.setMobile(aes256Util.encrypt(param.getMobile()));
				}
				if(param.getName()!=null) {
					param.setName(aes256Util.encrypt(param.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}


		int dataCount = philipsAdminService.countPrivateList(param);
		DataTable<adminTargetUserSelectResponse> result = new DataTable<adminTargetUserSelectResponse>();


		List<adminTargetUserSelectResponse> data = philipsAdminService.targetUserSelect(param);
		List<adminTargetUserSelectResponse> decryptDataList = new ArrayList<adminTargetUserSelectResponse>();

		for(adminTargetUserSelectResponse info : data) {
			adminTargetUserSelectResponse decryptData = new adminTargetUserSelectResponse();
			try {
				decryptData.setName(aes256Util.decrypt(info.getName()));
				decryptData.setMobile(aes256Util.decrypt(info.getMobile()));
				decryptData.setState(info.getState());
				decryptData.setStateData(info.getState().getDesc());
				decryptData.setCrt_dt(info.getCrt_dt());
				decryptData.setGroupName(info.getGroupName());
				decryptData.setUserPkId(info.getUserPkId());
				decryptDataList.add(decryptData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.toString());
			}

		}

		result.setDraw(param.getDraw());
		result.setData(decryptDataList);
		result.setRecordsFiltered(dataCount);
		result.setRecordsTotal(dataCount);


		return result;
	}

	@ResponseBody
	@RequestMapping("/ajax/groupInfo/list")
	public DataTable<adminGroupInfoResponse> groupInfo(HttpServletRequest req,adminGroupInfoParam param) {
		log.info("/ajax/groupInfo/list");
		
		
		int dataCount = philipsAdminService.countGroupInfo(param); 
		
		DataTable<adminGroupInfoResponse> result = new DataTable<adminGroupInfoResponse>();
		
		
		
		
		List<adminGroupInfoResponse> data =philipsAdminService.groupInfoSelect(param);
		List<adminGroupInfoResponse> decryptDataList = new ArrayList<adminGroupInfoResponse>(); 
		
		for(adminGroupInfoResponse info : data) {
			adminGroupInfoResponse decryptData = new adminGroupInfoResponse();
			try {
				decryptData.setName(aes256Util.decrypt(info.getName()));
				decryptData.setMobile(aes256Util.decrypt(info.getMobile()));
				decryptData.setRequestUrl(info.getRequestUrl());
				decryptDataList.add(decryptData);
			} catch (Exception e) {

				e.printStackTrace();
			} 
			
		}

		result.setDraw(param.getDraw());
		result.setData(decryptDataList);
		result.setRecordsFiltered(dataCount);
		result.setRecordsTotal(dataCount);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/member/ajax/usergroup")
	public DataTable<adminTargetGroupResponse> usergroup(HttpServletRequest req,adminTargetGroupParam param) {
		log.info("/member/ajax/usergroup");
		
		DataTable<adminTargetGroupResponse> result = new DataTable<adminTargetGroupResponse>();
		
		int dataCount = philipsAdminService.targetGroupCount(param);
		
		List<adminTargetGroupResponse> data= philipsAdminService.targetGroupSelect(param);

		result.setDraw(param.getDraw());
		result.setData(data);
		result.setRecordsFiltered(dataCount);
		result.setRecordsTotal(dataCount);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/ajax/send/list")
	public DataTable<adminSendListResponse> sendList(HttpServletRequest req,adminSendListParam param) {
		log.info("/ajax/send/list");
		
		try {
			if(param != null) {
				if(param.getMobile()!=null) {
					param.setMobile(aes256Util.encrypt(param.getMobile()));
				}
				if(param.getName()!=null) {
					param.setName(aes256Util.encrypt(param.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		
		Integer dataCount = philipsAdminService.countSendHistoy(param);
		
		DataTable<adminSendListResponse> result = new DataTable<adminSendListResponse>();
		if(dataCount == null) {
			return result;
		}
		
		
		List<adminSendListResponse> data = philipsAdminService.sendHistoySelect(param);
		List<adminSendListResponse> decryptDataList = new ArrayList<adminSendListResponse>(); 
		
		for(adminSendListResponse info : data) {
			adminSendListResponse decryptData = new adminSendListResponse();
			try {
				decryptData.setName(aes256Util.decrypt(info.getName()));
				decryptData.setMobile(aes256Util.decrypt(info.getMobile()));
				decryptData.setStateData(info.getState().getDesc());
				decryptData.setCrt_dt(info.getCrt_dt());
				decryptData.setGroup_no(info.getGroup_no());
				decryptData.setGroupName(info.getGroupName());
				decryptDataList.add(decryptData);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.toString());
			} 
			
		}
		
		result.setDraw(param.getDraw());
		result.setData(decryptDataList);
		result.setRecordsFiltered(dataCount);
		result.setRecordsTotal(dataCount);
		
		
		return result;
	}
	
	/**
	 * 알림톡 그룹 발송
	 * @param req
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/ajax/alimtalk/send/group", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity alimtalkSendGroup(HttpServletRequest req, @RequestBody adminSendGroupParam param) {
		log.info("/ajax/alimtalk/send/group");
		List<adminSendGroupResponse> memberList = philipsAdminService.getSendGroupMember(param);
		try {

			for(adminSendGroupResponse item : memberList) {
				
				
				String mobile = aes256Util.decrypt(item.getMobile());
				
				//알림톡 세팅
				reqKakaoAlimtalk ReqKakao = new reqKakaoAlimtalk();
				philipsSendTemplate template = new philipsSendTemplate();
				ReqKakao.AddMessage(template.createMessage(mobile,item.getRequestUrl()));
				
				
				
				// 유저 발송 로그 입력
				adminSendLogParam sendLogParam = new adminSendLogParam();
				sendLogParam.setState(item.getState());
				sendLogParam.setUserPkId(item.getUserPkId());
				int logInsert = philipsAdminService.insertSendLog(sendLogParam);
				
				
				// 유저 발송 상태 업데이트
				adminUpdateSendParam sendParam = new adminUpdateSendParam();
				sendParam.setSendYN("Y");
				sendParam.setState(userState.NOT);
				sendParam.setUserPkId(item.getUserPkId());
				int success = philipsAdminService.updateSendState(sendParam);

				
				// 알림톡 발송
				suremKaKaoService.Send(ReqKakao, mobile);
			}
			//그룹 상태 업데이트
			int updateGroup = philipsAdminService.updateGroupSend(param);
			
		} catch (Exception ex) {
			return new ResponseEntity(new BaseResponse("9999","발송에 실패했습니다."), HttpStatus.OK);
		}
		
		return new ResponseEntity(new BaseResponse("0001","발송에 성공했습니다."), HttpStatus.OK);

	}
	
	
	/**
	 * 알림톡 맴버 재발송
	 * @param req
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/ajax/alimtalk/resend/member", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity alimtalkReSendMember(HttpServletRequest req, @RequestBody adminReSendMemberParam param) {
		log.info("/ajax/resend/member");
		List<adminReSendMemberResponse> memberList = philipsAdminService.getReSendMember(param);
		try {
			
			for(adminReSendMemberResponse item : memberList) {
				
				String mobile = aes256Util.decrypt(item.getMobile());
				
				//알림톡 세팅
				reqKakaoAlimtalk ReqKakao = new reqKakaoAlimtalk();
				philipsSendTemplate template = new philipsSendTemplate();
				ReqKakao.AddMessage(template.createMessage(mobile,item.getRequestUrl()));
				
				// 유저 발송 로그 입력
				adminSendLogParam sendLogParam = new adminSendLogParam();
				sendLogParam.setState(item.getState());
				sendLogParam.setUserPkId(item.getUserPkId());
				int logInsert = philipsAdminService.insertSendLog(sendLogParam);
				
				//알림톡 발송
				suremKaKaoService.Send(ReqKakao, mobile);

			}
			
		} catch (Exception ex) {
			return new ResponseEntity(new BaseResponse("9999","발송에 실패했습니다."), HttpStatus.OK);
		}
		
		return new ResponseEntity(new BaseResponse("0001","발송에 성공했습니다."), HttpStatus.OK);

	}
	
	
	
	/**
	 * 알림톡 결과 발송
	 * @param req
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/ajax/alimtalk/send/result", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity alimtalkSendResult(HttpServletRequest req, @RequestBody adminReSendMemberParam param) {
		log.info("/ajax/send/result");
		List<adminSendResultResponse> memberList = philipsAdminService.getSendResultMember(param);
		try {
			
			for(adminSendResultResponse item : memberList) {
				String mobile = aes256Util.decrypt(item.getMobile());
				
				
				//알림톡 세팅
				reqKakaoAlimtalk ReqKakao = new reqKakaoAlimtalk();
				philipsResultTemplate template = new philipsResultTemplate();
				ReqKakao.AddMessage(template.createMessage(mobile,item.getAgreeMonth(),"https://healthsystems.tckdigital.com/agreeResult?user="+item.getUrl()));
				
				

				
				//알림톡 발송
				suremKaKaoService.Send(ReqKakao, mobile);


				//알림톡 세팅
				reqKakaoAlimtalk ReqKakao2 = new reqKakaoAlimtalk();
				philipsNPSTemplate template2 = new philipsNPSTemplate();
				ReqKakao2.AddMessage(template2.createMessage(mobile,item.getCase_number(),item.getEquipment_name()));






				//알림톡 발송
				suremKaKaoService.Send(ReqKakao2, mobile);

				// 유저 발송 로그 입력
				adminSendLogParam sendLogParam = new adminSendLogParam();
				sendLogParam.setState(item.getState());
				sendLogParam.setUserPkId(item.getUserPkId());
				int logInsert = philipsAdminService.insertSendLog(sendLogParam);
			}
			
		} catch (Exception ex) {
			return new ResponseEntity(new BaseResponse("9999","발송에 실패했습니다."), HttpStatus.OK);
		}
		
		return new ResponseEntity(new BaseResponse("0001","발송에 성공했습니다."), HttpStatus.OK);
		
	}



	
	
	
	
}
