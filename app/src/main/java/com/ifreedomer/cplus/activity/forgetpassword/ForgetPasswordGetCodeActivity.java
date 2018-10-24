package com.ifreedomer.cplus.activity.forgetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.req.GetVerifyCodeReq;
import com.ifreedomer.cplus.http.protocol.resp.ForgetPwdUserNameResp;
import com.ifreedomer.cplus.http.protocol.resp.GetVerifyCodeResp;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        ButterKnife.bind(this);

        initView();

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
                Observable<ForgetPwdUserNameResp> forgetPwdRespObservable = HttpManager.getInstance().getUserNameByPhone(countCodeTv.getText().toString(),
                        phoneEt.getText().toString());


                forgetPwdRespObservable.subscribe(forgetPwdResp -> {
                    if (forgetPwdResp.isStatus()) {
                        getVerifyCode(forgetPwdResp.getData().getUsername());
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
        Observable<GetVerifyCodeResp> verifyCodeObserver = HttpManager.getInstance().getVerifyCode(getVerifyCodeReq);
        Disposable subscribe = verifyCodeObserver.subscribe(getVerifyCodeResp -> {
            if (getVerifyCodeResp.isStatus()) {
                Intent intent = new Intent(ForgetPasswordGetCodeActivity.this, ForgetPasswordVerifyCodeActivity.class);
                intent.putExtra(ForgetPasswordVerifyCodeActivity.PHONE_KEY, phoneEt.getText().toString());
                startActivity(intent);
            } else {
                WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, getVerifyCodeResp.getMsg());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                WidgetUtil.showSnackBar(ForgetPasswordGetCodeActivity.this, throwable.getMessage());
            }
        });
    }

//    public void checkTime(){
////        https://passport.csdn.net/v1/fpwd/checkFpwdSendVerifyCodeTime
//    }
}
