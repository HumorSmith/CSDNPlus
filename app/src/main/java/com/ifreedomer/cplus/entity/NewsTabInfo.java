package com.ifreedomer.cplus.entity;

public class NewsTabInfo {
    private String tabName;
    private String tabKey;

    public NewsTabInfo(String tabName, String tabKey) {
        this.tabName = tabName;
        this.tabKey = tabKey;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }
}
