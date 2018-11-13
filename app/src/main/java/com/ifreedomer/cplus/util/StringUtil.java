package com.ifreedomer.cplus.util;

import android.text.TextUtils;

public class StringUtil {

    public static boolean isMobile(String s) {
        return TextUtils.isDigitsOnly(s) && s.length() == 11;
    }

    public static String getUrlCode(String url) {
        String id = "";
        if (TextUtils.isEmpty(url) || !url.contains("/")) {
            return id;
        }
        String[] split = url.split("\\/");
        return split[split.length - 1];
    }
}
