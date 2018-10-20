package com.ifreedomer.cplus.http.protocol.req;

public class LoginReq {


    /**
     * pwdOrVerifyCode : a7681810810
     * loginType : 1
     * userIdentification : wuyihua86@gmail.com
     * fkid : 201802171746501860b4d3cbd22d2c8a65046a2f1b4f8b016536f80cc2f8b4
     */

    private String pwdOrVerifyCode;
    private String loginType;
    private String userIdentification;
    private String fkid;

    public String getPwdOrVerifyCode() {
        return pwdOrVerifyCode;
    }

    public void setPwdOrVerifyCode(String pwdOrVerifyCode) {
        this.pwdOrVerifyCode = pwdOrVerifyCode;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUserIdentification() {
        return userIdentification;
    }

    public void setUserIdentification(String userIdentification) {
        this.userIdentification = userIdentification;
    }

    public String getFkid() {
        return fkid;
    }

    public void setFkid(String fkid) {
        this.fkid = fkid;
    }
}
