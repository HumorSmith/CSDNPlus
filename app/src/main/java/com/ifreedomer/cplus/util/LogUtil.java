package com.ifreedomer.cplus.util;

import android.util.Log;

import com.ifreedomer.cplus.BuildConfig;


public class LogUtil {
    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
}
