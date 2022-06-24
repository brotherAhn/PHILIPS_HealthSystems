package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminSendGroupResponse {
	private String userPkId;
	private String mobile;
	private String requestUrl;
	private userState state;
}
