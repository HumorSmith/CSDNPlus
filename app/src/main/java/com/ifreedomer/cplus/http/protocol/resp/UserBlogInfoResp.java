package com.ifreedomer.cplus.http.protocol.resp;

import com.google.gson.annotations.SerializedName;

public class UserBlogInfoResp {

    /**
     * statistic : {"Flag":"0","Rank":"32248","DiggCount":"10","OriginalCount":"79","BlogId":"2056551","TranslatedCount":"0","ViewCount":"87667","RepostCount":"36","Point":"1764","CommentCount":"8"}
     * article_count : {"draft":10,"enable":115,"deleted":22,"all":125,"private":0}
     * level : {"chizhiyiheng":false,"point":1764,"level":4,"ico_expert":0,"bokezhixing":false,"zhuanlandaren":false,"weiruanmvp":false}
     * fans : 20
     */

    private StatisticBean statistic;
    private ArticleCountBean article_count;
    private LevelBean level;
    private String fans;

    public StatisticBean getStatistic() {
        return statistic;
    }

    public void setStatistic(StatisticBean statistic) {
        this.statistic = statistic;
    }

    public ArticleCountBean getArticle_count() {
        return article_count;
    }

    public void setArticle_count(ArticleCountBean article_count) {
        this.article_count = article_count;
    }

    public LevelBean getLevel() {
        return level;
    }

    public void setLevel(LevelBean level) {
        this.level = level;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public static class StatisticBean {
        /**
         * Flag : 0
         * Rank : 32248
         * DiggCount : 10
         * OriginalCount : 79
         * BlogId : 2056551
         * TranslatedCount : 0
         * ViewCount : 87667
         * RepostCount : 36
         * Point : 1764
         * CommentCount : 8
         */

        private String Flag;
        private String Rank;
        private String DiggCount;
        private String OriginalCount;
        private String BlogId;
        private String TranslatedCount;
        private String ViewCount;
        private String RepostCount;
        private String Point;
        private String CommentCount;

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String Flag) {
            this.Flag = Flag;
        }

        public String getRank() {
            return Rank;
        }

        public void setRank(String Rank) {
            this.Rank = Rank;
        }

        public String getDiggCount() {
            return DiggCount;
        }

        public void setDiggCount(String DiggCount) {
            this.DiggCount = DiggCount;
        }

        public String getOriginalCount() {
            return OriginalCount;
        }

        public void setOriginalCount(String OriginalCount) {
            this.OriginalCount = OriginalCount;
        }

        public String getBlogId() {
            return BlogId;
        }

        public void setBlogId(String BlogId) {
            this.BlogId = BlogId;
        }

        public String getTranslatedCount() {
            return TranslatedCount;
        }

        public void setTranslatedCount(String TranslatedCount) {
            this.TranslatedCount = TranslatedCount;
        }

        public String getViewCount() {
            return ViewCount;
        }

        public void setViewCount(String ViewCount) {
            this.ViewCount = ViewCount;
        }

        public String getRepostCount() {
            return RepostCount;
        }

        public void setRepostCount(String RepostCount) {
            this.RepostCount = RepostCount;
        }

        public String getPoint() {
            return Point;
        }

        public void setPoint(String Point) {
            this.Point = Point;
        }

        public String getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(String CommentCount) {
            this.CommentCount = CommentCount;
        }
    }

    public static class ArticleCountBean {
        /**
         * draft : 10
         * enable : 115
         * deleted : 22
         * all : 125
         * private : 0
         */

        private int draft;
        private int enable;
        private int deleted;
        private int all;
        @SerializedName("private")
        private int privateX;

        public int getDraft() {
            return draft;
        }

        public void setDraft(int draft) {
            this.draft = draft;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getPrivateX() {
            return privateX;
        }

        public void setPrivateX(int privateX) {
            this.privateX = privateX;
        }
    }

    public static class LevelBean {
        /**
         * chizhiyiheng : false
         * point : 1764
         * level : 4
         * ico_expert : 0
         * bokezhixing : false
         * zhuanlandaren : false
         * weiruanmvp : false
         */

        private boolean chizhiyiheng;
        private int point;
        private int level;
        private int ico_expert;
        private boolean bokezhixing;
        private boolean zhuanlandaren;
        private boolean weiruanmvp;

        public boolean isChizhiyiheng() {
            return chizhiyiheng;
        }

        public void setChizhiyiheng(boolean chizhiyiheng) {
            this.chizhiyiheng = chizhiyiheng;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getIco_expert() {
            return ico_expert;
        }

        public void setIco_expert(int ico_expert) {
            this.ico_expert = ico_expert;
        }

        public boolean isBokezhixing() {
            return bokezhixing;
        }

        public void setBokezhixing(boolean bokezhixing) {
            this.bokezhixing = bokezhixing;
        }

        public boolean isZhuanlandaren() {
            return zhuanlandaren;
        }

        public void setZhuanlandaren(boolean zhuanlandaren) {
            this.zhuanlandaren = zhuanlandaren;
        }

        public boolean isWeiruanmvp() {
            return weiruanmvp;
        }

        public void setWeiruanmvp(boolean weiruanmvp) {
            this.weiruanmvp = weiruanmvp;
        }
    }

    @Override
    public String toString() {
        return "UserBlogInfoResp{" +
                "statistic=" + statistic +
                ", article_count=" + article_count +
                ", level=" + level +
                ", fans='" + fans + '\'' +
                '}';
    }
}
