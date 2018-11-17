package com.ifreedomer.cplus.activity.forgetpassword;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.req.GetVerifyCodeReq;
import com.ifreedomer.cplus.http.protocol.resp.ResetPwdResp;
import com.ifreedomer.cplus.util.LogUtil;
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
    public static final String TAG = ForgetPasswordGetCodeActivity.class.getSimpleName();
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
    @BindView(R.id.verifyCodeEt)
    EditText verifyCodeEt;
    @BindView(R.id.countDownTv)
    TextView countDownTv;
    @BindView(R.id.pwdEt)
    EditText pwdEt;
    private String mUserName;


    private int countDownTime = 60;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (countDownTime <= 0) {
                countDownTv.setText(R.string.resend);
                return;
            }
            countDownTime = countDownTime - 1;
            countDownTv.setText(countDownTime + "s");
            mHandler.sendEmptyMessageDelayed(0, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        ButterKnife.bind(this);
        initWebView();
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
                LogUtil.d(TAG, "url = " + url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }


        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);


            }
        });


        webview.loadUrl("https://passport.csdn.net/passport_fe/forget.html");
    }


    public void clickHtmlSendVerifyCode(String phone) {
        StringBuilder builder = new StringBuilder();
        // add javascript prefix
        builder.append("javascript:(function() { ");
        builder.append("alert(\"exe before\");");
        builder.append("document.getElementById(\"phone\").value = \"" + phone + "\";");
        builder.append("document.getElementsByClassName(\"btn btn-confirm btn-control\")[0].click();");
        builder.append("alert(\"exe after\");");
        // add javascript suffix
        builder.append("})()");
        LogUtil.d(TAG, builder.toString());
        webview.loadUrl(builder.toString());
    }

    private void initView() {
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.reset_pwd));
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
                clickHtmlSendVerifyCode(phoneEt.getText().toString());

//                Observable<ForgetPwdUserNameResp> forgetPwdRespObservable = HttpManager.getInstance().getUserNameByPhone("0086",
//                        phoneEt.getText().toString());
//
//
//                forgetPwdRespObservable.subscribe(forgetPwdResp -> {
//                    if (forgetPwdResp.isStatus()) {
//                        mUserName = forgetPwdResp.getData().getUsername();
//                        getVerifyCode(mUserName);
//                    } else {
//                        WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, forgetPwdResp.getMsg());
//                    }
//                }, throwable -> WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, throwable.getMessage()));
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
