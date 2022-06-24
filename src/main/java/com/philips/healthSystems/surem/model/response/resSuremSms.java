package com.philips.healthSystems.surem.model.response;

import lombok.Data;

import java.util.List;

@Data
public class resSuremSms {
    private String code;
    private String message;
    private List<resSuremSmsResults> results;

    @Data
    public class resSuremSmsResults {
        private String result;
        private String message_id;
    }
}



