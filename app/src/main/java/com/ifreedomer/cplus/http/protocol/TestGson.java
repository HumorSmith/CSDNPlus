package com.ifreedomer.cplus.http.protocol;

import com.google.gson.annotations.SerializedName;

public class TestGson {

    /**
     * code : 2000
     * message :
     * data : {"content":"success","result":1,"vote":0,"data":{"aa375809600":{"statistic":{"Flag":"0","Rank":"33104","DiggCount":9,"OriginalCount":"73","BlogId":"2056551","TranslatedCount":"0","ViewCount":85358,"RepostCount":"36","Point":1684,"CommentCount":"8"},"article_count":{"draft":10,"enable":109,"deleted":22,"all":119,"private":0},"level":{"chizhiyiheng":true,"point":1684,"level":4,"ico_expert":0,"bokezhixing":false,"zhuanlandaren":false,"weiruanmvp":false},"fans":"20"}},"callback":null,"user":null}
     * sessionId :
     * sessionExpired : 1536887559130
     * serviceVersions : V2.3.8
     */

    private int code;
    private String message;
    private DataBeanX data;
    private String sessionId;
    private String sessionExpired;
    private String serviceVersions;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(String sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    public String getServiceVersions() {
        return serviceVersions;
    }

    public void setServiceVersions(String serviceVersions) {
        this.serviceVersions = serviceVersions;
    }

    public static class DataBeanX {
        /**
         * content : success
         * result : 1
         * vote : 0
         * data : {"aa375809600":{"statistic":{"Flag":"0","Rank":"33104","DiggCount":9,"OriginalCount":"73","BlogId":"2056551","TranslatedCount":"0","ViewCount":85358,"RepostCount":"36","Point":1684,"CommentCount":"8"},"article_count":{"draft":10,"enable":109,"deleted":22,"all":119,"private":0},"level":{"chizhiyiheng":true,"point":1684,"level":4,"ico_expert":0,"bokezhixing":false,"zhuanlandaren":false,"weiruanmvp":false},"fans":"20"}}
         * callback : null
         * user : null
         */

        private String content;
        private int result;
        private int vote;
        private DataBean data;
        private Object callback;
        private Object user;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getVote() {
            return vote;
        }

        public void setVote(int vote) {
            this.vote = vote;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public Object getCallback() {
            return callback;
        }

        public void setCallback(Object callback) {
            this.callback = callback;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public static class DataBean {
            /**
             * aa375809600 : {"statistic":{"Flag":"0","Rank":"33104","DiggCount":9,"OriginalCount":"73","BlogId":"2056551","TranslatedCount":"0","ViewCount":85358,"RepostCount":"36","Point":1684,"CommentCount":"8"},"article_count":{"draft":10,"enable":109,"deleted":22,"all":119,"private":0},"level":{"chizhiyiheng":true,"point":1684,"level":4,"ico_expert":0,"bokezhixing":false,"zhuanlandaren":false,"weiruanmvp":false},"fans":"20"}
             */

            private Aa375809600Bean aa375809600;

            public Aa375809600Bean getAa375809600() {
                return aa375809600;
            }

            public void setAa375809600(Aa375809600Bean aa375809600) {
                this.aa375809600 = aa375809600;
            }

            public static class Aa375809600Bean {
                /**
                 * statistic : {"Flag":"0","Rank":"33104","DiggCount":9,"OriginalCount":"73","BlogId":"2056551","TranslatedCount":"0","ViewCount":85358,"RepostCount":"36","Point":1684,"CommentCount":"8"}
                 * article_count : {"draft":10,"enable":109,"deleted":22,"all":119,"private":0}
                 * level : {"chizhiyiheng":true,"point":1684,"level":4,"ico_expert":0,"bokezhixing":false,"zhuanlandaren":false,"weiruanmvp":false}
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

                public static class ArticleCountBean {
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
            }
        }
    }
}
