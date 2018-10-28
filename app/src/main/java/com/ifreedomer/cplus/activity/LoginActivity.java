package com.ifreedomer.cplus.activity;

import android.Manifest;
import android.os.Bundle;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.ui.login.LoginFragment;
import com.ifreedomer.permissionhelpler.PermissionHelper;
import com.umeng.analytics.MobclickAgent;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        new PermissionHelper(this).requestPermission(new PermissionHelper.Callback() {
            @Override
            public void onPermissionResult(boolean allGranted) {
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, LoginFragment.newInstance())
                            .commitAllowingStateLoss();
                }
            }
        }, PERMISSIONS);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
