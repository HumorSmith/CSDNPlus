package com.ifreedomer.cplus.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.ifreedomer.basead.BaseADSplashActivity;
import com.ifreedomer.cplus.R;
import com.ifreedomer.tencentad.TencentVerticalSplashActivity;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

import androidx.multidex.MultiDex;

public class CPApplication extends Application {
    public static CPApplication INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        BaseADSplashActivity.setAppId("1108012335");
        BaseADSplashActivity.setSplashId("1000941955057643");
        BaseADSplashActivity.setTargetClass(LoginActivity.class);
        startActivity(new Intent(this, TencentVerticalSplashActivity.class));

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
