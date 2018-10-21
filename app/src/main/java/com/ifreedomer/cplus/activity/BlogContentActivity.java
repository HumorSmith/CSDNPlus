package com.ifreedomer.cplus.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.constant.Constants;
import com.ifreedomer.cplus.entity.BlogContentInfo;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.ShareUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogContentActivity extends AppCompatActivity {
    public static final String DATA = "data";
    private static final String TAG = BlogContentActivity.class.getSimpleName();



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
    private BlogContentInfo mBlogContentInfo;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_content);
        ButterKnife.bind(this);

        StringBuffer sb = new StringBuffer();
        mBlogContentInfo = (BlogContentInfo) getIntent().getSerializableExtra(DATA);
        String userName = mBlogContentInfo.getUserName();
        String title = mBlogContentInfo.getTitle();
        String avatar = mBlogContentInfo.getAvatar();
        String date = mBlogContentInfo.getDate();
        String nickName = mBlogContentInfo.getNickName();
        String id = mBlogContentInfo.getId();
        try {
            dateTv.setText(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToolbarUtil.setTitleBar(this, toolbar, "");
        toolbar.setNavigationIcon(R.mipmap.ic_back);


        Glide.with((View) avatarIv).load(avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);

        blogTitleTv.setText(title);
        nameTv.setText(getIntent().getStringExtra(nickName));

        sb.append(Constants.api_blog_detail_domain).append(userName).append(Constants.api_blog_detail).append(id);
        LogUtil.d(TAG, "url = " + sb.toString() + "   username = " + userName + "   id =" + id);
        setSettings(this.webview.getSettings());
        mUrl = sb.toString();
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
        toolbar.getMenu().findItem(R.id.shareItem).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ShareUtil.shareString(BlogContentActivity.this, mUrl);
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
}
