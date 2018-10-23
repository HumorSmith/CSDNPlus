package com.ifreedomer.cplus.http.protocol.resp;

public class SaveArticleResp {

    /**
     * data : {"id":83280166,"url":"https://blog.csdn.net/aa375809600/article/details/83280166"}
     * error :
     * status : true
     */

    private DataBean data;
    private String error;
    private boolean status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * id : 83280166
         * url : https://blog.csdn.net/aa375809600/article/details/83280166
         */

        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return "SaveArticleResp{" +
                "data=" + data +
                ", error='" + error + '\'' +
                ", status=" + status +
                '}';
    }
}
