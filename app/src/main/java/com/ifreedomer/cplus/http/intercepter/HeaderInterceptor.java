package com.ifreedomer.cplus.http.intercepter;

import android.text.TextUtils;
import android.util.Log;

import com.ifreedomer.cplus.activity.CPApplication;
import com.ifreedomer.cplus.util.DevicesIdUtil;
import com.ifreedomer.cplus.util.LoginPrefs;
import com.ifreedomer.cplus.util.MD5Util;
import com.ifreedomer.cplus.util.MarkUtils;
import com.ifreedomer.cplus.util.URLEncodedUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    public static final String TAG = HeaderInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());

        Map<String, String> headerParams = getHeaderParams(original.url().query());
        Log.d(TAG, "header = " + headerParams);
        for (Map.Entry<String, String> entry : headerParams.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        return chain.proceed(requestBuilder.build());

    }


    public static Map<String, String> getHeaderParams(String requestName) {
//        LoginPrefs.setJwtToken("eyJhbGciOiJSUzI1NiIsImtpZCI6ImQzNThlMDY3LWZmODMtNDI4ZS1hZTJjLWViMzM5NDEzYWY1ZSJ9.eyJzdWIiOiJhYTM3NTgwOTYwMCIsIlgtQXBwSUQiOiJDU0ROLUFQUCIsIlgtRGV2aWNlLUlEIjoiYWltZWk4Njc2ODYwMjIyNDg5MTYiLCJhdWQiOiJwYXNzcG9ydCIsImV4cCI6MTU0MzI4OTE4NiwiaWF0IjoxNTQwNjk3MTg2fQ.N6aZR0YN7Zant9Z05gjHFE3TG0Wj5lgCDlWncYSpWzUi8U8jPhew9bWWIZKi5rDMaKbQUDQ_ylnBiTUr55IZMuWmPK3wlEhygt4M4KIx69Y_A3lxiyB50twHxc-cpLkyoiNt6eZ8wvAaCsc43qvJ3ECkOAK1mgmY672xg5SPdJYWqqcjVncyPTcPGZtSyO-oZH7omT76TZRY-h2bPJP-PWN4I4_mFFHMpr-oZtzXRConFsHO5NMbfn7QQlzWfebMMXY3g26gubdJn_U46E68awvh5fY4P0gLMhCMesUtt_HIUNMjyU60tfgd2-dUJhRD-bYNO2jnhnEBCg446SlMPg");
        Map<String, String> header = new HashMap();
        header.put("X-App-ID", "CSDN-APP");
        header.put("version", "3.5.0");
        header.put("X-OS", "android");
        header.put("Referer", "http://gw.csdn.net");
        header.put("JWT-TOKEN", TextUtils.isEmpty(LoginPrefs.getJwtToken()) ? "" : LoginPrefs.getJwtToken());
        header.put("Authorization", TextUtils.isEmpty(LoginPrefs.getJwtToken()) ? "" : "Bearer " + LoginPrefs.getJwtToken());
        String deviceId = DevicesIdUtil.getDeviceId(CPApplication.INSTANCE);
        header.put("X-Device-ID", deviceId);
        header.put("X-OS", "Android");
        header.put("X-App-ID", "CSDN-APP");
        header.put("X-Access-Token", MD5Util.md5(deviceId + "AndroidCSDN-APPb85fF96d-7Aa4-4Ec1-bf1D-2133c1A45656"));
        if (requestName != null && (requestName.equals(MarkUtils.CREATETOPIC) || requestName.equals(MarkUtils.FEEDBACK) || requestName.equals(MarkUtils.BLOG_COMMENT_ADD) || requestName.equals(MarkUtils.CREATEPOST))) {
            header.put("Content-Type", URLEncodedUtils.CONTENT_TYPE);
        }
        return header;
    }
}
