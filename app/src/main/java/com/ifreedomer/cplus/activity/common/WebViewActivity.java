package com.ifreedomer.cplus.activity.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.ToolbarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    public static final String TITLE_KEY = "title";
    public static final String URL = "url";
    public static final String SHOW_TITLE = "show";
    public static final String OPEN_BROWER = "openbrower";
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        boolean showTitle = getIntent().getBooleanExtra(SHOW_TITLE, true);
        toolbar.setVisibility(showTitle ? View.VISIBLE : View.GONE);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getIntent().getStringExtra(TITLE_KEY));
        String url = getIntent().getStringExtra(WebViewActivity.URL);
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
        webSettings.setAllowFileAccess(true);
        webview.loadUrl(url);

//        webview.loadUrl("file://assets/forget_pwd/get_pwd.html");
        boolean overrideUrl = getIntent().getBooleanExtra(OPEN_BROWER, true);
//        if (overrideUrl) {
//        webview.setWebChromeClient(new WebChromeClient() {
////                @Override
////                public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                    return false;
////                }
//
//
//        });
//        }
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("wtloginmqq://")) {
                    Uri uri = Uri.parse(url); //url为你要链接的地址
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    return true;
                }
                view.loadUrl(url);
                return false;
            }
        });

    }
}
