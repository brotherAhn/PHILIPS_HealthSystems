package com.philips.healthSystems.surem.template;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.philips.healthSystems.surem.model.request.AlimtalkMessage;

import lombok.Data;

@Data 
public class philipsSendTemplate_notice12 {
	@Value("${surem.text.from}")
    private String from;

    public AlimtalkMessage createMessage(String phone, String url) {
        AlimtalkMessage message = new AlimtalkMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        message.message_id = dateFormat.format(new Date());
        message.to = "82" + phone.substring(1, phone.length());

        message.from = from;
        message.template_code = "notice12";
        message.reserved_time = "";
        message.text = this.getText(url);
        
        message.re_send = "Y";
        
        
        return  message;

    }

    public String getText(String url) {
        String template = "안녕하세요, 필립스 양압기 슬립케어 서비스 입니다.\n";
		template += "새롭게 개정된 개인정보 동의서 서명 작성이 완료되지 않아 다시 한 번 안내 드립니다.\n";
		template += "\n";
		template += "2022년 2월28일 기준 개인정보 정보동의서 일부가 개정되었습니다.\n";
		template += "\n";
		template += "제3자 제공 ( 당사 계약업체 및 국외 포함 ) 및 자동 출금을 위한 신용정보 수집 이용 및 이전 등의 개인정보 동의\n";
		template += "기존에 개인정보동의서를 작성하신 고객님께서도, 개정된 내용을 포함하고 있는 최신 개인정보 동의서에 동의 및 서명이 필요합니다.\n";
		template += "\n";
		template += "하기 URL 에 접속 후, 계약서 동의 및 서명 부탁드립니다.";
		template += "\n";
		template += "URL 주소 :\n";
		template += url;
		template += "\n";
		template += "\n";
		template += "감사합니다.";
		
        return template;
    }
}
