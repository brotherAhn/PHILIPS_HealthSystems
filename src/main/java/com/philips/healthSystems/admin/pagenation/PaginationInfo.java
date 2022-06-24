package com.philips.healthSystems.admin.pagenation;

import lombok.Data;

@Data
public class PaginationInfo {
	private int start;
	private int length;
	private int draw;
	private int pageNo = 0;
	private String isLimit = "1";
}
