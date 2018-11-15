package com.ifreedomer.cplus.http.protocol.resp;

public class ResetPwdResp {

    /**
     * msg : 成功发送验证码
     * code : 0
     * data : {"successNum":1}
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
         * successNum : 1
         */

        private int successNum;

        public int getSuccessNum() {
            return successNum;
        }

        public void setSuccessNum(int successNum) {
            this.successNum = successNum;
        }
    }
}
