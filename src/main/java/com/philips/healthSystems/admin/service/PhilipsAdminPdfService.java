package com.philips.healthSystems.admin.service;

import com.philips.healthSystems.admin.mapper.PhilipsAdminPdfMapper;
import com.philips.healthSystems.admin.response.AdminUserResultForPDFResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhilipsAdminPdfService {
	@Autowired
	private PhilipsAdminPdfMapper philipsAdminPdfMapper;

	
	private Log log = LogFactory.getLog(PhilipsAdminPdfService.class);

	/**
	 * 고객 확인서 카운트
	 * @param param
	 * @return
	 */
	public int countUserInfoResult(int param) {
		return philipsAdminPdfMapper.countUserInfoResult(param);
	}

	/**
	 * 고객 확인서 정보
	 * @param param
	 * @return
	 */
	public AdminUserResultForPDFResponse getUserInfoResult(int param) {
		return philipsAdminPdfMapper.getUserInfoResult(param);
	}
}



	
	
