package com.ifreedomer.cplus.http.protocol.resp;

public class ForgetPwdUserNameResp {

    /**
     * msg : success
     * code : 0
     * data : {"username":"aa375809600"}
     * status : true
     */

    private String msg;
    private String code;
    private DataBean data;
    private boolean status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * username : aa375809600
         */

        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
