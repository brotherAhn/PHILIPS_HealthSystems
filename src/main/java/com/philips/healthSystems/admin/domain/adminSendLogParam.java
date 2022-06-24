package com.philips.healthSystems.admin.domain;

import com.philips.healthSystems.admin.response.userState;

import lombok.Data;

@Data
public class adminSendLogParam {
	private String userPkId;
	private userState state;
	
}
