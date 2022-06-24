package com.philips.healthSystems.client.response;

import lombok.Data;

@Data
public class hostpitalListResponse {

    private String name;
    private String type;
    private String hp;
    private String zipcode;
    private String address;
    private String homepage;
}
