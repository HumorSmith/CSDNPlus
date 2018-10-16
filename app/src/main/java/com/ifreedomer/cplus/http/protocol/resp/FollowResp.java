package com.ifreedomer.cplus.http.protocol.resp;

public class FollowResp {
    public static final int NOT_RELATION = 0;
    public static final int FOLLOW = 1;
    public static final int BEFOLLOW = 2;
    public static final int FOLLOW_EACH_OTHER = 3;

    /**
     * username : jiaozhenxinaichenme
     * avatar : https://avatar.csdn.net/A/E/4/1_jiaozhenxinaichenme.jpg
     * description : 主要做Android系统开发
     * nickname : Jason-Jiao
     * isFocus : 1
     */

    private String username;
    private String avatar;
    private String description;
    private String nickname;
    private int isFocus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }
}
