package com.ifreedomer.cplus.http.protocol.req;

public class SearchReq {
    private String block;
    private int size;
    private int page;
    private int timeScope;
    private int queryRules;
    private String keywords;

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTimeScope() {
        return timeScope;
    }

    public void setTimeScope(int timeScope) {
        this.timeScope = timeScope;
    }

    public int getQueryRules() {
        return queryRules;
    }

    public void setQueryRules(int queryRules) {
        this.queryRules = queryRules;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
