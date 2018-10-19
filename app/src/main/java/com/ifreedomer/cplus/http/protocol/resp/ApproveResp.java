package com.ifreedomer.cplus.http.protocol.resp;

public class ApproveResp {
    private boolean status;
    private int digg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDigg() {
        return digg;
    }

    public void setDigg(int digg) {
        this.digg = digg;
    }
}
