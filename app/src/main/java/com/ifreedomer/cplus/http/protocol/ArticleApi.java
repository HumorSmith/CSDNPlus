package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.ArticleDetailInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.ArticleListResp;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.http.protocol.resp.HistoryResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {
    @GET("/cms-app/v1/home_page/may_login/list_recomment_articles")
    Observable<PayLoad<ArticleListResp<ArticleResp>>> getNews(
             @Query("category") String category
            , @Query("cookieid") String cookieId
            , @Query("type") String type
            , @Query("shown_offset") long offset
            , @Query("size") int size);


    @GET("/cms-app/v1/me/login/get_viewhistory")
    Observable<PayLoad<List<HistoryResp>>> getHistory();


    @GET("api/v5/ArticleInfo/getArticleInfo")
    Observable<PayLoad<ArticleDetailInfoResp>> getArticleInfo(@Query("userName") String username
            , @Query("article_id") String articleId);


}
