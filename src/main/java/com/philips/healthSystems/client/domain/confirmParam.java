package com.philips.healthSystems.client.domain;

import com.philips.healthSystems.admin.response.userState;

import lombok.Data;

@Data
public class confirmParam {
	private String name;
	private String mobile;
	private String userURL;
	private String auth;
	private userState state;
}
