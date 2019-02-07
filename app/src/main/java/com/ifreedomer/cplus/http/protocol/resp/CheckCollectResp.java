package com.ifreedomer.cplus.http.protocol.resp;

public class CheckCollectResp {

    /**
     * is_exist : 1
     * msg : 您已经收藏过
     * favorite_id : 18658151
     */

    private boolean is_exist;
    private String msg;
    private String favorite_id;

    public boolean isIs_exist() {
        return is_exist;
    }

    public void setIs_exist(boolean is_exist) {
        this.is_exist = is_exist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }
}
