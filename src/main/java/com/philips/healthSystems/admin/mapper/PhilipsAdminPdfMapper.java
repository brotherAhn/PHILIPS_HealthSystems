package com.philips.healthSystems.admin.mapper;

import com.philips.healthSystems.admin.response.AdminUserResultForPDFResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhilipsAdminPdfMapper {
	/**
	 * 고객 확인서 카운트
	 * @param param
	 * @return
	 */
	int countUserInfoResult(int param);

	/**
	 * 고객 확인서 정보
	 * @param param
	 * @return
	 */
	AdminUserResultForPDFResponse getUserInfoResult(int param);

}
