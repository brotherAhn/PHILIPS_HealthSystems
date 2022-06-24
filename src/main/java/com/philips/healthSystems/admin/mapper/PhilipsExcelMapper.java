package com.philips.healthSystems.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.philips.healthSystems.admin.excelDomain.excelGroupInfoParam;
import com.philips.healthSystems.admin.excelDomain.excelTargetUserSelefctParam;
import com.philips.healthSystems.admin.excelResponse.excelGroupInfoResponse;
import com.philips.healthSystems.admin.excelResponse.excelTargetUserSelectResponse;

@Mapper
public interface PhilipsExcelMapper {
	/**
	 * 개인정보 동의서 엑셀 다운로드
	 * @param param
	 * @return
	 */
	List<excelTargetUserSelectResponse> excelTargetUserSelect(excelTargetUserSelefctParam param);
	/**
	 * 대상자 url 엑셀 다운로드
	 * @param param
	 * @return
	 */
	List<excelGroupInfoResponse> excelGroupInfo(excelGroupInfoParam param);

	/**
	 * 신규 동의서 2 엑셀 다운로드
	 * @param param
	 * @return
	 */
    List<excelTargetUserSelectResponse> excelTargetUserSelect2(excelTargetUserSelefctParam param);
}
