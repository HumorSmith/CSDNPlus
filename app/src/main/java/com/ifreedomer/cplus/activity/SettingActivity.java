package com.ifreedomer.cplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.SPUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.SettingItem;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ifreedomer.cplus.ui.login.LoginViewModel.LOGINED;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.clearItem)
    SettingItem clearItem;
    @BindView(R.id.loginOutItem)
    SettingItem loginOutItem;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        clearItem.setText(getString(R.string.clear_cache));
        loginOutItem.setText(getString(R.string.loginOut));
        clearItem.setOnClickListener(this);
        loginOutItem.setOnClickListener(this);
        ToolbarUtil.setTitleBarWithBack(this,toolbar,getString(R.string.setting) );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearItem:
                File cacheDir = getCacheDir();

                if (cacheDir.exists()) {
                    boolean delete = cacheDir.delete();
                    String clearStr = delete ? getString(R.string.clear_success) : getString(R.string.clear_failed);
                    WidgetUtil.showSnackBar(this, clearStr);
                } else {
                    WidgetUtil.showSnackBar(this, getString(R.string.no_cache));
                }
                break;
            case R.id.loginOutItem:
                SPUtil.put(this, LOGINED, false);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
