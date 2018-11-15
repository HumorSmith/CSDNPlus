package com.ifreedomer.cplus.http.intercepter;

import android.util.Log;

import com.ifreedomer.cplus.activity.CPApplication;
import com.ifreedomer.cplus.util.DateUtil;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.SPUtil;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : jc.lu
 * @create : 17/07/07.
 */
public class AddCookiesInterceptor implements Interceptor {
    public static final String TAG = AddCookiesInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (ReceivedCookiesInterceptor.headers != null) {
            for (int i = 0; i < ReceivedCookiesInterceptor.headers.size(); i++) {
                builder.addHeader(ReceivedCookiesInterceptor.headers.name(i), ReceivedCookiesInterceptor.headers.value(i));
            }
        }
        Request build = builder.build();
        LogUtil.d(TAG, build.headers().toString());
        return chain.proceed(build);
    }
}

