package com.ifreedomer.cplus.util;

import android.text.TextUtils;

public class StringUtil {

    public static boolean isMobile(String s) {
        return TextUtils.isDigitsOnly(s) && s.length() == 11;
    }
}
