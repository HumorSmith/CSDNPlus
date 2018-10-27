package com.ifreedomer.cplus.http.intercepter;

import com.ifreedomer.cplus.activity.WebLoginActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {
    public static final String TAG = CookieInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());

        requestBuilder.addHeader("Cookie", WebLoginActivity.cookie);
        return chain.proceed(requestBuilder.build());

    }


}
