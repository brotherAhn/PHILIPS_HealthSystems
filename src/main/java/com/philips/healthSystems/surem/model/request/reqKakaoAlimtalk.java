package com.philips.healthSystems.surem.model.request;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;


@Data
public class reqKakaoAlimtalk {
    private String usercode;
    private String deptcode;
    private String yellowid_key;
    private List<AlimtalkMessage> messages;

    @Value("${surem.text.from}")
    private String from;

    public reqKakaoAlimtalk () {
        this.messages = new ArrayList<AlimtalkMessage>();
    }

    public void AddMessage(AlimtalkMessage Message) {
        this.messages.add(Message);
    }
}
