package com.ifreedomer.cplus.http.protocol.resp;

import com.google.gson.annotations.SerializedName;

public class V2ProfileResp {


    /**
     * bbsLevel : 1
     * all : 140
     * private : 10
     * fansNum : 627
     * blogLevel : 4
     * topicsCount : 2
     * selfdesc : 学一门新技术，要放下姿态，从零开始
     * collectNum : 17
     * avatar : https://avatar.csdn.net/A/C/7/1_aa375809600.jpg
     * commnetCount : 13
     * translatedCount : 0
     * replyCount : 9
     * followNum : 11
     * enable : 120
     * nickname : 怪叔叔萝莉控
     * rank : 31481
     * originalCount : 84
     * viewCount : 94879
     * repostCount : 36
     */

    private String bbsLevel;
    private String all;
    @SerializedName("private")
    private String privateX;
    private String fansNum;
    private String blogLevel;
    private String topicsCount;
    private String selfdesc;
    private int collectNum;
    private String avatar;
    private String commnetCount;
    private String translatedCount;
    private String replyCount;
    private String followNum;
    private String enable;
    private String nickname;
    private String rank;
    private String originalCount;
    private String viewCount;
    private String repostCount;

    public String getBbsLevel() {
        return bbsLevel;
    }

    public void setBbsLevel(String bbsLevel) {
        this.bbsLevel = bbsLevel;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getPrivateX() {
        return privateX;
    }

    public void setPrivateX(String privateX) {
        this.privateX = privateX;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getBlogLevel() {
        return blogLevel;
    }

    public void setBlogLevel(String blogLevel) {
        this.blogLevel = blogLevel;
    }

    public String getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(String topicsCount) {
        this.topicsCount = topicsCount;
    }

    public String getSelfdesc() {
        return selfdesc;
    }

    public void setSelfdesc(String selfdesc) {
        this.selfdesc = selfdesc;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCommnetCount() {
        return commnetCount;
    }

    public void setCommnetCount(String commnetCount) {
        this.commnetCount = commnetCount;
    }

    public String getTranslatedCount() {
        return translatedCount;
    }

    public void setTranslatedCount(String translatedCount) {
        this.translatedCount = translatedCount;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getFollowNum() {
        return followNum;
    }

    public void setFollowNum(String followNum) {
        this.followNum = followNum;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getOriginalCount() {
        return originalCount;
    }

    public void setOriginalCount(String originalCount) {
        this.originalCount = originalCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(String repostCount) {
        this.repostCount = repostCount;
    }
}
