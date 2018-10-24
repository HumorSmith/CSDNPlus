package com.ifreedomer.cplus.activity.forgetpassword;

import android.os.Bundle;
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

public class ForgetPasswordResetPwdActivity extends AppCompatActivity implements View.OnClickListener {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_reset_activity);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.reset_pwd_3));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextStepBtn:
                HttpManager.getInstance().verifyCode(verifyCodeEt.getText().toString());
                break;
        }
    }
}
