package com.ifreedomer.cplus.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.ifreedomer.cplus.fragment.OtherUserActivity;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.AddCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.CheckCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.DeleteCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.DiggResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.ShareUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.AVATAR_KEY;
import static com.ifreedomer.cplus.fragment.OtherUserActivity.NICKNAME_KEY;
import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class BlogContentActivity extends AppCompatActivity implements View.OnClickListener {
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
    @BindView(R.id.diggNumTv)
    TextView diggNumTv;
    @BindView(R.id.diggIv)
    ImageView diggIv;
    @BindView(R.id.commentTv)
    TextView commentTv;
    @BindView(R.id.commentIv)
    ImageView commentIv;
    private BlogContentInfo mBlogContentInfo;
    private String mUrl;
    private String mFavoriteId;
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
        ToolbarUtil.setTitleBarWithBack(this, toolbar, "");
        toolbar.setNavigationIcon(R.mipmap.ic_back);


        Glide.with((View) avatarIv).load(avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);
        avatarIv.setOnClickListener(this);

        blogTitleTv.setText(title);
        nameTv.setText(nickName);

        sb.append(Constants.api_blog_detail_domain).append(userName).append(Constants.api_blog_detail).append(id);
        LogUtil.d(TAG, "url = " + sb.toString() + "   username = " + userName + "   id =" + id);
        setSettings(this.webview.getSettings());
        mUrl = sb.toString();
        this.webview.loadUrl(sb.toString());

        go2WebActivity(this, webview);
        getArticleDetail(id);

        diggIv.setOnClickListener(this);

        commentIv.setOnClickListener(v -> {
            Intent intent = new Intent(BlogContentActivity.this, CommentActivity.class);
            intent.putExtra(CommentActivity.ARTICLE_ID, id);
            intent.putExtra(CommentActivity.COUNT, mBlogContentInfo.getCommentNum());
            startActivity(intent);
        });

        commentEt.setOnClickListener(v -> {
            Intent intent = new Intent(BlogContentActivity.this, CommentActivity.class);
            intent.putExtra(CommentActivity.ARTICLE_ID, id);
            intent.putExtra(CommentActivity.COUNT, mBlogContentInfo.getCommentNum());
            startActivity(intent);
        });

    }

    private void getArticleDetail(String id) {
        Disposable subscribe = HttpManager.getInstance().getArticleInfo(id).subscribe(articleRespPayLoad -> {
            if (articleRespPayLoad.getCode() == PayLoad.SUCCESS) {
                commentTv.setText(articleRespPayLoad.getData().getCommentCount());
                diggNumTv.setText(articleRespPayLoad.getData().getDigg());
                diggIv.setSelected(articleRespPayLoad.getData().isIs_digg());
            } else {
                WidgetUtil.showSnackBar(BlogContentActivity.this, articleRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(BlogContentActivity.this, throwable.getMessage()));
    }

    private void checkFavorite() {
        Observable<PayLoad<CheckCollectResp>> checkFavoriteObserver = HttpManager.getInstance().checkFavorite(GlobalDataManager.getInstance().getUserInfo().getUserName(), mUrl);
        Disposable subscribeCheckFavorite = checkFavoriteObserver.subscribe(checkCollectRespPayLoad -> {
            if (checkCollectRespPayLoad.getCode() == PayLoad.SUCCESS) {
                mFavoriteId = checkCollectRespPayLoad.getData().getFavorite_id();
                setCollectIcon(checkCollectRespPayLoad.getData().getIs_exist() == 1);
            } else {
                WidgetUtil.showSnackBar(BlogContentActivity.this, checkCollectRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(BlogContentActivity.this, throwable.getMessage()));
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


    @Override
    protected void onPostResume() {
        super.onPostResume();
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
        initMenu();
        checkFavorite();
        return super.onPrepareOptionsMenu(menu);
    }

    private void initMenu() {
        toolbar.inflateMenu(R.menu.blog_content_menu);
        toolbar.getMenu().findItem(R.id.shareItem).setOnMenuItemClickListener(item -> {
            ShareUtil.shareString(BlogContentActivity.this, mUrl);
            return false;
        });
        toolbar.getMenu().findItem(R.id.collectItem).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.isChecked()) {
                    deleteCollect();
                } else {
                    addCollect();
                }
                return false;
            }
        });
    }

    private void setCollectIcon(boolean isChecked) {
        MenuItem item = toolbar.getMenu().findItem(R.id.collectItem);
        item.setChecked(isChecked);
        item.setIcon(isChecked ? R.mipmap.ic_collect_press : R.mipmap.ic_collect);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.diggIv:
                Observable<PayLoad<DiggResp>> digg = HttpManager.getInstance().digg(GlobalDataManager.getInstance().getUserInfo().getUserName(), mBlogContentInfo.getId());
                digg.subscribe(diggRespPayLoad -> {
                    if (diggRespPayLoad.getCode() == PayLoad.SUCCESS) {
//                        WidgetUtil.showSnackBar(BlogContentActivity.this, diggRespPayLoad.getMessage());
                        boolean status = diggRespPayLoad.getData().isStatus();
                        diggIv.setSelected(status);
                        String msg = status ? getString(R.string.digg_success) : getString(R.string.cancel_digg);
                        WidgetUtil.showSnackBar(BlogContentActivity.this, msg);
                        diggNumTv.setText(diggRespPayLoad.getData().getDigg() + "");
                    } else {
                        WidgetUtil.showSnackBar(BlogContentActivity.this, diggRespPayLoad.getMessage());
                    }
                }, throwable -> WidgetUtil.showSnackBar(BlogContentActivity.this, throwable.getMessage()));
                break;
            case R.id.avatarIv:
                Intent intent = new Intent(this, OtherUserActivity.class);
                intent.putExtra(USERNAME_KEY, mBlogContentInfo.getUserName());
                intent.putExtra(NICKNAME_KEY, mBlogContentInfo.getNickName());
                intent.putExtra(AVATAR_KEY, mBlogContentInfo.getAvatar());
                startActivity(intent);
                break;
        }
    }

    private void deleteCollect() {
        Observable<PayLoad<DeleteCollectResp>> collectObserver = HttpManager.getInstance().deleteCollect(mFavoriteId + "");
        Disposable subscribe = collectObserver.subscribe(deleteCollectRespPayLoad -> {
            if (deleteCollectRespPayLoad.getCode() == PayLoad.SUCCESS && deleteCollectRespPayLoad.getData().getSuccess() == 1) {
                WidgetUtil.showSnackBar(BlogContentActivity.this, getString(R.string.cancel_success));
                setCollectIcon(false);
            } else {
                if (!TextUtils.isEmpty(deleteCollectRespPayLoad.getMessage())) {
                    WidgetUtil.showSnackBar(BlogContentActivity.this, deleteCollectRespPayLoad.getMessage());
                }
                if (!TextUtils.isEmpty(deleteCollectRespPayLoad.getData().getMsg())) {
                    WidgetUtil.showSnackBar(BlogContentActivity.this, deleteCollectRespPayLoad.getData().getMsg());
                }

            }
        }, throwable -> WidgetUtil.showSnackBar(BlogContentActivity.this, throwable.getMessage()));
    }

    private void addCollect() {
        Observable<PayLoad<AddCollectResp>> collectObserver = HttpManager.getInstance().addCollect(mBlogContentInfo.getTitle(), mUrl, GlobalDataManager.getInstance().getUserInfo().getUserName());
        Disposable subscribe = collectObserver.subscribe(addCollectRespPayLoad -> {
            if (addCollectRespPayLoad.getCode() == PayLoad.SUCCESS && addCollectRespPayLoad.getData().getSuccess() == 1) {
                WidgetUtil.showSnackBar(BlogContentActivity.this, getString(R.string.collect_success));
                mFavoriteId = addCollectRespPayLoad.getData().getData().getId() + "";
                setCollectIcon(true);
            } else {
                if (!TextUtils.isEmpty(addCollectRespPayLoad.getMessage())) {
                    WidgetUtil.showSnackBar(BlogContentActivity.this, addCollectRespPayLoad.getMessage());
                }
                if (!TextUtils.isEmpty(addCollectRespPayLoad.getData().getMsg())) {
                    WidgetUtil.showSnackBar(BlogContentActivity.this, addCollectRespPayLoad.getData().getMsg());
                }
            }
        }, throwable -> WidgetUtil.showSnackBar(BlogContentActivity.this, throwable.getMessage()));
    }
}
