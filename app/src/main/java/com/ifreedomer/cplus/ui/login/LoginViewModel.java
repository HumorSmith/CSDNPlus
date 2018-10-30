package com.ifreedomer.cplus.ui.login;


import android.content.Context;

import com.ifreedomer.cplus.entity.UserInfo;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.GetUserTokenResp;
import com.ifreedomer.cplus.http.protocol.resp.LoginAppV1TokenResp;
import com.ifreedomer.cplus.http.protocol.resp.UserInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.LoginPrefs;
import com.ifreedomer.cplus.util.SPUtil;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;

public class LoginViewModel extends ViewModel {

    public static final String USER_KEY = "user_key";
    public static final String SESSION_KEY = "session_key";
    public static final String SESSION_EXPIRE_KEY = "session_expire_key";
    public static final String TOKEN = "token";
    public static final String LOGINED = "logined";
    public static final String ACCOUNT_KEY = "account";
    public static final String PASSWORD_KEY = "password";

    public boolean canFastLogin(Context context) {
        return (boolean) SPUtil.get(context, LOGINED, false);
    }

    public UserInfo fastLogin(Context context) {
        String userInfoStr = (String) SPUtil.get(context, USER_KEY, "");
        UserInfo userInfo = HttpManager.getInstance().getGson().fromJson(userInfoStr, UserInfo.class);
        GlobalDataManager.getInstance().setUserInfo(userInfo);
        GlobalDataManager.getInstance().setSessionId((String) SPUtil.get(context, SESSION_KEY, ""));
        return userInfo;
    }

    public Observable<PayLoad<UserInfoResp>> loginV3(String account, String password) {
        return HttpManager.getInstance().loginV3(account, password);
    }


    public Observable<PayLoad<LoginAppV1TokenResp>> loginAppV1(String account, String password) {
        return HttpManager.getInstance().loginAppV1(account, password);
    }


    public Observable<PayLoad<GetUserTokenResp>> getUserToken() {
        return HttpManager.getInstance().getUserToken();
    }





    public void saveLoginInfo(Context context, PayLoad<V2ProfileResp> v2ProfileRespPayLoad,String userName) {
        UserInfo userInfo = new UserInfo();
        GlobalDataManager.getInstance().setSessionId(v2ProfileRespPayLoad.getSessionId());
        userInfo.setNickName(v2ProfileRespPayLoad.getData().getNickname());
        userInfo.setUserName(userName);
        userInfo.setSign(v2ProfileRespPayLoad.getData().getSelfdesc());
        userInfo.setAvatar(v2ProfileRespPayLoad.getData().getAvatar());
        GlobalDataManager.getInstance().setUserInfo(userInfo);
        SPUtil.put(context, USER_KEY, HttpManager.getInstance().getGson().toJson(userInfo));
        SPUtil.put(context, SESSION_KEY, v2ProfileRespPayLoad.getSessionId());
        SPUtil.put(context, SESSION_EXPIRE_KEY, v2ProfileRespPayLoad.getSessionExpired());
    }

    public void saveAappV1Token(String token) {
        LoginPrefs.setJwtToken(token);
    }
}
