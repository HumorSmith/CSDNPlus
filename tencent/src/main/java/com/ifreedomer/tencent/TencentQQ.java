package com.ifreedomer.tencent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;


public class TencentQQ {
    private static final String TAG = TencentQQ.class.getSimpleName();

    private Tencent tencent;
    private LoginCallback loginCallback = null;
    private UserInfoCallback userInfoCallback;
    private String appId;
    private TencentLoginResult tencentLoginResult;
    private Context context;

    //辅助跳转,接受QQ的回调消息
    protected void loginRedirect(Activity activity, String appId, final LoginCallback loginCallback) {
        this.appId = appId;
        this.loginCallback = loginCallback;
        context = activity.getApplicationContext();
        activity.startActivity(new Intent(activity, LoginQQActivity.class));
    }

    //登录QQ
    protected void login(Activity activity) {
        tencent = Tencent.createInstance(appId, activity.getApplicationContext());
        tencent.login(activity, "all", mTencentLoginListener);
    }

    //获取用户信息
    public void getUserInfo(UserInfoCallback userInfoCallback) {
        this.userInfoCallback = userInfoCallback;
        UserInfo userInfo = new UserInfo(context, tencent.getQQToken());
        userInfo.getUserInfo(mUserInfoListener);
    }

    private IUiListener mUserInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            TencentUserInfo tencentUserInfo = JsonUtil.parseUserInfo((JSONObject) o);
            if (userInfoCallback != null) {
                userInfoCallback.onComplete(tencentUserInfo);
            }
        }

        @Override
        public void onError(UiError uiError) {
            Log.d(TAG, uiError.toString());
            if (userInfoCallback != null) {
                userInfoCallback.onError(uiError.errorCode, uiError.errorMessage);
            }
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel");
            if (userInfoCallback != null) {
                userInfoCallback.onCancel();
            }
        }
    };


    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                tencent.setAccessToken(token, expires);
                tencent.setOpenId(openId);
                if (tencentLoginResult != null) {
                    tencentLoginResult.setExpirs(expires);
                    tencentLoginResult.setOpenId(openId);
                    tencentLoginResult.setToken(token);
                }
            }
        } catch (Exception e) {
        }
    }


    public IUiListener getTencentLoginListener() {
        return mTencentLoginListener;
    }

    public Tencent getTencent() {
        return tencent;
    }

    public void setTencent(Tencent tencent) {
        this.tencent = tencent;
    }

    private IUiListener mTencentLoginListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            tencentLoginResult = new TencentLoginResult();
            initOpenidAndToken((JSONObject) o);
            Log.d(TAG, o.toString());
            if (loginCallback != null) {
                loginCallback.onComplete(tencentLoginResult);
            }
        }

        @Override
        public void onError(UiError uiError) {
            Log.d(TAG, uiError.toString());
            if (loginCallback != null) {
                loginCallback.onError(uiError.errorCode, uiError.errorMessage);
            }
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel");
            if (loginCallback != null) {
                loginCallback.onCancel();
            }
        }
    };


    public interface UserInfoCallback {

        void onComplete(TencentUserInfo tencentUserInfo);

        void onError(int errorCode, String errorMsg);

        void onCancel();
    }


    public interface LoginCallback {
        void onComplete(TencentLoginResult tecentLoginResult);

        void onError(int errorCode, String errorMsg);

        void onCancel();

    }
}
