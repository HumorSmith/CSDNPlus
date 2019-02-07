package com.ifreedomer.cplus.entity;

import android.util.Log;

import com.ifreedomer.cplus.activity.CPApplication;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;
import com.ifreedomer.cplus.http.protocol.resp.CollectListResp;
import com.ifreedomer.cplus.http.protocol.resp.HistoryResp;
import com.ifreedomer.cplus.http.protocol.resp.MyBlogItemResp;
import com.ifreedomer.cplus.http.protocol.resp.SearchDetailResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.DateUtil;
import com.ifreedomer.cplus.util.StringUtil;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogContentInfo implements Serializable {
    private static final String TAG = BlogContentInfo.class.getSimpleName();
    private String nickName;
    private String userName;
    private String date;
    private String avatar;
    private String title;
    private int commentNum;
    private String id;
    private static Pattern pattern;


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
        blogContentInfo.setTitle(articleResp.getTitle());
        return blogContentInfo;
    }

    public static BlogContentInfo convert(HistoryResp historyResp) {
        Log.d(TAG, "HISTORY Resp = " + historyResp.toString());
        BlogContentInfo blogContentInfo = new BlogContentInfo();
        blogContentInfo.setCommentNum(Integer.parseInt(historyResp.getCommentCount()));
        blogContentInfo.setUserName(historyResp.getUsername());
        blogContentInfo.setNickName(historyResp.getNickName());
        blogContentInfo.setDate(DateUtil.convertToMonth(CPApplication.INSTANCE, historyResp.getPostTime()));
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

    public static BlogContentInfo convert(CollectListResp collectItem) {
        Log.d(TAG, "HISTORY Resp = " + collectItem.toString());
        BlogContentInfo blogContentInfo = new BlogContentInfo();
        blogContentInfo.setCommentNum((collectItem.getCommentCount()));
        blogContentInfo.setUserName(collectItem.getUserName());
        blogContentInfo.setNickName(collectItem.getNickname());
        blogContentInfo.setDate(DateUtil.convertToMonth(CPApplication.INSTANCE, collectItem.getPostTime()));

        blogContentInfo.setId(StringUtil.getUrlCode(collectItem.getUrl()));
        blogContentInfo.setTitle(collectItem.getTitile());
        blogContentInfo.setAvatar(collectItem.getAvatarUrl());
        return blogContentInfo;
    }


    public static BlogContentInfo convert(MyBlogItemResp blogItemResp) {
        BlogContentInfo contentInfo = new BlogContentInfo();
        contentInfo.setAvatar(blogItemResp.getUserName());
        contentInfo.setTitle(blogItemResp.getTitle());
        contentInfo.setId(blogItemResp.getArticleId() + "");
        contentInfo.setUserName(blogItemResp.getUserName());
        contentInfo.setNickName(blogItemResp.getUserName());
        pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(blogItemResp.getPostTime());
        while (matcher.find()) {
            String group = matcher.group();
            Log.d(TAG, "GROUP = " + group);
            contentInfo.setDate(DateUtil.timeStamp2DateString(Long.parseLong(group) / 1000));
        }

        contentInfo.setCommentNum(Integer.parseInt(blogItemResp.getCommentCount()));
        return contentInfo;
    }


    public static BlogContentInfo convert(BlogResp blogItemResp) {
        BlogContentInfo contentInfo = new BlogContentInfo();
        contentInfo.setAvatar(GlobalDataManager.getInstance().getUserInfo().getAvatar());
        contentInfo.setTitle(blogItemResp.getTitle());
        contentInfo.setId(blogItemResp.getArticleId() + "");
        contentInfo.setNickName(blogItemResp.getUserName());
        contentInfo.setUserName(blogItemResp.getUserName());

        pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(blogItemResp.getPostTime());
        while (matcher.find()) {
            String group = matcher.group();
            Log.d(TAG, "GROUP = " + group);
            contentInfo.setDate(DateUtil.timeStamp2DateString(Long.parseLong(group) / 1000));
        }
        return contentInfo;

    }


    public static BlogContentInfo covert(SearchDetailResp.HitsBean item) {
        BlogContentInfo contentInfo = new BlogContentInfo();
        SearchDetailResp.HitsBean.SourceBean source = item.get_source();
        contentInfo.setTitle(source.getTitle());
        contentInfo.setUserName(source.getUser_name());
        contentInfo.setNickName(source.getNickname());
        contentInfo.setId(source.getId() + "");
        contentInfo.setDate(source.getEdit_time());
        return contentInfo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
