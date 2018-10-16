package com.ifreedomer.cplus.http.protocol.resp;

import com.google.gson.annotations.SerializedName;

public class BlogUserProfileResp {
    private ArticleCountBean article_count;
    private LevelBean level;

    public BlogUserProfileResp.ArticleCountBean getArticleCountBean() {
        return article_count;
    }

    public void setArticleCountBean(BlogUserProfileResp.ArticleCountBean articleCountBean) {
        article_count = articleCountBean;
    }

    public BlogUserProfileResp.LevelBean getLevelBean() {
        return level;
    }

    public void setLevelBean(BlogUserProfileResp.LevelBean levelBean) {
        level = levelBean;
    }

    public BlogUserProfileResp.StatisticBean getStatisticBean() {
        return statistic;
    }

    public void setStatisticBean(BlogUserProfileResp.StatisticBean statisticBean) {
        statistic = statisticBean;
    }

    private StatisticBean statistic;

    public static class ArticleCountBean {
        @Override
        public String toString() {
            return "ArticleCountBean{" +
                    "draft=" + draft +
                    ", enable=" + enable +
                    ", deleted=" + deleted +
                    ", all=" + all +
                    ", privateX=" + privateX +
                    '}';
        }

        /**
         * draft : 10
         * enable : 109
         * deleted : 22
         * all : 119
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
        @Override
        public String toString() {
            return "LevelBean{" +
                    "chizhiyiheng=" + chizhiyiheng +
                    ", point=" + point +
                    ", level=" + level +
                    ", ico_expert=" + ico_expert +
                    ", bokezhixing=" + bokezhixing +
                    ", zhuanlandaren=" + zhuanlandaren +
                    ", weiruanmvp=" + weiruanmvp +
                    '}';
        }

        /**
         * chizhiyiheng : true
         * point : 1684
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


    public static class StatisticBean {
        @Override
        public String toString() {
            return "StatisticBean{" +
                    "Flag='" + Flag + '\'' +
                    ", Rank='" + Rank + '\'' +
                    ", DiggCount=" + DiggCount +
                    ", OriginalCount='" + OriginalCount + '\'' +
                    ", BlogId='" + BlogId + '\'' +
                    ", TranslatedCount='" + TranslatedCount + '\'' +
                    ", ViewCount=" + ViewCount +
                    ", RepostCount='" + RepostCount + '\'' +
                    ", Point=" + Point +
                    ", CommentCount='" + CommentCount + '\'' +
                    '}';
        }

        /**
         * Flag : 0
         * Rank : 33104
         * DiggCount : 9
         * OriginalCount : 73
         * BlogId : 2056551
         * TranslatedCount : 0
         * ViewCount : 85358
         * RepostCount : 36
         * Point : 1684
         * CommentCount : 8
         */

        private String Flag;
        private String Rank;
        private int DiggCount;
        private String OriginalCount;
        private String BlogId;
        private String TranslatedCount;
        private int ViewCount;
        private String RepostCount;
        private int Point;
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

        public int getDiggCount() {
            return DiggCount;
        }

        public void setDiggCount(int DiggCount) {
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

        public int getViewCount() {
            return ViewCount;
        }

        public void setViewCount(int ViewCount) {
            this.ViewCount = ViewCount;
        }

        public String getRepostCount() {
            return RepostCount;
        }

        public void setRepostCount(String RepostCount) {
            this.RepostCount = RepostCount;
        }

        public int getPoint() {
            return Point;
        }

        public void setPoint(int Point) {
            this.Point = Point;
        }

        public String getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(String CommentCount) {
            this.CommentCount = CommentCount;
        }
    }

    @Override
    public String toString() {
        return "BlogUserProfileResp{" +
                "article_count=" + article_count +
                ", level=" + level +
                ", statistic=" + statistic +
                '}';
    }
}
