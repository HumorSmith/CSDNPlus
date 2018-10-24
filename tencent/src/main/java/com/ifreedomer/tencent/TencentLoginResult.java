package com.ifreedomer.tencent;


public class TencentLoginResult {
    private String token;
    private String openId;
    private String expirs;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getExpirs() {
        return expirs;
    }

    public void setExpirs(String expirs) {
        this.expirs = expirs;
    }

    @Override
    public String toString() {
        return "TencentLoginResult{" +
                "token='" + token + '\'' +
                ", openId='" + openId + '\'' +
                ", expirs='" + expirs + '\'' +
                '}';
    }
}
