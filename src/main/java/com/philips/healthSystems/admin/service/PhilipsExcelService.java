package com.philips.healthSystems.admin.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.healthSystems.admin.excelDomain.excelGroupInfoParam;
import com.philips.healthSystems.admin.excelDomain.excelTargetUserSelefctParam;
import com.philips.healthSystems.admin.excelResponse.excelGroupInfoResponse;
import com.philips.healthSystems.admin.excelResponse.excelTargetUserSelectResponse;
import com.philips.healthSystems.admin.mapper.PhilipsExcelMapper;
import com.philips.healthSystems.util.AES256Util;

@Service
public class PhilipsExcelService {
	@Autowired
	private PhilipsExcelMapper philipsExcelMapper;
	@Autowired
	private AES256Util aes256Util;
	
	private Log log = LogFactory.getLog(PhilipsExcelService.class);


	/**
	 * 대상자 url 엑셀 다운로드
	 * @param param
	 * @return
	 */
	public List<excelGroupInfoResponse> excelGroupInfo(excelGroupInfoParam param) {
		return philipsExcelMapper.excelGroupInfo(param);
	}

	/**
	 * 신규 동의서 2 엑셀 다운로드
	 * @param param
	 * @return
	 */
    public List<excelTargetUserSelectResponse> excelTargetUserSelect2(excelTargetUserSelefctParam param) {
		return philipsExcelMapper.excelTargetUserSelect2(param);
    }
}



	
	
