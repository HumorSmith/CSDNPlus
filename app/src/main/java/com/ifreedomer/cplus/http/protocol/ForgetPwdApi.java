package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.ForgetPwdUserNameResp;
import com.ifreedomer.cplus.http.protocol.resp.ResetPwdResp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ForgetPwdApi {
    @GET("https://passport.csdn.net/v1/service/mobiles/{phone}")
    Observable<ForgetPwdUserNameResp> requestUserName(@Path("phone") String phone, @Query(value = "comeFrom") int comeFrom, @Query("code") String code);


    @POST("https://passport.csdn.net/v1/fpwd/sendVerifyCode")
    Observable<ResetPwdResp> getVerifyCode(@Body  RequestBody body);

    @GET("https://passport.csdn.net/v1/fpwd/checkFpwdSendVerifyCodeTime")
    Observable<ResetPwdResp> checkResetPwdTime();
}
