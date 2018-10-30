package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.ForumDetailResp;
import com.ifreedomer.cplus.http.protocol.resp.ForumHotResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForumApi {
    @GET("api/v2/bbs/new_get_hot_topics")
    Observable<PayLoad<List<ForumHotResp>>> getHotForum(@Query("topic_type") String topicType, @Query("page") int page);

    @GET("api/v2/bbs/get_posts")
    Observable<PayLoad<List<ForumDetailResp>>> getForumDetail(@Query("topic_id") String topicId
            , @Query("page") int page
            , @Query("pagesize") int pageSize
            , @Query("type") String type
    );



}
