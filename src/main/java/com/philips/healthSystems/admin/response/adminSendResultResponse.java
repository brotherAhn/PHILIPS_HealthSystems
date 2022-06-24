package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminSendResultResponse {
	private String userPkId;
	private String mobile;
	private String url;
	private String agreeMonth;
	private userState state;
	private String case_number;
	private String equipment_name;
}
