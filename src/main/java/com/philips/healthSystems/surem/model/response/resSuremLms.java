package com.philips.healthSystems.surem.model.response;

import lombok.Data;

import java.util.List;

@Data
public class resSuremLms {
    private String code;
    private String message;
    private List<resSuremLmsResults> results;

    @Data
    public class resSuremLmsResults {
        private String result;
        private String message_id;
    }
}



