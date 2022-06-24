package com.philips.healthSystems.admin.domain;

import lombok.Data;

@Data
public class adminGroupInfoParam {
	private String groupNo;
	private int start;
	private int length;
	private int draw;
	private int pageNo = 0;
	private String isLimit = "1";
}
