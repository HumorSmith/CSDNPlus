package com.ifreedomer.cplus.http.protocol;


import com.ifreedomer.cplus.http.protocol.resp.FollowOperationResp;
import com.ifreedomer.cplus.http.protocol.resp.FollowResp;
import com.ifreedomer.cplus.http.protocol.resp.GetRelationResp;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FollowApi {
    @GET("cms-app/v1/me/login/my_follow")
    Observable<PayLoad<List<FollowResp>>>  getIdol( @Query("userName") String username, @Query("page") int page, @Query("pagesize") int size);

    @GET("/cms-app/v1/me/login/my_fans")
    Observable<PayLoad<List<FollowResp>>> getFans( @Query("userName") String username, @Query("page") int page, @Query("pagesize") int size);

    @POST("cms-app/v1/me/login/un_follow")
    Observable<PayLoad<FollowOperationResp>> unFollow(@Query("userName") String username, @Query("fans") String fans);

    @POST("https://gw.csdn.net/cms-app/v1/me/login/do_follow")
    Observable<PayLoad<FollowOperationResp>> doFollow(@Body RequestBody requestBody);


    @GET("api/user/relation")
    Observable<PayLoad<GetRelationResp>> getRelation(@Query("userName") String username);

}
