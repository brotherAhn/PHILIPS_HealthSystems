package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminTargetUserSelectResponse {
	private String name;
	private String mobile;
	private String group_no;
	private userState state;
	private String crt_dt;
	private String groupName;
	private String stateData;
	private String userPkId;
}
