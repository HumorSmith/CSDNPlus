package com.ifreedomer.cplus.http.protocol.resp;

public class MyBlogItemResp {


    /**
     * blogId : 2056551
     * articleId : 82938663
     * title : 六.opencv ndk环境搭建(cmake方式)
     * userName : aa375809600
     * nickname : 怪叔叔萝莉控
     * avatar : http://avatar.csdn.net/A/C/7/1_aa375809600.jpg
     * description : opencv全称opensourcecomputevision,开源的视觉库一.准备工作导入opencv的步骤还是相对简单,准备步骤分为以下三步:下载opencvandroidsdkhttps://opencv.org/releases.html将OpenCV-android-sdk/sdk/native/libs拷贝到工程的jniLibs将OpenCV-android...
     * viewCount : 39
     * digg : 0
     * bury : 0
     * commentCount : 0
     * tags :
     * postTime : /Date(1538643431000)/
     * url : null
     * channelId : 0
     */

    private int blogId;
    private int articleId;
    private String title;
    private String userName;
    private String nickname;
    private String avatar;
    private String description;
    private int viewCount;
    private int digg;
    private int bury;
    private int commentCount;
    private String tags;
    private String postTime;
    private Object url;
    private int channelId;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
