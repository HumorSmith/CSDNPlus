package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.CountResp;
import com.ifreedomer.cplus.http.protocol.resp.LoginAppV1TokenResp;
import com.ifreedomer.cplus.http.protocol.resp.UserInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginV3Api {
    @GET("api/v2/blog/blog_user_info")
    Observable<String> getBlogUserinfo(@Query("username") String username);

    @POST("v3/loginV3")
    Observable<PayLoad<UserInfoResp>> loginV3(@Query("username") String username, @Query("password") String password, @Query("SessionId") String sessionId);
    @GET("/cms-app/v1/me/my")
    Observable<PayLoad<V2ProfileResp>> getUserProfile(@Query("userName") String username);
    @GET("uc/userinfo/count")
    Observable<PayLoad<CountResp>> getCountProfile(@Query("username")String username);

    @GET("api/v2/blog/blog_user_info")
    Observable<String> getUserBlogInfo(@Query("username") String username);



}
