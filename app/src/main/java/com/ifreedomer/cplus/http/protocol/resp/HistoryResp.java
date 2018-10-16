package com.ifreedomer.cplus.http.protocol.resp;

public class HistoryResp {


    /**
     * username : aa375809600
     * type : blog
     * bizId : 50273787&aa375809600
     * title : 11_排序二叉树删除-3
     * viewTime : 0
     * createAt : 1970-01-01
     * publishTime : null
     * description : 3 普通节点的删除      3.1 删除的节点没有左子树，也没有右子树       测试用例1： 删除节点6      [cpp] view  plaincopy       /*  *                 *         10          ======>     10  *        /  \
     * postTime : 2015-12-12 11:44:53
     * viewCount : 181
     * commentCount : 0
     * nickName : 怪叔叔萝莉控
     */

    private String username;
    private String type;
    private String bizId;
    private String title;
    private int viewTime;
    private String createAt;
    private Object publishTime;
    private String description;
    private String postTime;
    private String viewCount;
    private String commentCount;
    private String nickName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewTime() {
        return viewTime;
    }

    public void setViewTime(int viewTime) {
        this.viewTime = viewTime;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Object getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Object publishTime) {
        this.publishTime = publishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
