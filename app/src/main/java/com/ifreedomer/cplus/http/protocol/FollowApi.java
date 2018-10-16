package com.ifreedomer.cplus.http.protocol;


import com.ifreedomer.cplus.http.protocol.resp.FollowOperationResp;
import com.ifreedomer.cplus.http.protocol.resp.FollowResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FollowApi {
    @GET("api/user/myFocus")
    Observable<PayLoad<List<FollowResp>>>  getIdol(@Query("SessionId") String sessionId, @Query("username") String username, @Query("page") int page, @Query("pagesize") int size);

    @GET("api/user/myRelation")
    Observable<PayLoad<List<FollowResp>>> getFans(@Query("SessionId") String sessionId, @Query("username") String username, @Query("page") int page, @Query("pagesize") int size);

    @POST("api/user/unFollow")
    Observable<PayLoad<FollowOperationResp>> unFollow(@Query("SessionId") String sessionId, @Query("username") String username, @Query("fans") String fans);

    @POST("api/user/doFollow")
    Observable<PayLoad<FollowOperationResp>> doFollow(@Query("SessionId") String sessionId, @Query("username") String username, @Query("fans") String fans);

}
