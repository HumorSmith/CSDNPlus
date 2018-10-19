package com.ifreedomer.cplus.http.protocol.resp;

public class CountResp {

    /**
     * data : {"follow_num":"10","download":{"level":2,"total_score":500,"score":153},"blog":{"level":4,"total_score":2000,"score":1761},"ask":{"level":1,"total_score":10,"score":0},"visitor_num":"87578","fans_num":"20","bbs":{"level":1,"total_score":100,"score":0}}
     * msg : 成功
     * success : 1
     */

    private DataBean data;
    private String msg;
    private int success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * follow_num : 10
         * download : {"level":2,"total_score":500,"score":153}
         * blog : {"level":4,"total_score":2000,"score":1761}
         * ask : {"level":1,"total_score":10,"score":0}
         * visitor_num : 87578
         * fans_num : 20
         * bbs : {"level":1,"total_score":100,"score":0}
         */

        private String follow_num;
        private DownloadBean download;
        private BlogBean blog;
        private AskBean ask;
        private String visitor_num;
        private String fans_num;
        private BbsBean bbs;

        public String getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(String follow_num) {
            this.follow_num = follow_num;
        }

        public DownloadBean getDownload() {
            return download;
        }

        public void setDownload(DownloadBean download) {
            this.download = download;
        }

        public BlogBean getBlog() {
            return blog;
        }

        public void setBlog(BlogBean blog) {
            this.blog = blog;
        }

        public AskBean getAsk() {
            return ask;
        }

        public void setAsk(AskBean ask) {
            this.ask = ask;
        }

        public String getVisitor_num() {
            return visitor_num;
        }

        public void setVisitor_num(String visitor_num) {
            this.visitor_num = visitor_num;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public BbsBean getBbs() {
            return bbs;
        }

        public void setBbs(BbsBean bbs) {
            this.bbs = bbs;
        }

        public static class DownloadBean {
            /**
             * level : 2
             * total_score : 500
             * score : 153
             */

            private int level;
            private int total_score;
            private int score;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getTotal_score() {
                return total_score;
            }

            public void setTotal_score(int total_score) {
                this.total_score = total_score;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }

        public static class BlogBean {
            /**
             * level : 4
             * total_score : 2000
             * score : 1761
             */

            private int level;
            private int total_score;
            private int score;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getTotal_score() {
                return total_score;
            }

            public void setTotal_score(int total_score) {
                this.total_score = total_score;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }

        public static class AskBean {
            /**
             * level : 1
             * total_score : 10
             * score : 0
             */

            private int level;
            private int total_score;
            private int score;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getTotal_score() {
                return total_score;
            }

            public void setTotal_score(int total_score) {
                this.total_score = total_score;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }

        public static class BbsBean {
            /**
             * level : 1
             * total_score : 100
             * score : 0
             */

            private int level;
            private int total_score;
            private int score;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getTotal_score() {
                return total_score;
            }

            public void setTotal_score(int total_score) {
                this.total_score = total_score;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }
}
