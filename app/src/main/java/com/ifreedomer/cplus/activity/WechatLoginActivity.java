package com.ifreedomer.cplus.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.ToolbarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WechatLoginActivity extends AppCompatActivity {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wechatIv)
    ImageView wechatIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_login);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.scan_code_register));
        Glide.with(wechatIv).load("https://open.weixin.qq.com/connect/qrcode/081s5tHrCTDIUa-r").into(wechatIv);
    }
}
