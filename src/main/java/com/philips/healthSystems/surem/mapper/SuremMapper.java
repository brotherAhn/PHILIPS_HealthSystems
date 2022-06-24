package com.philips.healthSystems.surem.mapper;



import com.philips.healthSystems.surem.model.log.reqSuremLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuremMapper {
    /*
     * 로그 저장
     * @param reqSuremLog
     * @return int
     */
    public int insertLog(reqSuremLog reqSuremLog);
}