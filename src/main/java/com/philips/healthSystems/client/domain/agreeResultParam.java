package com.philips.healthSystems.client.domain;

import com.philips.healthSystems.admin.response.userState;

import lombok.Data;

@Data
public class agreeResultParam {
	private String name;
	private String mobile;
	private String url;
	private userState state;
	private String marketing1_yn;
	private String marketing2_all;
	private String marketing2_tel;
	private String marketing2_sms;
	private String marketing2_dm;
	private String marketing2_email;
	
}
