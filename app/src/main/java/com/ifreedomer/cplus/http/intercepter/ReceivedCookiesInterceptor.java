package com.ifreedomer.cplus.http.intercepter;

import android.content.SharedPreferences;

import com.ifreedomer.cplus.activity.CPApplication;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.SPUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    public static final String TAG = ReceivedCookiesInterceptor.class.getSimpleName();
    public static Headers headers;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
//
//        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//            HashSet<String> cookies = new HashSet<>();
//
//            for (String header : originalResponse.headers("Cookie")) {
//                cookies.add(header);
//            }
//            SPUtil.put(CPApplication.INSTANCE, "cookie", cookies);
//            LogUtil.d(TAG,"cookie = "+cookies);
//        }
        headers = originalResponse.headers();
        LogUtil.d(TAG, "headers = " + headers.toString());
        return originalResponse;
    }
}

