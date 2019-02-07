package com.ifreedomer.cplus.http.protocol;


import com.ifreedomer.cplus.http.protocol.resp.ApproveResp;
import com.ifreedomer.cplus.http.protocol.resp.ArticleDetailInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;
import com.ifreedomer.cplus.http.protocol.resp.DiggResp;
import com.ifreedomer.cplus.http.protocol.resp.MyBlogItemResp;
import com.ifreedomer.cplus.http.protocol.resp.SearchDetailResp;
import com.ifreedomer.cplus.http.protocol.resp.SearchResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BlogApi {
    @GET("cms-app/v1/me_blog/my_blog_type")
    Observable<PayLoad<List<BlogCategoryResp>>> getBlogCategory( @Query("page") int page, @Query("userName") String username);

    @GET("api/blog/articlelist")
    Observable<PayLoad<List<BlogResp>>> getBlogListByCategory(
            @Query("username") String username,
            @Query("id") int id,
            @Query("page") int page,
            @Query("size") int size);

    @GET("api/v5/ArticleDiggApp/digg")
    Observable<PayLoad<ApproveResp>> approve(
            @Query("username") String username,
            @Query("article_id") String articleId);


    @GET("api/ask/search_tags")
    Observable<PayLoad<SearchResp>> search(
            @Query("size") int size,
            @Query("page") int page,
            @Query("word") String word);


    @GET("api/v3/search/elastic")
    Observable<PayLoad<SearchDetailResp>> getDetailListByTag(
            @Query("size") int size,
            @Query("page") int page,
            @Query("block") String block,
            @Query("keywords") String words);


    @GET("api/blog/user_blog_list")
    Observable<PayLoad<ArticleDetailInfoResp>> getArticleInfo(@Query("userName") String username, @Query("page") int page, @Query("pagesize") int pagesize);


    @GET("/cms-app/v1/me_blog/login/my_blog")
    Observable<PayLoad<List<MyBlogItemResp>>> getMyBlogList(@Query("userName") String username, @Query("page") int page, @Query("size") int pagesize);

    @POST("api/v5/ArticleDiggApp/digg")
    Observable<PayLoad<DiggResp>> digg(@Query("userName") String username, @Query("article_id") String articleId);



//    /api/v5/Comment/list?article_id=72834303&page=1&size=30



}
