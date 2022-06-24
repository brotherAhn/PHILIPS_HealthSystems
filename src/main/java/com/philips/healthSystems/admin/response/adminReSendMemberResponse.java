package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminReSendMemberResponse {
	private String userPkId;
	private String mobile;
	private String requestUrl;
	private userState state;
}
