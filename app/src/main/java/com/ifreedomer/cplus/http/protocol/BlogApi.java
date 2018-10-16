package com.ifreedomer.cplus.http.protocol;


import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BlogApi {
    @GET("api/blog/categorylist")
    Observable<PayLoad<List<BlogCategoryResp>>> getBlogCategory(@Query("SessionId") String sessionId, @Query("page") int page, @Query("username") String username);

    @GET("api/blog/articlelist")
    Observable<PayLoad<List<BlogResp>>> getBlogListByCategory(
            @Query("SessionId") String sessionId,
            @Query("username") String username,
            @Query("id") int id,
            @Query("page") int page,
            @Query("size") int size);

}
