package com.ifreedomer.cplus.http.protocol.resp;

public class BlogCategoryResp {

    /**
     * id : 5819635
     * article_count : 8
     * hide : false
     * name : python
     */

    private int id;
    private int article_count;
    private boolean hide;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle_count() {
        return article_count;
    }

    public void setArticle_count(int article_count) {
        this.article_count = article_count;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlogCategoryResp{" +
                "id=" + id +
                ", article_count=" + article_count +
                ", hide=" + hide +
                ", name='" + name + '\'' +
                '}';
    }
}
