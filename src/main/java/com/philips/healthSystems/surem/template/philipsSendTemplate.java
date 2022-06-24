package com.philips.healthSystems.surem.template;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.philips.healthSystems.surem.model.request.AlimtalkMessage;

import lombok.Data;

@Data 
public class philipsSendTemplate {
	@Value("${surem.text.from}")
    private String from;

    public AlimtalkMessage createMessage(String phone, String url) {
        AlimtalkMessage message = new AlimtalkMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        message.message_id = dateFormat.format(new Date());
        message.to = "82" + phone.substring(1, phone.length());

        message.from = from;
        message.template_code = "health care_notice14";
        message.reserved_time = "";
        message.text = this.getText(url);
        
        message.re_send = "Y";
        
        
        return  message;

    }

    public String getText(String url) {
        String template = "안녕하세요. 필립스 헬스케어 고객지원팀 입니다.\n";
        template += "\n";
        template += "필립스에서는 보다 나은 서비스를 제공하고자 노력하고 있습니다.\n";
		template += "\n";
		template += "이를 위한 고객정보를 취합하기 전 하기 URL에 접속하여 개인정보활용동의를 해주시면 감사하겠습니다.\n";
		template += "\n";
		template += "URL 주소 :\n";
		template += url;


        return template;
    }
}
