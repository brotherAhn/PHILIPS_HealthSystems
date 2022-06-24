package com.philips.healthSystems.surem.model.request;

import lombok.Data;

import java.util.List;

@Data
public class AlimtalkMessage {
    public String message_id;
    public String to;
    public String text;
    public String from;
    public String template_code;
    public String reserved_time;
    public String re_send;
    public String re_text;
    public List<AlimtalkButton> buttons;

}
