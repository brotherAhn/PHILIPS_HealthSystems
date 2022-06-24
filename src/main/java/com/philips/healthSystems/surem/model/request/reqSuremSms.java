package com.philips.healthSystems.surem.model.request;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class reqSuremSms {
    private String usercode;
    private String deptcode;
    private String text;
    private String from;

    private List<SmsMessage> messages;

    public void SetMessage(String phone) {
        SmsMessage Message = new SmsMessage();
        //yyyyMMdd_HHmmss
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddSSS");
        Message.message_id = dateFormat.format(new Date());
        Message.to = phone;
        this.messages = new ArrayList<SmsMessage>();
        this.messages.add(Message);
    }

    @Data
    public class SmsMessage {
        private String message_id;
        private String to;
    }
}


