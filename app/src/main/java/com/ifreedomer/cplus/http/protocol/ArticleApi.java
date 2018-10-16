package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.ArticleListResp;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.http.protocol.resp.HistoryResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {
    @GET("api/v5/index/articles")
    Observable<PayLoad<ArticleListResp<ArticleResp>>> getNews(@Query("SessionId") String sessionId
            , @Query("category") String category
            , @Query("cookieid") String cookieId
            , @Query("type") String type
            , @Query("shown_offset") long offset
            , @Query("size") int size);


    @GET("api/history/list")
    Observable<PayLoad<List<HistoryResp>>> getHistory(@Query("SessionId") String sessionId
            , @Query("username") String username);




}
