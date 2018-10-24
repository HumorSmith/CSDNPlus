package com.ifreedomer.tencent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import androidx.annotation.Nullable;


public class LoginQQActivity extends Activity {
    private static final String TAG = LoginQQActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TencentManager.getInstance().login(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, TencentManager.getInstance().getTencentQQ().getTencentLoginListener());
            finish();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
