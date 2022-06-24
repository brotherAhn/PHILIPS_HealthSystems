package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminUserDetailResponse {
	private String name;
	private String mobile;
	private String requestUrl;
	private String responseUrl;
	private String group_no;
	private String customer_no;
	private userState state;
	private String stateString;
	private String url;
	private String marketing1_yn;
	private String marketing2_all;
	private String marketing2_tel;
	private String marketing2_sms;
	private String marketing2_dm;
	private String marketing2_email;
	private String crt_dt;
	private String sendYN;
	private String send_dt;
	private String agree_dt;
	private String userAuthCode;
	private String auth_dt;
	private String groupName;
	private String targetId;
	private String position;
	private String dept;
	private String hospital;
}
