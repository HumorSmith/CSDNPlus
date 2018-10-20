package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.GetUserTokenResp;
import com.ifreedomer.cplus.http.protocol.resp.LoginAppV1TokenResp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAppV1Api {

    @POST("https://passport.csdn.net/v1/api/app/login/doLogin")
    Observable<PayLoad<LoginAppV1TokenResp>> login(@Body RequestBody loginReq);

    @POST("https://passport.csdn.net/v1/api/app/login/getUserToken")
    Observable<PayLoad<GetUserTokenResp>> getUserToken();
}
