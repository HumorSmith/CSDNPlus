package com.ifreedomer.cplus.activity.forgetpassword;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.WebLoginActivity;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.req.GetVerifyCodeReq;
import com.ifreedomer.cplus.http.protocol.resp.ForgetPwdUserNameResp;
import com.ifreedomer.cplus.http.protocol.resp.ResetPwdResp;
import com.ifreedomer.cplus.util.SPUtil;
import com.ifreedomer.cplus.util.StringUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ForgetPasswordGetCodeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.countCodeTv)
    TextView countCodeTv;
    @BindView(R.id.forwardIv)
    ImageView forwardIv;
    @BindView(R.id.phoneEt)
    EditText phoneEt;
    @BindView(R.id.nextStepBtn)
    Button nextStepBtn;
    @BindView(R.id.webview)
    WebView webview;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        ButterKnife.bind(this);
//        initWebView();
        initView();

    }

    private void initWebView() {
        //声明WebSettings子类
        WebSettings webSettings = webview.getSettings();


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


        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                CookieManager cookieManager = CookieManager.getInstance();
//                cookieManager.removeAllCookie();
//                SPUtil.put(ForgetPasswordGetCodeActivity.this, "cookie", cookieManager.getCookie(url));
                WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, getString(R.string.get_cookie_success));
            }
        });

        webview.loadUrl("https://passport.csdn.net/passport_fe/forget.html");
    }

    private void initView() {
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.reset_pwd_first));
        nextStepBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextStepBtn:
                if (TextUtils.isEmpty(phoneEt.getText().toString())) {
                    WidgetUtil.showSnackBar(this, getString(R.string.phonenum_cannot_null));
                    return;
                }
                if (!StringUtil.isMobile(phoneEt.getText().toString())) {
                    WidgetUtil.showSnackBar(this, getString(R.string.invalid_phonenum));
                    return;
                }
                Observable<ForgetPwdUserNameResp> forgetPwdRespObservable = HttpManager.getInstance().getUserNameByPhone("0086",
                        phoneEt.getText().toString());


                forgetPwdRespObservable.subscribe(forgetPwdResp -> {
                    if (forgetPwdResp.isStatus()) {
                        mUserName = forgetPwdResp.getData().getUsername();
                        getVerifyCode(mUserName);
                    } else {
                        WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, forgetPwdResp.getMsg());
                    }
                }, throwable -> WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, throwable.getMessage()));
                break;

        }
    }


    public void getVerifyCode(String userName) {
        GetVerifyCodeReq getVerifyCodeReq = new GetVerifyCodeReq();
        getVerifyCodeReq.setMobileOrEmail(phoneEt.getText().toString());
        getVerifyCodeReq.setSendType(1 + "");
        getVerifyCodeReq.setCode("0086");
        getVerifyCodeReq.setUserName(userName);
        Observable<ResetPwdResp> verifyCodeObserver = HttpManager.getInstance().getVerifyCode(getVerifyCodeReq);
        Disposable subscribe = verifyCodeObserver.subscribe(getVerifyCodeResp -> {
            if (getVerifyCodeResp.isStatus()) {
                Intent intent = new Intent(ForgetPasswordGetCodeActivity.this, ForgetPasswordVerifyCodeActivity.class);
                intent.putExtra(ForgetPasswordVerifyCodeActivity.PHONE_KEY, phoneEt.getText().toString());
                startActivity(intent);
            } else {
                WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, getVerifyCodeResp.getMsg());
            }
        }, throwable -> WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, throwable.getMessage()));
    }

    public void checkTime() {
        Observable<ResetPwdResp> resetPwdRespObservable = HttpManager.getInstance().checkPWDResetTime();
        resetPwdRespObservable.subscribe(new Consumer<ResetPwdResp>() {
            @Override
            public void accept(ResetPwdResp resetPwdResp) throws Exception {
                if (resetPwdResp.isStatus()) {
                    getVerifyCode(mUserName);
                } else {
                    WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, resetPwdResp.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, throwable.getMessage());
            }
        });

    }
}
