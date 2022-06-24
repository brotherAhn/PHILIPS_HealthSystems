package com.philips.healthSystems.admin.domain;

import lombok.Data;

@Data
public class adminSendParam {
	private String name;
	private String mobile;
	private String state;
	private String group_no;
	private String start_dt;
	private String end_dt;
	private String crt_dt;
	private int start;
	private int length;
	private int draw;
	private int pageNo = 0;
	private String isLimit = "1";
}
