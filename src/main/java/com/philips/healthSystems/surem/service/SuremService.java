package com.philips.healthSystems.surem.service;


import com.philips.healthSystems.surem.mapper.SuremMapper;
import com.philips.healthSystems.surem.model.log.reqSuremLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuremService {

	@Autowired
	private SuremMapper suremMapper;

	/*
	 * 로그 저장
	 * @param reqSuremLog
	 * @return int
	 */
	public int insertLog(reqSuremLog reqSuremLog) {
		return suremMapper.insertLog(reqSuremLog);
	}

}