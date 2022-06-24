package com.philips.healthSystems.client.mapper;


import java.util.List;

import com.philips.healthSystems.client.domain.*;
import com.philips.healthSystems.client.response.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhilipsMapper {

	

	/**
	 * 유저 본인확인
	 * @param param
	 * @return
	 */
	List<confirmResponse> getConfiramUser(confirmParam param);
	/**
	 * 본인확인 받은 유저인지 확인
	 * @param auth
	 * @return
	 */
	String getUserAuth(confirmParam auth);
	/**
	 * 본인 확인
	 * @param param
	 * @return
	 */
	int setUserAuth(confirmParam param);
	/**
	 * 유저 url 확인
	 * @param user
	 * @return
	 */
	String getUserPkId(String user);
	/**
	 * 유저 정보 확인
	 * @param user
	 * @return
	 */
	List<agreeUserResponse> getUserInfo(String user);

	/**
	 * 결과보기 url, 상태 확인
	 * @param user
	 * @return
	 */
	String getUserPkIdForResult(String user);
	/**
	 * 결과보기 최종 데이터
	 * @param user
	 * @return
	 */
	List<agreeUserResultResponse> getUserInfoResult(agreeResultParam user);
	/**
	 * 결과 전송
	 * @param param
	 * @return
	 */
	List<sendResultResponse> getSendResultMember(jsonAgreeParam param);
	/**
	 * 병원 리스트
	 */
	List<hostpitalListResponse> getHospitalList(hospitalListParam param);

	/**
	 * 동의서 2 동의서 업데이트
	 * @param param
	 * @return
	 */
	int userAgreeUpdateAgreement2(jsonAgreeParam param);
}
