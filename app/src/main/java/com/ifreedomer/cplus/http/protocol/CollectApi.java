package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.CollectListResp;
import com.ifreedomer.cplus.http.protocol.resp.CollectNumResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CollectApi {
    @GET("api/favorite/get_domain_favorite_num")
    Observable<PayLoad<CollectNumResp>> getFavoriteNum();


    @GET("api/favorite/get_list_favorite")
    Observable<PayLoad<CollectListResp>> getCollectList(@Query("tag_name") String tagName, @Query("pageno") int pageNum, @Query("pagesize") int pageSize);

}
