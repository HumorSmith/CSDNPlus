package com.ifreedomer.cplus.http.protocol.resp;

public class CollectListResp {


    /**
     * avatarUrl : https://avatar.csdn.net/8/B/B/3_sinyu890807.jpg
     * articleId : 84886691
     * description : 今天跟大家谈谈情怀。话说我从13年开始写博客写到现在，也写出了一些成绩。成为了博客专家，出版了自己的书，推出了自己的开源框架，还有着自己的微信公众号。我相信有很多朋友可能都是通过我的书或者是我的博客入门和学习Android开发的，但是我自己却始终有一个遗憾，我虽然帮助了许多人学会了开发AndroidApp，但是我自己却几乎从来没有开发过一个完整的App，更没有参与过任何一个知名App的研发。我从11年开始参加工作，一开始进入的是一家华为的外包公司，主要做华为的外包项目。而华为的项目也是别人外包过来
     * userName : sinyu890807
     * type : Original
     * url : https://blog.csdn.net/sinyu890807/app/article/details/84886691
     * commentCount : 84
     * postTime : 2018-12-14 08:11:04
     * titile : 两年沉淀，我的开源项目已上线！
     * isTop : false
     * nickname : guolin
     * viewCount : 8622
     * channelId : 1
     * status : 1
     */

    private String avatarUrl;
    private String articleId;
    private String description;
    private String userName;
    private String type;
    private String url;
    private int commentCount;
    private String postTime;
    private String titile;
    private boolean isTop;
    private String nickname;
    private int viewCount;
    private String channelId;
    private String status;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
