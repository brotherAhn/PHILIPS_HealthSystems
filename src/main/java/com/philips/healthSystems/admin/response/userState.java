package com.philips.healthSystems.admin.response;

public enum userState {
	NSE("미 발송"), NOT("미 확인"), COF("확인"), COM("완료");

    private String desc;

    userState(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
