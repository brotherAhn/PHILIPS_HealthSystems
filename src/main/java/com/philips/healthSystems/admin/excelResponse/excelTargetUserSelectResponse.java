package com.philips.healthSystems.admin.excelResponse;

import com.philips.healthSystems.admin.response.userState;

import lombok.Data;

@Data
public class excelTargetUserSelectResponse {
	private String name;
	private String mobile;
	private String group_no;
	private userState state;
	private String crt_dt;
	private String groupName;
	private String requestUrl;
	private String responseUrl;
	private String customer_no;
	private String stateString;
	private String url;
	private String marketing1_yn;
	private String marketing2_all;
	private String marketing2_tel;
	private String marketing2_sms;
	private String marketing2_dm;
	private String marketing2_email;
	private String sendYN;
	private String send_dt;
	private String agree_dt;
	private String auth_dt;
	private String position;
	private String dept;
	private String hospital;
}
