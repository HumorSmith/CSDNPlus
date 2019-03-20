package com.ifreedomer.basead;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class BaseADSplashActivity extends Activity {
    public static Class<?> sTargetClass;
    public static String appId;
    public static String splashId;
    public static boolean jumpToTarget = false;
    public static final int NOT_DEFINE = -1;
    public static int splashLayoutId = NOT_DEFINE;
    public static final String DAY_KEY = "data";

    public static int getSplashLayoutId() {
        return splashLayoutId;
    }

    public static void setSplashLayoutId(int splashLayoutId) {
        BaseADSplashActivity.splashLayoutId = splashLayoutId;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        BaseADSplashActivity.appId = appId;
    }

    public static String getSplashId() {
        return splashId;
    }

    public static void setSplashId(String splashId) {
        BaseADSplashActivity.splashId = splashId;
    }

    public static boolean isJumpToTarget() {
        return jumpToTarget;
    }

    public static void setJumpToTarget(boolean jumpToTarget) {
        BaseADSplashActivity.jumpToTarget = jumpToTarget;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void setTargetClass(Class<?> targetClass) {
        sTargetClass = targetClass;
    }

    public static Class<?> getTargetClass() {
        return sTargetClass;
    }
}
