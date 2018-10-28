package com.ifreedomer.cplus.entity;

import com.google.gson.annotations.SerializedName;

public class DeployBlogContentInfo {

//      .addFormDataPart("title", "title")
//                .addFormDataPart("markdowncontent", "markdowncontent")
//                .addFormDataPart("content", "content")
//                .addFormDataPart("id", "")
//                .addFormDataPart("private", true + "")
//                .addFormDataPart("tags", "opencv")
//                .addFormDataPart("status", "65")
//                .addFormDataPart("categories", "opencv,android")
//                .addFormDataPart("channel", "28")
//                .addFormDataPart("type", "original")
//                .addFormDataPart("articleedittype", "1")
//                .addFormDataPart("Description", "")


    public DeployBlogContentInfo() {
    }

    private String markdownContent = "";
    private String content = "";
    private String id = "";
    private String tags = "";
    private String status = "";
    private String categories;
    private String channel = "28";
    private String type = "";
    private String Description = "";
    private String title = "";
    @SerializedName("private")
    private boolean aPrivate;
    private String articleedittype = "1";

    public DeployBlogContentInfo(String markdownContent, String content, String id, String tags, String categories, String type, String description, String title, boolean aPrivate, String articleedittype) {
        this.markdownContent = markdownContent;
        this.content = content;
        this.id = id;
        this.tags = tags;
        this.categories = categories;
        this.type = type;
        Description = description;
        this.title = title;
        this.aPrivate = aPrivate;
        this.articleedittype = articleedittype;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPrivate() {
        return aPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        this.aPrivate = aPrivate;
    }

    public String getArticleedittype() {
        return articleedittype;
    }

    public void setArticleedittype(String articleedittype) {
        this.articleedittype = articleedittype;
    }

    @Override
    public String toString() {
        return "DeployBlogContentInfo{" +
                "markdownContent='" + markdownContent + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", tags='" + tags + '\'' +
                ", status='" + status + '\'' +
                ", categories='" + categories + '\'' +
                ", channel='" + channel + '\'' +
                ", type='" + type + '\'' +
                ", Description='" + Description + '\'' +
                ", title='" + title + '\'' +
                ", aPrivate=" + aPrivate +
                ", articleedittype='" + articleedittype + '\'' +
                '}';
    }
}
