package com.philips.healthSystems.client.response;

import lombok.Data;

@Data
public class BaseResponse {
	private String code = "0000";
	private String msg;
	
	
	public BaseResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
