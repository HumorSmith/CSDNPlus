package com.ifreedomer.cplus.entity;

public class DeployBlogContentInfo {
    private String markdownContent;
    private String content;
    private String id;
    private String tags;
    private String status;
    private String categories;
    private String channel;
    private String type;
    private String Description;
    private String title;
    private boolean aPrivate;
    private String articleedittype;

    public DeployBlogContentInfo(String markdownContent, String content, String id, String tags, String status, String categories, String channel, String type, String description, String title, boolean aPrivate, String articleedittype) {
        this.markdownContent = markdownContent;
        this.content = content;
        this.id = id;
        this.tags = tags;
        this.status = status;
        this.categories = categories;
        this.channel = channel;
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
}
