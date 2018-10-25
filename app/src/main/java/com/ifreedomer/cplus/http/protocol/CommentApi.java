package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.AddCommentResp;
import com.ifreedomer.cplus.http.protocol.resp.CommentListResp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentApi {

    //    /api/v5/Comment/list?article_id=72834303&page=1&size=30
    @GET("api/v5/Comment/list")
    Observable<PayLoad<CommentListResp>> getCommentList(@Query("article_id") String articleId, @Query("page") int page, @Query("size") int size);


    @FormUrlEncoded
    @POST("api/v5/Comment/add")
    Observable<PayLoad<AddCommentResp>> addComment(@Field("article_id") String articleId, @Field("content") String content
            , @Field("replyId") String replyId, @Field("Content-Type") String contentType, @Field("username") String userName);


}
