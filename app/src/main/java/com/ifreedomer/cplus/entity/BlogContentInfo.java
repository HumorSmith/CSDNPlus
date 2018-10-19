package com.ifreedomer.cplus.entity;

import android.util.Log;

import com.ifreedomer.cplus.CPApplication;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.http.protocol.resp.HistoryResp;
import com.ifreedomer.cplus.util.DateUtil;

import java.io.Serializable;

public class BlogContentInfo implements Serializable {
    private static final String TAG = BlogContentInfo.class.getSimpleName();
    private String nickName;
    private String userName;
    private String date;
    private String avatar;
    private String title;
    private int commentNum;
    private String id;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public static BlogContentInfo covert(ArticleResp articleResp) {
        BlogContentInfo blogContentInfo = new BlogContentInfo();
        blogContentInfo.setAvatar(articleResp.getAvatar());
        blogContentInfo.setDate(DateUtil.timeStamp2DateStringWithMonth(CPApplication.INSTANCE, Long.parseLong(articleResp.getShown_time()) * 1000));
        blogContentInfo.setNickName(articleResp.getNickname());
        blogContentInfo.setUserName(articleResp.getUser_name());
        blogContentInfo.setCommentNum(Integer.parseInt(articleResp.getComments()));
        blogContentInfo.setId(articleResp.getId());
        return blogContentInfo;
    }

    public static BlogContentInfo convert(HistoryResp historyResp) {
        Log.d(TAG,"HISTORY Resp = "+historyResp.toString());
        BlogContentInfo blogContentInfo = new BlogContentInfo();
        blogContentInfo.setCommentNum(Integer.parseInt(historyResp.getCommentCount()));
        blogContentInfo.setUserName(historyResp.getUsername());
        blogContentInfo.setNickName(historyResp.getNickName());
        blogContentInfo.setDate( DateUtil.convertToMonth(CPApplication.INSTANCE, historyResp.getPostTime()));
       ;
        String id = historyResp.getBizId();
        if (id.contains("&")) {
            id = id.split("&")[0];
        }
        blogContentInfo.setId(id);
        blogContentInfo.setTitle(historyResp.getTitle());
        blogContentInfo.setAvatar(historyResp.getAvatar());
        return blogContentInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
