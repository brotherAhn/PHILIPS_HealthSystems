package com.philips.healthSystems.admin.response;

import lombok.Data;

@Data
public class AdminUserResultForPDFResponse {
	
	private String name;
	private String mobile;
	private userState state;
	private String agree_year;
	private String agree_month;
	private String agree_day;
	private String agree_dt;
	private String marketing1_yn;
	private String marketing2_all;
	private String marketing2_tel;
	private String marketing2_sms;
	private String marketing2_dm;
	private String marketing2_email;
	private String file_name;

}
