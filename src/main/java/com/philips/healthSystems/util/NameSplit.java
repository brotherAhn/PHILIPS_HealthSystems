package com.philips.healthSystems.util;

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
public class NameSplit {
	
	public agreeParam NameSplit(agreeParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public filterParam NameSplit(filterParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public installParam NameSplit(installParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public prescriptionParam NameSplit(prescriptionParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public privateParam NameSplit(privateParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public qnaParam NameSplit(qnaParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public returnParam NameSplit(returnParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	public sleepcoachParam NameSplit(sleepcoachParam param) {
		String fullname = param.getName();
		if(fullname.charAt(0) >= 'A' && fullname.charAt(0) <= 'z') {//첫입력이 영문일 경우
			//System.out.println("영어이름입력됨");
			param.setName(fullname);
			param.setFirstname(null);
			//System.out.println(param.getName());
			return param;
		}
		
		if(fullname.length()<=3) {//이름총길이가 3자이하일 경우
			String firstName = fullname.substring(0,1);
			String name = fullname.substring(1,fullname.length());
			param.setName(name);
			param.setFirstname(firstName);
			//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
			return param;
		}
		
		if(fullname.length()>3) {
			boolean isFirstName = true;
			String firstName = fullname.substring(0,2);
			String[] nameArray = {"남궁","독고","동방","사공","서문","선우","소봉","어금","제갈","황보"};
			for(String str : nameArray) {
				if(firstName.equals(str)) {
					//System.out.println("복성입니다.");
					param.setFirstname(firstName);
					String name = fullname.substring(2,fullname.length());
					param.setName(name);
					//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
					isFirstName = false;
					return param;
				}
			}
			if(isFirstName) {
				firstName = fullname.substring(0,1);
				String name = fullname.substring(1,fullname.length());
				param.setName(name);
				param.setFirstname(firstName);
				//System.out.println("firstName : "+param.getFirstname()+" / name : "+param.getName());
				return param;
			}
			
		}
		return param;
	}
	
	/*
	 * public static void main(String[] args) { agreeParam para = new agreeParam();
	 * NameSplit ns = new NameSplit(); para.setName(""); ns.SplitName(para); }
	 */
}
