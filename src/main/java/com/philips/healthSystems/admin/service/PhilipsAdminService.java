package com.philips.healthSystems.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.philips.healthSystems.admin.mapper.PhilipsAdminMapper;
import com.philips.healthSystems.admin.response.adminGetGroupResponse;
import com.philips.healthSystems.admin.response.adminGroupInfoResponse;
import com.philips.healthSystems.admin.response.adminReSendMemberResponse;
import com.philips.healthSystems.admin.response.adminSendGroupResponse;
import com.philips.healthSystems.admin.response.adminSendListResponse;
import com.philips.healthSystems.admin.response.adminSendResultResponse;
import com.philips.healthSystems.admin.response.adminTargetGroupResponse;
import com.philips.healthSystems.admin.response.adminTargetUserSelectResponse;
import com.philips.healthSystems.admin.response.adminUserDetailResponse;
import com.philips.healthSystems.util.AES256Util;

@Service
public class PhilipsAdminService {
	@Autowired
	private PhilipsAdminMapper philipsAdminMapper;
	@Autowired
	private AES256Util aes256Util;
	
	private Log log = LogFactory.getLog(PhilipsAdminService.class);
	


	public String login(Login param) {
		String code = philipsAdminMapper.login(param);
		log.info("----------------"+code);
		if(code != null && code.trim()!="") {
			int upd = loginUpd(param);
			log.info("----------------"+param.getAuth()+"-------+++++");
			if(upd != 0) {
				log.info("----------------"+upd);
				return "success";
			}
			return "fail";
		}
		return "fail";
	}
	public int loginUpd(Login param) {
		return philipsAdminMapper.updateSession(param);
	}
	public String auth(Login param) {
		return philipsAdminMapper.loginSession(param);
	}
	public int logout(Login param) {
		return philipsAdminMapper.logout(param);
	}

	/**
	 * 그룹 생성
	 * @param group_name
	 */
	public int insertGroup(adminGroupParam groupParam) {
		return philipsAdminMapper.insertGroup(groupParam);
	}

	/**
	 * URL 생성
	 * @param surverUserData
	 */
	public int updateUrl(SurverUserData surverUserData) {
		return philipsAdminMapper.updateUrl(surverUserData);
	}
	/**
	 * 대상 관리 페이지
	 * @param param
	 * @return
	 */
	public List<adminTargetGroupResponse> targetGroupSelect(adminTargetGroupParam param){
		return philipsAdminMapper.targetGroupSelect(param);
	}
	
	/**
	 * 대상 상세보기
	 * @param groupId
	 * @return
	 */
	public List<adminGroupInfoResponse> groupInfoSelect(adminGroupInfoParam param){
		return philipsAdminMapper.groupInfoSelect(param);
	
	}
	/**
	 * 개인정보 동의서 동의 목록
	 * @param param
	 * @return
	 */
	public List<adminTargetUserSelectResponse> targetUserSelect(adminTargetUserSelefctParam param){
		
		return philipsAdminMapper.targetUserSelect(param);
	}
	/**
	 * 그룹 리스트
	 */
	public List<adminGetGroupResponse> getGroupList(Map<String, Object> pMap){
		
		return philipsAdminMapper.getGroupList(null);
		
	}
	/**
	 * 개인정보 동의서 리스트 카운트
	 * @param param
	 * @return
	 */
	public int countPrivateList(adminTargetUserSelefctParam param) {
		return philipsAdminMapper.countPrivateList(param);
	}
	
	/**
	 * 그룹 정보 리스트 카운트
	 * @param param
	 * @return
	 */
	public int countGroupInfo(adminGroupInfoParam param) {

		return philipsAdminMapper.countGroupInfo(param);
	}
	/**
	 * 그룹 카운트
	 * @param param
	 * @return
	 */
	public int targetGroupCount(adminTargetGroupParam param) {
		return philipsAdminMapper.targetGroupCount(param);
	}
	/**
	 * 발송 이력 카운트
	 * @param param
	 * @return
	 */
	public Integer countSendHistoy(adminSendListParam param) {
		return philipsAdminMapper.countSendHistoy(param);
	}
	/**
	 * 발송 이력 목록
	 * @param param
	 * @return
	 */
	public List<adminSendListResponse> sendHistoySelect(adminSendListParam param) {
		return philipsAdminMapper.sendHistoySelect(param);
	}
	public List<adminUserDetailResponse> userDetailSelect(adminUserDetailParam param) {
		return philipsAdminMapper.userDetailSelect(param);
	}
	/**
	 * 발송 대상자 그룹 맴버 
	 * @param param
	 * @return
	 */
	public List<adminSendGroupResponse> getSendGroupMember(adminSendGroupParam param) {
		return philipsAdminMapper.getSendGroupMember(param);
	}
	/**
	 * 발송 대상자 상태 업데이트
	 * @param sendParam
	 * @return
	 */
	public int updateSendState(adminUpdateSendParam sendParam) {
		return philipsAdminMapper.updateSendState(sendParam);
	}
	/**
	 * 그룹 상태 업데이트
	 * @param param
	 * @return
	 */
	public int updateGroupSend(adminSendGroupParam param) {
		return philipsAdminMapper.updateGroupSend(param);
	}
	/**
	 * 유저 로그 생성
	 * @param item 
	 * @return
	 */
	public int insertSendLog(adminSendLogParam item) {
		return philipsAdminMapper.insertSendLog(item);
	}
	/**
	 * 재발송 대상자 정보
	 * @param param
	 * @return
	 */
	public List<adminReSendMemberResponse> getReSendMember(adminReSendMemberParam param) {
		return philipsAdminMapper.getReSendMember(param);
	}
	/**
	 * 확인서 재발송
	 * @param param
	 * @return
	 */
	public List<adminSendResultResponse> getSendResultMember(adminReSendMemberParam param) {
		return philipsAdminMapper.getSendResultMember(param);
	}

	/**
	 * 동의서 2번 대상 그룹 불러오기
	 * @param o
	 * @return
	 */
	public List<adminGetGroupResponse> getGroupListby2(Map<String, Object> pMap) {
		return philipsAdminMapper.getGroupListby2(null);
	}

	/**
	 * 발송이력 리스트
	 * @param o
	 * @return
	 */
    public List<adminGetGroupResponse> getGroupSendList(Map<String, Object> pMap) {
		return philipsAdminMapper.getGroupSendList(null);
    }

	/**
	 * 엑셀 업로드 타겟 그룹 NPS
	 * @param surverUserData
	 */
	public int insertTargetUserForNPS(SurverUserData surverUserData) {
		return philipsAdminMapper.insertTargetUserForNPS(surverUserData);
	}
}



	
	
