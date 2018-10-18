package com.ifreedomer.cplus;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ifreedomer.cplus.constant.Constants;
import com.ifreedomer.cplus.util.DateUtil;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogContentActivity extends AppCompatActivity {
    private static final String TAG = BlogContentActivity.class.getSimpleName();
    public static final String USER_NAME_KEY = "username";
    public static final String ARTICLE_ID_KEY = "id";
    public static final String TITLE_KEY = "title";
    public static final String AVATAR_KEY = "avatar";
    public static final String DATE_KEY = "date";
    public static final String NICK_NAME_KEY = "nickname";


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.titleTv)
    TextView titleTv;

    @BindView(R.id.avatarIv)
    ImageView avatarIv;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.dateTv)
    TextView dateTv;
    @BindView(R.id.blogTitleTv)
    TextView blogTitleTv;
    @BindView(R.id.commentEt)
    EditText commentEt;
    @BindView(R.id.collectNumTv)
    TextView collectNumTv;
    @BindView(R.id.collectIv)
    ImageView collectIv;
    @BindView(R.id.commentTv)
    TextView commentTv;
    @BindView(R.id.commentIv)
    ImageView commentIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_content);
        ButterKnife.bind(this);

        StringBuffer sb = new StringBuffer();
        String userName = getIntent().getStringExtra(USER_NAME_KEY);
        String id = getIntent().getStringExtra(ARTICLE_ID_KEY);
        String title = getIntent().getStringExtra(TITLE_KEY);
        String avatar = getIntent().getStringExtra(AVATAR_KEY);
        String date = getIntent().getStringExtra(DATE_KEY);
        try {
            dateTv.setText(DateUtil.timeStamp2DateStringWithMonth(BlogContentActivity.this, Long.parseLong(date) * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToolbarUtil.setTitleBar(this, toolbar, "");
        toolbar.setNavigationIcon(R.mipmap.ic_back);


        Glide.with((View) avatarIv).load(avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);

        blogTitleTv.setText(title);
        nameTv.setText(getIntent().getStringExtra(NICK_NAME_KEY));

        sb.append(Constants.api_blog_detail_domain).append(userName).append(Constants.api_blog_detail).append(id);
        LogUtil.d(TAG, "url = " + sb.toString() + "   username = " + userName + "   id =" + id);
        setSettings(this.webview.getSettings());

        this.webview.loadUrl(sb.toString());

        go2WebActivity(this, webview);

    }

    protected void setSettings(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        webSettings.setBlockNetworkImage(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(0);
        }
    }


    protected void go2WebActivity(final Activity mActivity, WebView wvcontent) {
        wvcontent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                CSDNUtils.goInnerOrOutWebView(mActivity, url);
                return false;
            }

            public void onPageFinished(WebView view, String url) {
//                blogOnError.onFinish();
                super.onPageFinished(view, url);
            }

            @TargetApi(23)
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                if (errorResponse.getStatusCode() == 404) {
//                    blogOnError.onError();
                }
            }
        });
        wvcontent.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
            }

            public void onReceivedTitle(WebView view, String title) {
                if (Build.VERSION.SDK_INT >= 23) {
                    return;
                }
                if (title.contains("404") || title.contains("Error")) {
//                    blogOnError.onError();
                }
            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.blog_content_menu);
        return super.onPrepareOptionsMenu(menu);
    }
}
