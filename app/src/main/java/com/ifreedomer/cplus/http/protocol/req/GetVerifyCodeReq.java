package com.ifreedomer.cplus.http.protocol.req;

public class GetVerifyCodeReq {

    /**
     * userName : aa375809600
     * sendType : 1
     * code : 0086
     * mobileOrEmail : 18311362506
     */

    private String userName;
    private String sendType;
    private String code;
    private String mobileOrEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobileOrEmail() {
        return mobileOrEmail;
    }

    public void setMobileOrEmail(String mobileOrEmail) {
        this.mobileOrEmail = mobileOrEmail;
    }
}
