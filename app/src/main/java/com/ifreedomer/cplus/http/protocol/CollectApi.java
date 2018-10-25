package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.AddCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.CheckCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.CollectListResp;
import com.ifreedomer.cplus.http.protocol.resp.CollectNumResp;
import com.ifreedomer.cplus.http.protocol.resp.DeleteCollectResp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CollectApi {
    @GET("api/favorite/get_domain_favorite_num")
    Observable<PayLoad<CollectNumResp>> getFavoriteNum();


    @GET("api/favorite/get_list_favorite")
    Observable<PayLoad<CollectListResp>> getCollectList(@Query("tag_name") String tagName, @Query("pageno") int pageNum, @Query("pagesize") int pageSize);


    @FormUrlEncoded
    @POST("api/favorite/add_favorite")
    Observable<PayLoad<AddCollectResp>> addCollect(@Field("title") String title, @Field("url") String url, @Field("username") String username);


    @GET("api/favorite/do_delete_favorite")
    Observable<PayLoad<DeleteCollectResp>> deleteCollect(@Query("id") String id);


    @GET("api/v5/check/app_check_favorite")
    Observable<PayLoad<CheckCollectResp>> checkCollect(@Query("username") String username, @Query("url")String url);


}
