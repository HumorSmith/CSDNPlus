package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.UserInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {
    @GET("api/v2/blog/blog_user_info")
    Observable<String> getBlogUserinfo(@Query("username") String username);
    @POST("v3/login")
    Observable<PayLoad<UserInfoResp>> login(@Query("username") String username, @Query("password") String password, @Query("SessionId") String sessionId);
    @GET("api/v2/user/profile")
    Observable<PayLoad<V2ProfileResp>> getUserProfile(@Query("username") String username, @Query("SessionId") String sessionId);
}
