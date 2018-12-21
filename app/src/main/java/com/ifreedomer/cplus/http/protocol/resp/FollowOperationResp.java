package com.ifreedomer.cplus.http.protocol.resp;

public class FollowOperationResp {
    public static final int FAILED = 0;
    public static final int SUCCESS = 1;

    /**
     * succ : 0
     * msg : 取消关注失败
     */

    private int succ;
    private String msg;

    public int getSucc() {
        return succ;
    }

    public void setSucc(int succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "FollowOperationResp{" +
                "succ=" + succ +
                ", msg='" + msg + '\'' +
                '}';
    }
}
