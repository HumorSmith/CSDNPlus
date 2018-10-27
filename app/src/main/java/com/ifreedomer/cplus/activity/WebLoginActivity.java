package com.ifreedomer.cplus.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.entity.DeployBlogContentInfo;
import com.ifreedomer.cplus.http.center.HttpManager;

import androidx.appcompat.app.AppCompatActivity;

public class WebLoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = WebLoginActivity.class.getSimpleName();
    private WebView mWebView;
    public static String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deploy);
        findViewById(R.id.loginBtn).setOnClickListener(this);
        findViewById(R.id.deployBtn).setOnClickListener(this);
        findViewById(R.id.msgBtn).setOnClickListener(this);
        mWebView = findViewById(R.id.webview);


        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();


        // 设置 webView 是否支持 JavaScript 的调用（应用中涉及原生与 JS 交互的必须设置为 true）
        webSettings.setJavaScriptEnabled(true);
        // 设置是否允许 JS 开启新窗口(function window.open())
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


// 设置编码方式

// 设置是否自适应屏幕，（一般图片和网页缩放同时使用）
        webSettings.setUseWideViewPort(true); //将图片调整到适合 webView 的大小
        webSettings.setLoadWithOverviewMode(true); //缩放至屏幕的大小（显示未做移动端兼容的 PC 网页）

// 设置是否支持缩放，默认为支持
        webSettings.setSupportZoom(true);
// 设置内置的缩放控件
        webSettings.setBuiltInZoomControls(true);
// 隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(true);


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                CookieManager cookieManager = CookieManager.getInstance();
                cookie = cookieManager.getCookie(url);
                Toast.makeText(WebLoginActivity.this, cookie, Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                mWebView.loadUrl("https://passport.csdn.net/account/login");
                break;
            case R.id.deployBtn:
                DeployBlogContentInfo deployBlogContentInfo = new DeployBlogContentInfo();
                HttpManager.getInstance().saveArticle(deployBlogContentInfo);
//                saveArticleRespObservable.subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String saveArticleResp) throws Exception {
//                        Log.d(TAG,saveArticleResp.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d(TAG,throwable.toString());
//                    }
//                });
                break;
            case R.id.msgBtn:
                mWebView.loadUrl("https://msg.csdn.net/");
                break;
        }
    }
}
