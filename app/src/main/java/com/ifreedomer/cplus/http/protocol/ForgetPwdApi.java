package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.ForgetPwdUserNameResp;
import com.ifreedomer.cplus.http.protocol.resp.GetVerifyCodeResp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ForgetPwdApi {
    @GET("https://passport.csdn.net/v1/service/mobiles/{phone}")
    Observable<ForgetPwdUserNameResp> requestUserName(@Path("phone") String phone, @Query("comeFrom") int comeFrom, @Query("code") String code);


    @GET("https://passport.csdn.net/v1/fpwd/sendVerifyCode")
    Observable<GetVerifyCodeResp> getVerifyCode(RequestBody body);
}
