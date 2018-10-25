package com.ifreedomer.cplus.http.protocol.resp;

import java.util.List;

public class CommentListResp {

    /**
     * count : 2
     * floor_count : 2
     * list : [{"info":{"ParentId":"0","NickName":"怪叔叔萝莉控","BlogId":"6679886","IP":"","IsBoleComment":"0","Avatar":"https://avatar.csdn.net/A/C/7/3_aa375809600.jpg","UserName":"aa375809600","PKId":"0","CommentId":"8581272","WeixinArticleId":"0","Digg":"0","Bury":"0","Status":"0","ArticleId":"72834303","SubjectType":"-1","PostTime":"2018-10-18 22:00:29","Content":"哈哈哈哈"}},{"info":{"ParentId":"0","NickName":"吃个椰子压压惊","BlogId":"6679886","IP":"172.16.10.4","IsBoleComment":"0","Avatar":"https://avatar.csdn.net/3/5/5/3_lyztyycode.jpg","UserName":"lyztyycode","PKId":"0","CommentId":"6885581","WeixinArticleId":"0","Digg":"0","Bury":"0","Status":"0","ArticleId":"72834303","SubjectType":"0","PostTime":"2017-06-05 20:30:16","Content":"博主这波给你满分 很赞，求更！"}}]
     * page_count : 1
     */

    private int count;
    private int floor_count;
    private int page_count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFloor_count() {
        return floor_count;
    }

    public void setFloor_count(int floor_count) {
        this.floor_count = floor_count;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * info : {"ParentId":"0","NickName":"怪叔叔萝莉控","BlogId":"6679886","IP":"","IsBoleComment":"0","Avatar":"https://avatar.csdn.net/A/C/7/3_aa375809600.jpg","UserName":"aa375809600","PKId":"0","CommentId":"8581272","WeixinArticleId":"0","Digg":"0","Bury":"0","Status":"0","ArticleId":"72834303","SubjectType":"-1","PostTime":"2018-10-18 22:00:29","Content":"哈哈哈哈"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * ParentId : 0
             * NickName : 怪叔叔萝莉控
             * BlogId : 6679886
             * IP :
             * IsBoleComment : 0
             * Avatar : https://avatar.csdn.net/A/C/7/3_aa375809600.jpg
             * UserName : aa375809600
             * PKId : 0
             * CommentId : 8581272
             * WeixinArticleId : 0
             * Digg : 0
             * Bury : 0
             * Status : 0
             * ArticleId : 72834303
             * SubjectType : -1
             * PostTime : 2018-10-18 22:00:29
             * Content : 哈哈哈哈
             */

            private String ParentId;
            private String NickName;
            private String BlogId;
            private String IP;
            private String IsBoleComment;
            private String Avatar;
            private String UserName;
            private String PKId;
            private String CommentId;
            private String WeixinArticleId;
            private String Digg;
            private String Bury;
            private String Status;
            private String ArticleId;
            private String SubjectType;
            private String PostTime;
            private String Content;

            public String getParentId() {
                return ParentId;
            }

            public void setParentId(String ParentId) {
                this.ParentId = ParentId;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getBlogId() {
                return BlogId;
            }

            public void setBlogId(String BlogId) {
                this.BlogId = BlogId;
            }

            public String getIP() {
                return IP;
            }

            public void setIP(String IP) {
                this.IP = IP;
            }

            public String getIsBoleComment() {
                return IsBoleComment;
            }

            public void setIsBoleComment(String IsBoleComment) {
                this.IsBoleComment = IsBoleComment;
            }

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String Avatar) {
                this.Avatar = Avatar;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPKId() {
                return PKId;
            }

            public void setPKId(String PKId) {
                this.PKId = PKId;
            }

            public String getCommentId() {
                return CommentId;
            }

            public void setCommentId(String CommentId) {
                this.CommentId = CommentId;
            }

            public String getWeixinArticleId() {
                return WeixinArticleId;
            }

            public void setWeixinArticleId(String WeixinArticleId) {
                this.WeixinArticleId = WeixinArticleId;
            }

            public String getDigg() {
                return Digg;
            }

            public void setDigg(String Digg) {
                this.Digg = Digg;
            }

            public String getBury() {
                return Bury;
            }

            public void setBury(String Bury) {
                this.Bury = Bury;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getArticleId() {
                return ArticleId;
            }

            public void setArticleId(String ArticleId) {
                this.ArticleId = ArticleId;
            }

            public String getSubjectType() {
                return SubjectType;
            }

            public void setSubjectType(String SubjectType) {
                this.SubjectType = SubjectType;
            }

            public String getPostTime() {
                return PostTime;
            }

            public void setPostTime(String PostTime) {
                this.PostTime = PostTime;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }
        }
    }
}
