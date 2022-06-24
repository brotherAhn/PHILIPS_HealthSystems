package com.philips.healthSystems.surem.template;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.philips.healthSystems.surem.model.request.AlimtalkMessage;

import lombok.Data;

@Data 
public class philipsResultTemplate {
	@Value("${surem.text.from}")
    private String from;

    public AlimtalkMessage createMessage(String phone,String month, String url) {
        AlimtalkMessage message = new AlimtalkMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        message.message_id = dateFormat.format(new Date());
        message.to = "82" + phone.substring(1, phone.length());

        message.from = from;
        message.template_code = "health care_notice15";
        message.reserved_time = "";
        message.text = this.getText(month,url);
        
        message.re_send = "Y";


        return  message;

    }

    public String getText(String month, String url) {
        String template = "안녕하세요, 필립스 헬스케어 고객지원팀 입니다.\n";
        template += "\n";
        template += month+"월 개인정보 동의서 작성해주신 개인정보동의서 사본 발송 드립니다.\n";
        template += "\n";
        template += "URL 주소 :\n";
        template += url;

        return template;
    }
}
