package com.ifreedomer.cplus.http.protocol.resp;

public class UserInfoResp {

    /**
     * id : 0
     * userId : 30647096
     * userName : aa375809600
     * email : wuyihua86@gmail.com
     * mobile : 18311362506
     * nickname : 怪叔叔萝莉控
     * userInfo :
     * created_at :
     * updated_at :
     * avatarUpdateTime : 0
     * app : null
     * tgc : TGT-60274-mtIjSuGcrFJ1RxuC702j0X4DFDZZXx62l7aOyNb7oktTVaSjHx-passport.csdn.net
     * avatar : http://avatar.csdn.net/A/C/7/1_aa375809600.jpg
     */

    private int id;
    private int userId;
    private String userName;
    private String email;
    private String mobile;
    private String nickname;
    private String userInfo;
    private String created_at;
    private String updated_at;
    private int avatarUpdateTime;
    private Object app;
    private String tgc;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getAvatarUpdateTime() {
        return avatarUpdateTime;
    }

    public void setAvatarUpdateTime(int avatarUpdateTime) {
        this.avatarUpdateTime = avatarUpdateTime;
    }

    public Object getApp() {
        return app;
    }

    public void setApp(Object app) {
        this.app = app;
    }

    public String getTgc() {
        return tgc;
    }

    public void setTgc(String tgc) {
        this.tgc = tgc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
