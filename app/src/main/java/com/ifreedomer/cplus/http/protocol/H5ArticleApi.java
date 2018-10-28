package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.DeployBlogResp;

import io.reactivex.Observable;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface H5ArticleApi {
    @Multipart
    @POST("https://mp.csdn.net/mdeditor/saveArticle")
    Observable<DeployBlogResp> saveArticle(@Part("id") String id,
                                           @Part("private") String isPrivate,
                                           @Part("tags") String tags,
                                           @Part("status") String status,
                                           @Part("categories") String categorys,
                                           @Part("channel") String channel,
                                           @Part("type") String type,
                                           @Part("articleedittype") String articleedittype,
                                           @Part("Description") String description,
                                           @Part("title") String title,
                                           @Part("markdowncontent") String markdowncontent,
                                           @Part("content") String content);






}
