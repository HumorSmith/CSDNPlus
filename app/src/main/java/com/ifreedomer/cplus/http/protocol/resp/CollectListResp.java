package com.ifreedomer.cplus.http.protocol.resp;

import java.util.List;

public class CollectListResp {

    /**
     * data : [{"nickname":"qq_16563637","dateline":"1539792585","avatar":"https://avatar.csdn.net/0/3/1/3_qq_16563637.jpg","commentCount":"0","url":"https://blog.csdn.net/qq_16563637/article/details/82906868","id":"18539152","username":"qq_16563637","title":"logstash-2.3.1安装和kafka结合使用","share":"0","description":"logstash-2.3.1安装和kafka结合使用 说明: 1.logstash必须运行于jdk7.55以上版本(可参考的我另一篇博客安装https://blog.csdn.net/qq_16563637/article/details/81738113) 2.logstash开发语言JRuby,运行于jvm 3.logstash可以防止数据丢失并可以采集更多数据源(https://www.el...","domain":"blog.csdn.net","map_name":"","viewCount":"398","postTime":"2018-09-30 11:00:35"}]
     * success : 1
     */

    private int success;
    private List<CollectItem> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<CollectItem> getData() {
        return data;
    }

    public void setData(List<CollectItem> data) {
        this.data = data;
    }

    public static class CollectItem {
        /**
         * nickname : qq_16563637
         * dateline : 1539792585
         * avatar : https://avatar.csdn.net/0/3/1/3_qq_16563637.jpg
         * commentCount : 0
         * url : https://blog.csdn.net/qq_16563637/article/details/82906868
         * id : 18539152
         * username : qq_16563637
         * title : logstash-2.3.1安装和kafka结合使用
         * share : 0
         * description : logstash-2.3.1安装和kafka结合使用 说明: 1.logstash必须运行于jdk7.55以上版本(可参考的我另一篇博客安装https://blog.csdn.net/qq_16563637/article/details/81738113) 2.logstash开发语言JRuby,运行于jvm 3.logstash可以防止数据丢失并可以采集更多数据源(https://www.el...
         * domain : blog.csdn.net
         * map_name :
         * viewCount : 398
         * postTime : 2018-09-30 11:00:35
         */

        private String nickname;
        private String dateline;
        private String avatar;
        private String commentCount;
        private String url;
        private String id;
        private String username;
        private String title;
        private String share;
        private String description;
        private String domain;
        private String map_name;
        private String viewCount;
        private String postTime;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getMap_name() {
            return map_name;
        }

        public void setMap_name(String map_name) {
            this.map_name = map_name;
        }

        public String getViewCount() {
            return viewCount;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getPostTime() {
            return postTime;
        }

        public void setPostTime(String postTime) {
            this.postTime = postTime;
        }
    }
}
