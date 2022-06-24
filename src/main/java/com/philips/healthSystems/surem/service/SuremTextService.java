package com.philips.healthSystems.surem.service;


import com.philips.healthSystems.surem.http.SuremRestService;
import com.philips.healthSystems.surem.model.BaseResult;
import com.philips.healthSystems.surem.model.log.reqSuremLog;
import com.philips.healthSystems.surem.model.request.reqSuremLms;
import com.philips.healthSystems.surem.model.request.reqSuremSms;
import com.philips.healthSystems.surem.model.response.resSuremLms;
import com.philips.healthSystems.surem.model.response.resSuremSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class SuremTextService {
	@Value("${surem.text.id}")
	private String usercode;

	@Value("${surem.text.deptcode}")
	private String deptcode;

	@Value("${surem.text.yellow_key}")
	private String yellowid_key;

	@Value("${surem.text.from}")
	private String from;

	@Autowired
	private SuremRestService suremRestService;

	@Autowired
	private SuremService suremService;

	@Autowired
	private com.philips.healthSystems.util.AES256Util AES256Util;

	/**
	 * 알림톡 발송
	 * @return resKakaoAlimtalk
	 */
	public BaseResult Send(String to, String text, String subject) {
		BaseResult result = new BaseResult();
		result.setResultCode("0000");


		int MsgSize = 0;
		reqSuremLog log_param = new reqSuremLog();
		String Message_id = "";
		String Message_tyep = "";
		to = to.replaceAll("-", "");

		if(text.getBytes().length < 90){
			reqSuremSms sms_param = new reqSuremSms();
			sms_param.setUsercode(usercode);
			sms_param.setDeptcode(deptcode);

			sms_param.setFrom(from);
			sms_param.setText(text);
			sms_param.SetMessage(to);
			resSuremSms Result = (resSuremSms) suremRestService.post("https://api.surem.com/sms/v1/json" , sms_param,(Type)resSuremSms.class);
			if(!Result.getCode().equals("200")){
				result.setResultCode("9999");
				result.setResultMsg("문자발송 오류(SMS)");
			}


			MsgSize = sms_param.getMessages().size();
			if(MsgSize != 0) {
				Message_id = Result.getResults().get(0).getMessage_id();
				log_param.setMessage_title(subject);
				Message_tyep = "SMS";
			}
		}else {

			reqSuremLms lms_param = new reqSuremLms();
			lms_param.setUsercode(usercode);
			lms_param.setDeptcode(deptcode);

			lms_param.setFrom(from);
			lms_param.setText(text);
			lms_param.SetMessage(to);
			lms_param.setSubject(subject);

			resSuremLms Result = (resSuremLms) suremRestService.post("https://api.surem.com/lms/v1/json" , lms_param,(Type)resSuremLms.class);
			if(!Result.getCode().equals("200")){
				result.setResultCode("9999");
				result.setResultMsg("문자발송 오류(LMS)");
			}

			MsgSize = lms_param.getMessages().size();
			if(MsgSize != 0) {
				Message_id = Result.getResults().get(0).getMessage_id();
				log_param.setMessage_title(subject);
				Message_tyep = "LMS";
			}
		}

		/* 로그 저장 */
		if (MsgSize != 0)
		{
			try{
				log_param.setMessage_id(Message_id);
				log_param.setMessage_tyep(Message_tyep);
				log_param.setMessage_content(text);
				log_param.setTel_to(AES256Util.encrypt(to));
				log_param.setTel_to_ori(AES256Util.encrypt(to));
				log_param.setTel_from(from);
				suremService.insertLog(log_param);
			}catch (Exception ex){

			}
		}
		/* 로그 저장 */

		return result;

	}

}