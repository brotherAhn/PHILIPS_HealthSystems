package com.philips.healthSystems.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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
import com.philips.healthSystems.admin.response.adminGetGroupResponse;
import com.philips.healthSystems.admin.response.adminGroupInfoResponse;
import com.philips.healthSystems.admin.response.adminReSendMemberResponse;
import com.philips.healthSystems.admin.response.adminSendGroupResponse;
import com.philips.healthSystems.admin.response.adminSendListResponse;
import com.philips.healthSystems.admin.response.adminSendResultResponse;
import com.philips.healthSystems.admin.response.adminTargetGroupResponse;
import com.philips.healthSystems.admin.response.adminTargetUserSelectResponse;
import com.philips.healthSystems.admin.response.adminUserDetailResponse;

@Mapper
public interface PhilipsAdminMapper {
	List<Map<String, Object>> adminSelect(Map<String, Object> pMap);
	String getAnswerNo(Map<String, Object> pMap);
	String login(Login param);
	int updateSession(Login param);
	String loginSession(Login param);
	int logout(Login param);

	int insertGroup(adminGroupParam groupParam);
	
	int updateUrl(SurverUserData surverUserData);
	List<adminTargetGroupResponse> targetGroupSelect(adminTargetGroupParam param);
	List<adminGroupInfoResponse> groupInfoSelect(adminGroupInfoParam param);
	
	List<adminTargetUserSelectResponse> targetUserSelect(adminTargetUserSelefctParam param);
	List<adminGetGroupResponse> getGroupList(Map<String, Object> pMap);
	int countPrivateList(adminTargetUserSelefctParam param);
	int countGroupInfo(adminGroupInfoParam param);
	int targetGroupCount(adminTargetGroupParam param);
	Integer countSendHistoy(adminSendListParam param);
	List<adminSendListResponse> sendHistoySelect(adminSendListParam param);
	List<adminUserDetailResponse> userDetailSelect(adminUserDetailParam userNo);
	List<adminSendGroupResponse> getSendGroupMember(adminSendGroupParam param);
	int updateSendState(adminUpdateSendParam sendParam);
	int insertSendLog(adminSendLogParam item);
	int updateGroupSend(adminSendGroupParam param);
	List<adminReSendMemberResponse> getReSendMember(adminReSendMemberParam param);
	List<adminSendResultResponse> getSendResultMember(adminReSendMemberParam param);

	List<adminGetGroupResponse> getGroupListby2(Map<String, Object> pMap);

    List<adminGetGroupResponse> getGroupSendList(Map<String, Object> pMap);

    int insertTargetUserForNPS(SurverUserData surverUserData);
}
