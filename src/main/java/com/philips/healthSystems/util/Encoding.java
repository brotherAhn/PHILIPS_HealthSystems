package com.philips.healthSystems.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.healthSystems.client.domain.agreeParam;
import com.philips.healthSystems.client.domain.filterParam;
import com.philips.healthSystems.client.domain.installParam;
import com.philips.healthSystems.client.domain.prescriptionParam;
import com.philips.healthSystems.client.domain.privateParam;
import com.philips.healthSystems.client.domain.qnaParam;
import com.philips.healthSystems.client.domain.returnParam;
import com.philips.healthSystems.client.domain.sleepcoachParam;

@Service
public class Encoding {
	@Autowired
	private AES256Util aes256Util;
	
	public agreeParam encodeAgreeParam(agreeParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		agreeParam eParam = new agreeParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return eParam;
	}
	
	public qnaParam encodeQnaParam(qnaParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		qnaParam eParam = new qnaParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return eParam;
	}
	
	
	public returnParam encodeReturnParam(returnParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		returnParam eParam = new returnParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return eParam;
	}
	
	public filterParam encodeFilterParam(filterParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		filterParam eParam = new filterParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return eParam;
	}
	
	public installParam encodeInstallParam(installParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		installParam eParam = new installParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return eParam;
	}
	public privateParam encodePrivateParam(privateParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		privateParam eParam = new privateParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return eParam;
	}
	public sleepcoachParam encodeSleepcoachParam(sleepcoachParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		sleepcoachParam eParam = new sleepcoachParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return eParam;
	}
	public prescriptionParam encodePrescriptionParam(prescriptionParam param) throws NoSuchAlgorithmException, GeneralSecurityException {
		prescriptionParam eParam = new prescriptionParam();
		try {
			if(param.getCampaign_no()!=null) {
				eParam.setCampaign_no(URLDecoder.decode(param.getCampaign_no(), "UTF-8"));
			}
			if(param.getName()!=null) {
				eParam.setName(URLDecoder.decode(param.getName(), "UTF-8"));
			}
			if(param.getMobile()!=null) {
				eParam.setMobile(aes256Util.encrypt(URLDecoder.decode(param.getMobile(), "UTF-8")));
			}
			if(param.getEmail()!=null) {
				eParam.setEmail(URLDecoder.decode(param.getEmail(), "UTF-8"));
			}
			if(param.getZipcode()!=null) {
				eParam.setZipcode(aes256Util.encrypt(URLDecoder.decode(param.getZipcode(), "UTF-8")));
			}
			if(param.getAddress()!=null) {
				eParam.setAddress(aes256Util.encrypt(URLDecoder.decode(param.getAddress(), "UTF-8")));
			}
			if(param.getHospital()!=null) {
				eParam.setHospital(URLDecoder.decode(param.getHospital(), "UTF-8"));
			}
			if(param.getFile()!=null) {
				eParam.setFile(URLDecoder.decode(param.getFile(), "UTF-8"));
			}
			if(param.getOriginal_file()!=null) {
				eParam.setOriginal_file(URLDecoder.decode(param.getOriginal_file(), "UTF-8"));
			}
			if(param.getPrivate_yn()!=null) {
				eParam.setPrivate_yn(URLDecoder.decode(param.getPrivate_yn(), "UTF-8"));
			}
			if(param.getDream()!=null) {
				eParam.setDream(URLDecoder.decode(param.getDream(), "UTF-8"));
			}
			if(param.getAdapter()!=null) {
				eParam.setAdapter(URLDecoder.decode(param.getAdapter(), "UTF-8"));
			}
			if(param.getTransfer_yn()!=null) {
				eParam.setTransfer_yn(URLDecoder.decode(param.getTransfer_yn(), "UTF-8"));
			}
			if(param.getMarketing_yn()!=null) {
				eParam.setMarketing_yn(URLDecoder.decode(param.getMarketing_yn(), "UTF-8"));
			}
			if(param.getCall_dt()!=null) {
				eParam.setCall_dt(URLDecoder.decode(param.getCall_dt(), "UTF-8"));
			}
			if(param.getCall_time()!=null) {
				eParam.setCall_time(URLDecoder.decode(param.getCall_time(), "UTF-8"));
			}
			if(param.getRequest()!=null) {
				eParam.setRequest(URLDecoder.decode(param.getRequest(), "UTF-8"));
			}
			if(param.getBirth()!=null) {
				eParam.setBirth(aes256Util.encrypt(URLDecoder.decode(param.getBirth(), "UTF-8")));
			}
			if(param.getFile1()!=null) {
				eParam.setFile1(URLDecoder.decode(param.getFile1(), "UTF-8"));
			}
			if(param.getOriginal_file1()!=null) {
				eParam.setOriginal_file1(URLDecoder.decode(param.getOriginal_file1(), "UTF-8"));
			}
			if(param.getFile2()!=null) {
				eParam.setFile2(URLDecoder.decode(param.getFile2(), "UTF-8"));
			}
			if(param.getOriginal_file2()!=null) {
				eParam.setOriginal_file2(URLDecoder.decode(param.getOriginal_file2(), "UTF-8"));
			}
			if(param.getFile3()!=null) {
				eParam.setFile3(URLDecoder.decode(param.getFile3(), "UTF-8"));
			}
			if(param.getOriginal_file3()!=null) {
				eParam.setOriginal_file3(URLDecoder.decode(param.getOriginal_file3(), "UTF-8"));
			}
			if(param.getFile4()!=null) {
				eParam.setFile4(URLDecoder.decode(param.getFile4(), "UTF-8"));
			}
			if(param.getOriginal_file4()!=null) {
				eParam.setOriginal_file4(URLDecoder.decode(param.getOriginal_file4(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return eParam;
	}
}
