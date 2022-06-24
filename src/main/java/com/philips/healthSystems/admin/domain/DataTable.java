package com.philips.healthSystems.admin.domain;

import java.util.List;

public class DataTable<T> {
	private int draw;
	private int recordsTotal; // 필터링 전의 총 레코드 수 (예 : 데이터베이스의 총 레코드 수)
	private int recordsFiltered; // 필터링 후의 총 레코드 수 (예 : 필터링 후 적용된 총 레코드 수-이 데이터 페이지에 대해 반환되는 레코드 수가 아니라).
	private List<T> data;
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
