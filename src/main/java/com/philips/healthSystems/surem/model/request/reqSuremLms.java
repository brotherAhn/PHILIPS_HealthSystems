package com.philips.healthSystems.surem.model.request;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class reqSuremLms {
    private String usercode;
    private String deptcode;
    private String subject;

    private String text;
    private String from;

    private List<LmsMessage> messages;

    public void SetMessage(String phone) {
        LmsMessage Message = new LmsMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddSSS");
        Message.message_id = dateFormat.format(new Date());
        Message.to = phone.replaceAll("-", "");
        this.messages = new ArrayList<LmsMessage>();
        this.messages.add(Message);
    }

    public class LmsMessage {
        private String message_id;
        private String to;
    }
}


