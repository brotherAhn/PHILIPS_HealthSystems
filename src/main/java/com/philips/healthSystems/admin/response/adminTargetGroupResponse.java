package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class adminTargetGroupResponse {
	private Integer GroupPkId;
	private String name;
	private String crt_dt;
	private String targetId;
	private String groupState;
}
