package com.ifreedomer.cplus.http.protocol.resp;

public class AddCollectResp {

    /**
     * data : {"id":18657144}
     * msg : 收藏成功
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
         * id : 18657144
         */

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
