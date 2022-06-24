package com.philips.healthSystems.util;

import java.util.ArrayList;
import java.util.List;

import com.philips.healthSystems.client.domain.agreeList;
import com.philips.healthSystems.client.domain.agreeParam;

public class Util {
	
	public List<agreeList> getlist(agreeParam param){
		List<agreeList> rList = new ArrayList<agreeList>();
		agreeList al = new agreeList();
		if(param.getCampaign_no()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("campaign_no");
			al.setValue(param.getCampaign_no());
			rList.add(al);
		}
		if(param.getName()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("name");
			al.setValue(param.getName());
			rList.add(al);
		}
		if(param.getFirstname()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("firstname");
			al.setValue(param.getFirstname());
			rList.add(al);
		}
		if(param.getMobile()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("moblie");
			al.setValue(param.getMobile());
			rList.add(al);
		}
		if(param.getEmail()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("email");
			al.setValue(param.getEmail());
			rList.add(al);
		}
		if(param.getZipcode()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("zipcode");
			al.setValue(param.getZipcode());
			rList.add(al);
		}
		if(param.getAddress()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("address");
			al.setValue(param.getAddress());
			rList.add(al);
		}
		if(param.getHospital()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("hospital");
			al.setValue(param.getHospital());
			rList.add(al);
		}
		if(param.getFile()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("file");
			al.setValue(param.getFile());
			rList.add(al);
		}
		if(param.getPrivate_yn()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("private_yn");
			al.setValue(param.getPrivate_yn());
			rList.add(al);
		}
		if(param.getTransfer_yn()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("transfer_yn");
			al.setValue(param.getTransfer_yn());
			rList.add(al);
		}
		if(param.getMarketing_yn()!=null) {
			al = null;
			al = new agreeList();
			al.setAnswer_no(param.getAnswer_no());
			al.setType("marketing_yn");
			al.setValue(param.getMarketing_yn());
			rList.add(al);
		}
		return rList;
	}
}
