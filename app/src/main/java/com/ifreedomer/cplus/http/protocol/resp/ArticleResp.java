package com.ifreedomer.cplus.http.protocol.resp;

public class ArticleResp {


    /**
     * summary : 一、为什么要使用域名解析？

       在给大家讲解域名解析过程之前，我想先让大家明白为什么我们要使用DNS域名解析。就拿淘宝来说吧，淘宝网的IP地址是119.147.15.13，但是我们一般都是在浏览器输入www.taobao.com。这是为什么呢？众所周知，计算机只能识别0和1，计算机对数字的记忆是简单的，但是对于我们人类而言，你给我一串数字我是很难记忆的，而且IP地址多了容易记混淆。因而，域名解析...
     * nickname : 时光里的那缕风
     * shown_time : 1537067649
     * category_id : none
     * user_url : https://blog.csdn.net/qq_34208467
     * cache_time : 1537067665
     * avatar : https://avatar.csdn.net/D/3/8/1_qq_34208467.jpg
     * type : blog
     * shown_offset : 1537067649000000
     * url : https://blog.csdn.net/qq_34208467/article/details/82721238
     * id : 82721238
     * user_name : qq_34208467
     * category :
     * title : DNS域名解析过程
     * views : 1
     * created_at : 刚刚
     * strategy_id : watchers
     * comments : 0
     */

    private String summary;
    private String nickname;
    private String shown_time;
    private String category_id;
    private String user_url;
    private String cache_time;
    private String avatar;
    private String type;
    private long shown_offset;
    private String url;
    private String id;
    private String user_name;
    private String category;
    private String title;
    private String views;
    private String created_at;
    private String strategy_id;
    private String comments;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getShown_time() {
        return shown_time;
    }

    public void setShown_time(String shown_time) {
        this.shown_time = shown_time;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    public String getCache_time() {
        return cache_time;
    }

    public void setCache_time(String cache_time) {
        this.cache_time = cache_time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getShown_offset() {
        return shown_offset;
    }

    public void setShown_offset(long shown_offset) {
        this.shown_offset = shown_offset;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStrategy_id() {
        return strategy_id;
    }

    public void setStrategy_id(String strategy_id) {
        this.strategy_id = strategy_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ArticleResp{" +
                "summary='" + summary + '\'' +
                ", nickname='" + nickname + '\'' +
                ", shown_time='" + shown_time + '\'' +
                ", category_id='" + category_id + '\'' +
                ", user_url='" + user_url + '\'' +
                ", cache_time='" + cache_time + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type='" + type + '\'' +
                ", shown_offset=" + shown_offset +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", views='" + views + '\'' +
                ", created_at='" + created_at + '\'' +
                ", strategy_id='" + strategy_id + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
