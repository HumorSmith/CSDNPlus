package com.ifreedomer.cplus.util;

import com.ifreedomer.cplus.CPApplication;

public class LoginPrefs {
    private static final String JWT_TOKEN = "jwt_token";
    private static final String LOGIN_MOBILE = "login_mobile";
    private static final String LOGIN_PASSWORD = "login_password";
    private static final String LOGIN_USERNAME = "login_username";
    private static final String SP_NAME = "LoginPref";
    private static final String USERNAME = "username";
    private static final String USER_TOKEN = "user_token";



    public static void checkOldVersionSaveData() {


    }




    public static String getJwtToken() {
        return (String) SPUtil.get(CPApplication.INSTANCE, JWT_TOKEN, "");
    }

    public static void setJwtToken(String token) {
        SPUtil.put(CPApplication.INSTANCE, JWT_TOKEN, token);
    }



}