package com.ifreedomer.tencent;

import android.app.Activity;


public class TencentManager {
    private static final String TAG = TencentManager.class.getSimpleName();
    private static final TencentManager ourInstance = new TencentManager();
    private TencentQQ tencentQQ = new TencentQQ();

    public static TencentManager getInstance() {
        return ourInstance;
    }

    private TencentManager() {
    }

    public void login(Activity activity, String appId, final TencentQQ.LoginCallback loginCallback) {
        tencentQQ.loginRedirect(activity, appId, loginCallback);
    }

    void login(Activity activity) {
        tencentQQ.login(activity);
    }

    public TencentQQ getTencentQQ() {
        return tencentQQ;
    }

}
