package com.ifreedomer.cplus.http.protocol.resp;

public class DeleteCollectResp {

    /**
     * msg :
     * success : 1
     */

    private String msg;
    private int success;

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
}
