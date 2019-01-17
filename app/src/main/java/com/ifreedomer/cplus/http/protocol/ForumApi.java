package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.ForumDetailResp;
import com.ifreedomer.cplus.http.protocol.resp.ForumHotResp;
import com.ifreedomer.cplus.http.protocol.resp.ForumPostResp;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ForumApi {
    @GET("cms-app/v1/bbs/new_hot_topics")
    Observable<PayLoad<List<ForumHotResp>>> getHotForum(@Query("topicType") String topicType, @Query("page") int page);

    @GET("api/v2/bbs/get_posts")
    Observable<PayLoad<List<ForumDetailResp>>> getForumDetail(@Query("topic_id") String topicId
            , @Query("page") int page
            , @Query("pagesize") int pageSize
            , @Query("type") String type
    );


    @GET("api/v2/bbs/digg")
    Observable<PayLoad<Boolean>> digg(@Query("username") String username, @Query("topic_id") String topicId, @Query("post_id") String postId);


    @POST("api/v2/bbs/create_post")
    Observable<PayLoad<ForumPostResp>> forumPost(@Body RequestBody body);


    @GET("api/v2/bbs/report")
    Observable<PayLoad<Boolean>> forumReport(@Query("reason_type") int reasonType, @Query("username") String username, @Query("topic_id") String topicId, @Query("post_id") String postId);

    @POST("api/v3/bbs/create_topic")
    Observable<String> forumCreateTopic(@Body RequestBody formBody);

}
