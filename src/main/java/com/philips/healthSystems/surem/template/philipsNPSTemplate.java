package com.philips.healthSystems.surem.template;


import com.philips.healthSystems.surem.model.request.AlimtalkMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data 
public class philipsNPSTemplate {
	@Value("${surem.text.from}")
    private String from;

    public AlimtalkMessage createMessage(String phone,String caseNumber, String equiment) {
        AlimtalkMessage message = new AlimtalkMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        message.message_id = dateFormat.format(new Date());
        message.to = "82" + phone.substring(1, phone.length());

        message.from = from;
        message.template_code = "health care_notice16";
        message.reserved_time = "";
        message.text = this.getText(caseNumber,equiment);
        
        message.re_send = "Y";


        return  message;

    }

    public String getText(String caseNumber, String equiment) {
        String template = "안녕하세요. 필립스 헬스케어 고객지원팀 입니다.\n";
        template += "\n";
        template += "필립스에서는 보다 나은 서비스를 제공하고자 만족도 설문을 진행하고 있습니다.하기 URL에 접속하여 설문을 작성해 주시면 감사하겠습니다.\n";
        template += "\n";
        template += "Case#\n";
        template += caseNumber;
        template += "\n";
        template += "Equipment Name\n";
        template += equiment;
        template += "\n";
        template += "다시 한번 감사드리며, 항상 최선을 다하는 필립스가 되겠습니다.";

        return template;
    }
}
