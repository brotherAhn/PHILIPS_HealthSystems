package com.philips.healthSystems.surem.service;


import com.philips.healthSystems.surem.http.SuremRestService;
import com.philips.healthSystems.surem.model.BaseResult;
import com.philips.healthSystems.surem.model.log.reqSuremLog;
import com.philips.healthSystems.surem.model.request.reqKakaoAlimtalk;
import com.philips.healthSystems.surem.model.response.resKakaoAlimtalk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class SuremKaKaoService {
	@Value("${surem.kako.id}")
	private String usercode;

	@Value("${surem.kako.deptcode}")
	private String deptcode;

	@Value("${surem.kako.yellow_key}")
	private String yellowid_key;

	@Value("${surem.kako.from}")
	private String from;

	@Autowired
	private SuremRestService suremRestService;

	@Autowired
	private SuremService suremService;

	@Autowired
	private com.philips.healthSystems.util.AES256Util AES256Util;




	/**
	 * 알림톡 발송
	 * @param ReqData
	 * @return resKakaoAlimtalk
	 */
	public BaseResult Send(reqKakaoAlimtalk ReqData, String to_ori) {
		BaseResult result = new BaseResult();
		result.setResultCode("0000");

		ReqData.setUsercode(usercode);
		ReqData.setDeptcode(deptcode);
		ReqData.setYellowid_key(yellowid_key);

		resKakaoAlimtalk Result = (resKakaoAlimtalk) suremRestService.post("https://api.surem.com/alimtalk/v2/json" , ReqData,(Type)resKakaoAlimtalk.class);
		if(!Result.getCode().equals("200")){
			result.setResultCode("9999");
			result.setResultMsg("알림톡 발송 오류");
		}

		/* 알림톡 로그 저장 */
		if (ReqData.getMessages().size() != 0)
		{
			try{
				reqSuremLog log_param = new reqSuremLog();
				log_param.setMessage_id(ReqData.getMessages().get(0).message_id);
				log_param.setMessage_tyep("KAKAO");
				log_param.setMessage_content(ReqData.getMessages().get(0).text);
				log_param.setTel_to(AES256Util.encrypt(ReqData.getMessages().get(0).to));
				log_param.setTel_to_ori(AES256Util.encrypt(to_ori));
				log_param.setTel_from(ReqData.getMessages().get(0).from);
				suremService.insertLog(log_param);
			}catch (Exception ex){

			}
		}
		/* 알림톡 로그 저장 끝 */
		return  result;
	}


}