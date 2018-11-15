package com.ifreedomer.cplus.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TestWebViewActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = TestWebViewActivity.class.getSimpleName();
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.sendVerifyCodeBtn)
    Button sendVerifyCodeBtn;
    public String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_view);
        ButterKnife.bind(this);
        sendVerifyCodeBtn.setOnClickListener(this);

        //声明WebSettings子类
        WebSettings webSettings = webview.getSettings();
        // 设置 webView 是否支持 JavaScript 的调用（应用中涉及原生与 JS 交互的必须设置为 true）
        webSettings.setJavaScriptEnabled(true);
        // 设置是否允许 JS 开启新窗口(function window.open())
        //webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 设置是否自适应屏幕，（一般图片和网页缩放同时使用）
        webSettings.setUseWideViewPort(true); //将图片调整到适合 webView 的大小
        webSettings.setLoadWithOverviewMode(true); //缩放至屏幕的大小（显示未做移动端兼容的 PC 网页）

        // 设置是否支持缩放，默认为支持
        webSettings.setSupportZoom(true);
        // 设置内置的缩放控件
        webSettings.setBuiltInZoomControls(true);
        // 隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(true);

//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        } else {
            try {
                Class<?> clazz = webSettings.getClass();
                Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);
                method.invoke(webSettings, true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        webview.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                CookieManager cookieManager = CookieManager.getInstance();
                if (TextUtils.isEmpty(cookie)) {
                    cookie = cookieManager.getCookie(url);
                    LogUtil.d(TAG, "onPageStarted cookie out = " + cookie);

                    if (!TextUtils.isEmpty(cookie)) {
                        LogUtil.d(TAG, "onPageStarted cookie = " + cookie);
                        webview.loadUrl("file:///android_asset/forget_pwd/get_pwd.html");

                    }

                }
            }


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Map<String, String> requestHeaders = request.getRequestHeaders();
                requestHeaders.put("Referer", "https://passport.csdn.net/passport_fe/forget.html");
                requestHeaders.put("Content-Type", "application/json;charset=UTF-8");
                requestHeaders.put("X-Requested-With", "XMLHttpRequest");
                requestHeaders.put("Origin", "https://passport.csdn.net");
                requestHeaders.put("Accept", "application/json");
                requestHeaders.put("Cookie", cookie);
                LogUtil.d(TAG + "=======>", request.getRequestHeaders().toString());
                return super.shouldInterceptRequest(view, request);
            }

        });


    }


    public static boolean syncCookie(String url, String cookie) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(CPApplication.INSTANCE);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url, cookie);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
        String newCookie = cookieManager.getCookie(url);
        return TextUtils.isEmpty(newCookie) ? false : true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendVerifyCodeBtn:
                webview.loadUrl("https://passport.csdn.net/passport_fe/forget.html");
//                webview.loadUrl("file:///android_asset/forget_pwd/get_pwd.html");

                break;

        }
    }


}
