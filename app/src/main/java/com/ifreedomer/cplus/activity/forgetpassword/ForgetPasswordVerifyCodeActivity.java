package com.ifreedomer.cplus.activity.forgetpassword;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.util.ToolbarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordVerifyCodeActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PHONE_KEY = "phone";
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.verifyCodeEt)
    EditText verifyCodeEt;
    @BindView(R.id.nextStepBtn)
    Button nextStepBtn;
    @BindView(R.id.phoneTipTv)
    TextView phoneTipTv;
    @BindView(R.id.countDownTv)
    TextView countDownTv;
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
        setContentView(R.layout.activity_forget_password_verify_code);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.reset_pwd_2));
        String phoneNum = getIntent().getStringExtra(PHONE_KEY);
        phoneTipTv.setText(getString(R.string.verifyCodeSended) + phoneNum);
        countDownTv.setOnClickListener(this);
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.countDownTv:
                if (countDownTv.getText().toString().equals(getString(R.string.resend))) {
                    return;
                }
                break;
            case R.id.nextStepBtn:
                HttpManager.getInstance().verifyCode(verifyCodeEt.getText().toString());
                break;
        }
    }
}
