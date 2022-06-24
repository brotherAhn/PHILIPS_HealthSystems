package com.philips.healthSystems.admin.domain;

import com.philips.healthSystems.admin.response.userState;

import lombok.Data;

@Data
public class adminUpdateSendParam {
	private String userPkId;
	private userState state;
	private String sendYN;
}
