package com.philips.healthSystems.admin.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.philips.healthSystems.admin.excelDomain.excelGroupInfoParam;
import com.philips.healthSystems.admin.excelDomain.excelTargetUserSelefctParam;
import com.philips.healthSystems.admin.excelResponse.excelGroupInfoResponse;
import com.philips.healthSystems.admin.excelResponse.excelTargetUserSelectResponse;
import com.philips.healthSystems.admin.response.userState;
import com.philips.healthSystems.admin.service.PhilipsExcelService;
import com.philips.healthSystems.util.AES256Util;
import com.philips.healthSystems.util.ExcelView;
import com.philips.healthSystems.util.WebUtil;


@Controller
@RequestMapping("admin")
public class AdminExcelController {
	@Autowired
	private AES256Util aes256Util;
	
	private Log log = LogFactory.getLog(AdminExcelController.class);
	
	@Autowired
	private PhilipsExcelService philipsExcelService;
	
	@Autowired
	private ExcelView excelView;

    @RequestMapping(value = "/ajax/excel/agreement/list")
    public void agreementExcel(HttpServletRequest req, HttpServletResponse res, excelTargetUserSelefctParam param) throws IOException {
        log.info("/ajax/excel/agreement/list");


        List<excelTargetUserSelectResponse> data = philipsExcelService.excelTargetUserSelect2(param);
        List<excelTargetUserSelectResponse> decryptDataList = new ArrayList<excelTargetUserSelectResponse>();
        for(excelTargetUserSelectResponse info : data) {
            excelTargetUserSelectResponse decryptData = info;
            try {
                decryptData.setName(aes256Util.decrypt(info.getName()));
                decryptData.setMobile(aes256Util.decrypt(info.getMobile()));
                if(info.getState()==userState.COM) {
                    decryptData.setResponseUrl("https://healthsystems.tckdigital.com/agreeResult?user="+info.getUrl());
                }
                decryptDataList.add(decryptData);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }


        List<String> listColumn = new ArrayList<>();
        List<List<Object>> listData = new ArrayList<List<Object>>();
        /*Excel */
        if (null != decryptDataList) {
            listColumn.add("No");
            listColumn.add("??????");
            listColumn.add("????????? ??????");
            listColumn.add("?????? ??????");
            listColumn.add("?????? ???");
            listColumn.add("??????");
            listColumn.add("????????? URL");
            listColumn.add("????????? URL");
            listColumn.add("????????? ??????");
            listColumn.add("?????? ??????");
            listColumn.add("?????? ??????");
            listColumn.add("DM ??????");
            listColumn.add("????????? ??????");
            listColumn.add("?????????");
            listColumn.add("?????????");
            listColumn.add("??????");
            listColumn.add("?????? ??????");
            listColumn.add("?????? ??????");
            listColumn.add("?????? ??????");
            listColumn.add("?????? ??????");

        }
        for (excelTargetUserSelectResponse item : decryptDataList) {
            List<Object> row = new ArrayList<Object>();
            row.add(decryptDataList.indexOf(item)+1);
            row.add(item.getName());
            row.add(item.getMobile());
            row.add(item.getCustomer_no());
            row.add(item.getGroupName());
            row.add(item.getState().getDesc());
            row.add(item.getRequestUrl());
            row.add(item.getResponseUrl());
            row.add(item.getMarketing1_yn());
            row.add(item.getMarketing2_tel());
            row.add(item.getMarketing2_sms());
            row.add(item.getMarketing2_dm());
            row.add(item.getMarketing2_email());
            row.add(item.getHospital());
            row.add(item.getDept());
            row.add(item.getPosition());
            row.add(item.getSend_dt());
            row.add(item.getSendYN());
            row.add(item.getCrt_dt());
            row.add(item.getAgree_dt());



            listData.add(row);
        }

        Map<String, Object> model = new HashedMap<String, Object>();
        model.put("fileName", "?????????");
        model.put("sheetName", "?????????");
        model.put("listData", listData);
        model.put("listColumn", listColumn);

        try {
            excelView.createExcel(model, req, res);
        } catch (Exception e) {
            WebUtil.alert("??????????????? ??????????????????", res);
            log.error(e.toString());
        }


    }


	@RequestMapping(value = "/ajax/excel/groupInfo/list")
	public void groupInfoExcel(HttpServletRequest req, HttpServletResponse res,excelGroupInfoParam param) throws IOException {
		log.info("/ajax/excel/groupInfo/list");
		
		List<excelGroupInfoResponse> data = philipsExcelService.excelGroupInfo(param);
		List<excelGroupInfoResponse> decryptDataList = new ArrayList<excelGroupInfoResponse>(); 
		
		for(excelGroupInfoResponse info : data) {
			excelGroupInfoResponse decryptData = new excelGroupInfoResponse();
			try {
				decryptData.setName(aes256Util.decrypt(info.getName()));
				decryptData.setMobile(aes256Util.decrypt(info.getMobile()));
				decryptData.setRequestUrl(info.getRequestUrl());
				decryptDataList.add(decryptData);
			} catch (Exception e) {

				e.printStackTrace();
			} 
			
		}

		List<String> listColumn = new ArrayList<>();
        List<List<Object>> listData = new ArrayList<List<Object>>();
        /*Excel */
        if (null != decryptDataList) {
            listColumn.add("????????????");
            listColumn.add("??????");
            listColumn.add("URL");
        }
        for (excelGroupInfoResponse item : decryptDataList) {
            List<Object> row = new ArrayList<Object>();
            row.add(item.getMobile());
            row.add(item.getName());
            row.add(item.getRequestUrl());
            
            listData.add(row);
        }
        Map<String, Object> model = new HashedMap<String, Object>();
        model.put("fileName", "????????? ??????");
        model.put("sheetName", "????????? ??????");
        model.put("listData", listData);
        model.put("listColumn", listColumn);
        
        try {
			excelView.createExcel(model, req, res);
		} catch (Exception e) {
			WebUtil.alert("??????????????? ??????????????????", res);
			log.error(e.toString());
		}
		
	}
	
	
	
}
