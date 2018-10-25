package com.ifreedomer.cplus.http.protocol.resp;

public class AddCommentResp {

    /**
     * content :
     * result : 1
     * vote : 0
     * data : {"PostTime":"2018-10-25 21:54:42","CommentId":8611448}
     * callback : null
     */

    private String content;
    private int result;
    private int vote;
    private DataBean data;
    private Object callback;

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

    public static class DataBean {
        /**
         * PostTime : 2018-10-25 21:54:42
         * CommentId : 8611448
         */

        private String PostTime;
        private int CommentId;

        public String getPostTime() {
            return PostTime;
        }

        public void setPostTime(String PostTime) {
            this.PostTime = PostTime;
        }

        public int getCommentId() {
            return CommentId;
        }

        public void setCommentId(int CommentId) {
            this.CommentId = CommentId;
        }
    }
}
