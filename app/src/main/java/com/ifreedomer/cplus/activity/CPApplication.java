package com.ifreedomer.cplus.activity;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

public class CPApplication extends Application {
    public static CPApplication INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        CrashReport.initCrashReport(getApplicationContext(), "2bab5d07a6", false);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
    }
}
