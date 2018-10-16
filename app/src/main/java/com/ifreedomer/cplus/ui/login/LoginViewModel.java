package com.ifreedomer.cplus.ui.login;


import android.content.Context;
import android.text.TextUtils;

import com.ifreedomer.cplus.entity.UserInfo;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.UserInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.SPUtil;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;

public class LoginViewModel extends ViewModel {

    public static final String USER_KEY = "user_key";
    public static final String SESSION_KEY = "session_key";
    public static final String SESSION_EXPIRE_KEY = "session_expire_key";

    public boolean canFastLogin(Context context) {
        String expireTime = (String) SPUtil.get(context, SESSION_EXPIRE_KEY, "");
        if (TextUtils.isEmpty(expireTime) || Long.parseLong(expireTime) < System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public UserInfo fastLogin(Context context) {
        String userInfoStr = (String) SPUtil.get(context, USER_KEY, "");
        UserInfo userInfo = HttpManager.getInstance().getGson().fromJson(userInfoStr, UserInfo.class);
        GlobalDataManager.getInstance().setUserInfo(userInfo);
        GlobalDataManager.getInstance().setSessionId((String) SPUtil.get(context, SESSION_KEY, ""));
        return userInfo;
    }

    public Observable<PayLoad<UserInfoResp>> login(String account, String password) {
        return HttpManager.getInstance().login(account, password);
    }




    public void saveLoginInfo(Context context, PayLoad<V2ProfileResp> v2ProfileRespPayLoad) {
        UserInfo userInfo = new UserInfo();
        GlobalDataManager.getInstance().setSessionId(v2ProfileRespPayLoad.getSessionId());
        userInfo.setNickName(v2ProfileRespPayLoad.getData().getNickname());
        userInfo.setAvatar(v2ProfileRespPayLoad.getData().getAvatar());
        GlobalDataManager.getInstance().setUserInfo(userInfo);
        SPUtil.put(context, USER_KEY, HttpManager.getInstance().getGson().toJson(userInfo));
        SPUtil.put(context, SESSION_KEY, v2ProfileRespPayLoad.getSessionId());
        SPUtil.put(context, SESSION_EXPIRE_KEY, v2ProfileRespPayLoad.getSessionExpired());
    }

}
