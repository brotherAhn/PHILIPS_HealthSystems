package com.philips.healthSystems.client.service;



import java.util.List;

import com.philips.healthSystems.client.domain.*;
import com.philips.healthSystems.client.response.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.healthSystems.client.mapper.PhilipsMapper;

@Service
public class PhilipsClientService {
	@Autowired
	private PhilipsMapper philipsMapper;
	
	private Log log = LogFactory.getLog(PhilipsClientService.class);
	

	/**
	 * 유저 본인 확인
	 * @param param
	 * @return
	 */
	public List<confirmResponse> getConfiramUser(confirmParam param) {
		return philipsMapper.getConfiramUser(param);
	}
	/**
	 * 본인확인을 거친 유저인지 확인
	 * @param auth
	 * @return
	 */
	public String auth(confirmParam auth) {
		// 
		return philipsMapper.getUserAuth(auth);
	}
	/**
	 * 유저 인증
	 * @param param
	 * @return
	 */
	public int setUserAuth(confirmParam param) {
		return philipsMapper.setUserAuth(param);
	}
	/**
	 * 유저 URL 체크
	 * @param user
	 * @return
	 */
	public String getUserPkId(String user) {
		return philipsMapper.getUserPkId(user);
	}
	/**
	 * 유저 정보 불러오기
	 * @param user
	 * @return
	 */
	public List<agreeUserResponse> getUserInfo(String user) {
		return philipsMapper.getUserInfo(user);
	}

	/**
	 * 결과보기 url, 상태 확인
	 * @param user
	 * @return
	 */
	public String getUserPkIdForResult(String user) {
		return philipsMapper.getUserPkIdForResult(user);
	}
	/**
	 * 결과보기 최종 데이터
	 * @param user
	 * @return
	 */
	public List<agreeUserResultResponse> getUserInfoResult(agreeResultParam user) {
		return philipsMapper.getUserInfoResult(user);
	}
	/**
	 * 유저 결과 전송
	 * @param param
	 * @return
	 */
	public List<sendResultResponse> getSendResultMember(jsonAgreeParam param) {
		return philipsMapper.getSendResultMember(param);
	}

	/**
	 * 병원 리스트 - 검색
	 * @param param
	 * @return
	 */
    public List<hostpitalListResponse> getHospitalList(hospitalListParam param) {
		return philipsMapper.getHospitalList(param);
    }

	/**
	 * 동의서 2 동의 업데이트
	 * @param param
	 * @return
	 */
	public int userAgreeUpdateAgreement2(jsonAgreeParam param) {
		return philipsMapper.userAgreeUpdateAgreement2(param);
	}
}



	
	
