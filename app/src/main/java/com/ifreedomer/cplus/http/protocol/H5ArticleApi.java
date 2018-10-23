package com.ifreedomer.cplus.http.protocol;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface H5ArticleApi {
    @Multipart
    @POST("https://mp.csdn.net/mdeditor/saveArticle")
    Observable<String> saveArticle(@Body RequestBody body);





}
