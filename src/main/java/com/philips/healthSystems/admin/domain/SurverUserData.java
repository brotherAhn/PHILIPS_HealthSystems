package com.philips.healthSystems.admin.domain;

import com.philips.healthSystems.admin.response.userState;
import com.philips.healthSystems.util.AES256Util;

import lombok.Data;


@Data
public class SurverUserData {

    private Integer SurveyUserDataPkid;
    private Integer SurveyUserPkid;
    private Integer UserPkid;
    private Integer CampaignPkid;
    private String SurveyUrl;
    private String SurveyUserTel;
    private String SurveyUserTelDecrypt;
    private String SurveyUserName;
    private String SurveyUserNameDecrypt;
    private String SurveyUserEmail;
    private String SurveyUserEmailDecrypt;
    private String SurveyUserInteractionType;
    private String SurveyUserInteractionTypeName;
    private String SurveyUserRegType;
    private String SurveyUserRegTypeName;
    private String ManagerName;
    private String ManagerNameDecrypt;
    private userState State;
    private String StateName;
    private String IsSend;
    private String IsSendName;
    private Integer SendCnt;
    private String SendDate;
    private String AccessData;
    private String ReSendDate;
    private String RegistDate;
    private String CompleteDate;
    private String UserRegDate;
    private String InsertType;
    private Integer GroupPkId;
    private String customerNo;
    private String url;


    private String caseNumber;
    private String EquipmentName;
    
    public String getSurveyUserTelDecrypt() {
        try {
            return this.SurveyUserTel != null && !this.SurveyUserTel.isEmpty() ? new AES256Util().decrypt(this.SurveyUserTel) : "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getSurveyUserNameDecrypt() {
        try {
            return this.SurveyUserName != null && !this.SurveyUserName.isEmpty() ? new AES256Util().decrypt(this.SurveyUserName) : "";
        } catch (Exception e) {

            return "";
        }
    }

    public String getSurveyUserEmailDecrypt() {
        try {
            return this.SurveyUserEmail != null && !this.SurveyUserEmail.isEmpty() ? new AES256Util().decrypt(this.SurveyUserEmail) : "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getManagerNameDecrypt() {
        try {
            return this.ManagerName != null && !this.ManagerName.isEmpty() ? new AES256Util().decrypt(this.ManagerName) : "";
        } catch (Exception e) {
            return "";
        }
    }



    public String getStateName() {
        if (this.State == null) return "";
        if (this.State.equals("1")) {
            return "완료";
        } else if (this.State.equals("0")) {
            if(this.AccessData == null || this.AccessData.isEmpty()) return "미참여";
            else return "이탈";
        } else {
            return "";
        }
    }

    public String getStateNameView() {
        if (this.State == null) return "";
        if (this.State.equals("1")) {
            return "<span style='color:blue;font-weight: bold;'>완료</span>";
        } else if (this.State.equals("0")) {
            if(this.AccessData == null || this.AccessData.isEmpty()) return "<span style='color: black;font-weight: bold;'>미참여</span>";
            else return "<span style='color:red;font-weight: bold;'>이탈</span>";
        } else {
            return "";
        }
    }

    public String getSurveyUserInteractionTypeName() {
        if (this.SurveyUserInteractionType == null) return "";

        if (this.SurveyUserInteractionType.equals("1")) {
            return "Advice";
        } else if (this.SurveyUserInteractionType.equals("2a")) {
            return "FSE Repair";
        } else if (this.SurveyUserInteractionType.equals("2b")) {
            return "RTB Repair";
        } else if (this.SurveyUserInteractionType.equals("3c")) {
            return "Direct web orders";
        } else if (this.SurveyUserInteractionType.equals("6")) {
            return "Registration";
        } else {
            return "";
        }
    }

    public String getSurveyUserRegTypeName() {
        if (this.SurveyUserRegType == null) return "";

        if(this.SurveyUserRegType.equals("1")) return  "TCK";
        else if(this.SurveyUserRegType.equals("2")) return  "유베이스";
        else if(this.SurveyUserRegType.equals("3")) return  "DBL";
        else if(this.SurveyUserRegType.equals("4")) return  "관리자 직접 등록";
        else if(this.SurveyUserRegType.equals("5")) return  "WebOrders";
        else return "";
    }


    public String getIsSendName() {
        if (this.IsSend == null) return "";

        if (this.IsSend.equals("Y")) {
            return "발송";
        } else if (this.IsSend.equals("N")) {
            return "대기";
        } else {
            return "";
        }
    }

    public String getSurveyUrl() {
        try {
            return this.SurveyUrl.replace("&", "%26").replace("+", "%2B");
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getUrl() {
        try {
            return this.url.replace("&", "%26").replace("+", "%2B");
        } catch (Exception e) {
            return "";
        }
    }
}