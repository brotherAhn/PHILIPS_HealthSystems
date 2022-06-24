package com.philips.healthSystems.surem.model.response;

import lombok.Data;

import java.util.List;

@Data
public class resKakaoAlimtalk {
    private String code;
    private String message;
    private List<resKakaoAlimtalkResults> results;
    private String additional_information;

    @Data
    public class resKakaoAlimtalkResults {
        private String result;
        private String message_id;
    }
}


