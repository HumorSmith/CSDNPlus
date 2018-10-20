package com.ifreedomer.cplus.http.intercepter;

import android.text.TextUtils;
import android.util.Log;

import com.ifreedomer.cplus.CPApplication;
import com.ifreedomer.cplus.util.AppUtil;
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
        Map<String, String> header = new HashMap();
        header.put("X-App-ID", "android");
        header.put("version", AppUtil.getVersionName(CPApplication.INSTANCE));
        header.put("Referer", "http://ms.csdn.net");
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
