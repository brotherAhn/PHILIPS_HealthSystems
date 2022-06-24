package com.philips.healthSystems.surem.model.log;

import lombok.Data;

@Data
public class reqSuremLog {
    private String message_id;
    private String message_tyep;
    private String message_title;
    private String message_content;
    private String tel_to;
    private String tel_to_ori;
    private String tel_from;
}