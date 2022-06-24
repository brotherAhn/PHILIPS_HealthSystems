package com.philips.healthSystems.client.response;

import com.philips.healthSystems.admin.response.userState;

import lombok.Data;
@Data
public class sendResultResponse {
	private String userPkId;
	private String mobile;
	private String url;
	private String agreeMonth;
	private userState state;
	private String case_number;
	private String equipment_name;
}
