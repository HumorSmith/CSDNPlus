package com.ifreedomer.cplus.manager;


import com.ifreedomer.cplus.entity.UserInfo;

public class GlobalDataManager {


    private static final GlobalDataManager sUserManager = new GlobalDataManager();

    public static GlobalDataManager getInstance() {
        return sUserManager;
    }

    private GlobalDataManager() {
    }


    private String mSessionId = "LH5Dv4pUXww%3D";

    public String getSessionId() {
        return mSessionId;
    }

    public void setSessionId(String sessionId) {
        mSessionId = sessionId;
    }


    private UserInfo mUserInfo;

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }
}
