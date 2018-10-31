package com.ifreedomer.cplus.http.protocol.resp;

public class ForumPostResp {

    /**
     * user_id : 30647096
     * username : aa375809600
     * nickname : 怪叔叔萝莉控
     * body : 查哈哈哈哈哈哈哈哈哈哈哈哈
     * post_id : 403540530
     * created_at : 2018-10-31 21:56:07
     * digg : 0
     * bury : 0
     * is_digged : false
     * is_buryed : false
     * point : 0
     * floor : 32
     * avatar : http://avatar.csdn.net/A/C/7/1_aa375809600.jpg
     */

    private int user_id;
    private String username;
    private String nickname;
    private String body;
    private int post_id;
    private String created_at;
    private int digg;
    private int bury;
    private boolean is_digged;
    private boolean is_buryed;
    private int point;
    private int floor;
    private String avatar;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getDigg() {
        return digg;
    }

    public void setDigg(int digg) {
        this.digg = digg;
    }

    public int getBury() {
        return bury;
    }

    public void setBury(int bury) {
        this.bury = bury;
    }

    public boolean isIs_digged() {
        return is_digged;
    }

    public void setIs_digged(boolean is_digged) {
        this.is_digged = is_digged;
    }

    public boolean isIs_buryed() {
        return is_buryed;
    }

    public void setIs_buryed(boolean is_buryed) {
        this.is_buryed = is_buryed;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
