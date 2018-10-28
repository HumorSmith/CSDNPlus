package com.ifreedomer.cplus.http.protocol;

import com.ifreedomer.cplus.http.protocol.resp.DeployBlogResp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface H5ArticleApi {
    @POST("https://mp.csdn.net/mdeditor/saveArticle")
    Observable<DeployBlogResp> saveArticle(@Body RequestBody requestBody);






}
