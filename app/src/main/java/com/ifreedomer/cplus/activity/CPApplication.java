package com.ifreedomer.cplus.activity;

import android.app.Application;
import android.content.Context;

import com.ifreedomer.basead.BaseADSplashActivity;
import com.ifreedomer.cplus.R;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

import androidx.multidex.MultiDex;

public class CPApplication extends Application {
    public static CPApplication INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        BaseADSplashActivity.setJumpToTarget(true);
        BaseADSplashActivity.setTargetClass(LoginActivity.class);
        BaseADSplashActivity.setSplashLayoutId(R.layout.activity_splash);
        CrashReport.initCrashReport(getApplicationContext(), "2bab5d07a6", false);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
