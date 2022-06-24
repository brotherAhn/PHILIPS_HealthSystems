package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminSendListResponse {
	private String name;
	private String mobile;
	private userState state;
	private String stateData;
	private String crt_dt;
	private String group_no;
	private String groupName;
}
