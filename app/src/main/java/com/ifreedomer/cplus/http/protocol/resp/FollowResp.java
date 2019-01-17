package com.ifreedomer.cplus.http.protocol.resp;

public class FollowResp {
    public static final int NOT_RELATION = 0;
    public static final int FOLLOW = 1;
    public static final int BEFOLLOW = 2;
    public static final int FOLLOW_EACH_OTHER = 3;

    /**
     * isFocus : 1
     * nickname : 飞-翔-鸟
     * description : 游戏程序员一枚，做过三维引擎开发，后发觉移动互联网大势所趋，转行移动端开发，热爱ios应用，android应用开发，现从事游戏开发，常用语言c，lua
     * avatar : https://avatar.csdn.net/3/2/3/1_ahstuxq.jpg
     * username : ahstuxq
     */

    private int isFocus;
    private String nickname;
    private String description;
    private String avatar;
    private String username;

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
