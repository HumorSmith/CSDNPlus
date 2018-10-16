package com.ifreedomer.cplus.http.center;

import android.util.Log;

import com.google.gson.Gson;
import com.ifreedomer.cplus.http.protocol.ArticleApi;
import com.ifreedomer.cplus.http.protocol.BlogApi;
import com.ifreedomer.cplus.http.protocol.FollowApi;
import com.ifreedomer.cplus.http.protocol.LoginApi;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.ArticleListResp;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;
import com.ifreedomer.cplus.http.protocol.resp.BlogUserProfileResp;
import com.ifreedomer.cplus.http.protocol.resp.FollowOperationResp;
import com.ifreedomer.cplus.http.protocol.resp.FollowResp;
import com.ifreedomer.cplus.http.protocol.resp.HistoryResp;
import com.ifreedomer.cplus.http.protocol.resp.UserInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.SecurityUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpManager {
    private Gson mGson = new Gson();

    public Gson getGson() {
        return mGson;
    }

    public void setGson(Gson gson) {
        mGson = gson;
    }

    public static final String TAG = HttpManager.class.getSimpleName();
    private static final String BASE_URL = "https://ms.csdn.net";


    private HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
    private Retrofit retrofit;


    private Retrofit mStringRetrofit;


    private OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(mLogging)
            .build();


    private HttpManager() {
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        mStringRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(mClient)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(mClient)
                .build();
    }

    ;
    private static HttpManager sInstance = new HttpManager();

    public static HttpManager getInstance() {
        return sInstance;
    }


    public Observable<BlogUserProfileResp> getUserInfo(final String userName) {
        Observable<String> userInfoObserva = mStringRetrofit.create(LoginApi.class).getBlogUserinfo(userName);
        Observable<BlogUserProfileResp> userInfoRespObservable = userInfoObserva.map(new Function<String, BlogUserProfileResp>() {
            @Override
            public BlogUserProfileResp apply(String s) throws Exception {
                JSONObject jsonObject = new JSONObject(s);
                String string = jsonObject.getJSONObject("data").getJSONObject("data").getString(userName);
                BlogUserProfileResp userInfoResp = mGson.fromJson(string, BlogUserProfileResp.class);
                return userInfoResp;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return userInfoRespObservable;
    }

    public Observable<PayLoad<UserInfoResp>> login(String account, String password) {
        String decryptPwd = SecurityUtil.DESEncrypt(password);
        LoginApi loginApi = retrofit.create(LoginApi.class);
        Log.d(TAG, "account = " + account + "  password = " + password + "   decryptPwd =" + decryptPwd);
        Observable<PayLoad<UserInfoResp>> loginObservable = loginApi.login(account, decryptPwd, "LH5Dv4pUXww%3D").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return loginObservable;
    }


    public Observable<PayLoad<V2ProfileResp>> getV2Profile(String account) {
        String sessionId = GlobalDataManager.getInstance().getSessionId();
        LoginApi loginApi = retrofit.create(LoginApi.class);
        Observable<PayLoad<V2ProfileResp>> userProfileObservable = loginApi.getUserProfile(account, sessionId);
        return userProfileObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<PayLoad<List<BlogCategoryResp>>> getBlogCatergory(String username) {
        BlogApi blogApi = retrofit.create(BlogApi.class);
        Observable<PayLoad<List<BlogCategoryResp>>> blogCategoryObservable = blogApi.getBlogCategory(GlobalDataManager.getInstance().getSessionId(), 1, username)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return blogCategoryObservable;
    }

    public Observable<PayLoad<List<BlogResp>>> getBlogListByCategory(int categoryId, String username, int page, int size) {
        Observable<PayLoad<List<BlogResp>>> blogListByCategory = retrofit.create(BlogApi.class).getBlogListByCategory(
                GlobalDataManager.getInstance().getSessionId(), username, categoryId, page, size);
        return blogListByCategory.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<PayLoad<ArticleListResp<ArticleResp>>> getArticleListByCategory(String category, String type, long offset, int size) {
        Observable<PayLoad<ArticleListResp<ArticleResp>>> articleListByCategoryObservable = retrofit.create(ArticleApi.class).getNews(
                GlobalDataManager.getInstance().getSessionId(), category, "867686022248916", type, offset, size);
        articleListByCategoryObservable = articleListByCategoryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return articleListByCategoryObservable;
    }


    public Observable<PayLoad<List<HistoryResp>>> getHistory() {
        Observable<PayLoad<List<HistoryResp>>> historyListByCategoryObservable = retrofit.create(ArticleApi.class).getHistory(
                GlobalDataManager.getInstance().getSessionId(), GlobalDataManager.getInstance().getUserInfo().getUserName());
        historyListByCategoryObservable = historyListByCategoryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return historyListByCategoryObservable;
    }

    public Observable<PayLoad<List<FollowResp>>> getIdol(int page, int size) {
        Observable<PayLoad<List<FollowResp>>> idolObservable = retrofit.create(FollowApi.class).getIdol(GlobalDataManager.getInstance().getSessionId(), GlobalDataManager.getInstance().getUserInfo().getUserName(), page, size);
        idolObservable = idolObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return idolObservable;
    }

    public Observable<PayLoad<List<FollowResp>>> getFans(int page, int size) {
        Observable<PayLoad<List<FollowResp>>> idolObservable = retrofit.create(FollowApi.class).getFans(GlobalDataManager.getInstance().getSessionId(), GlobalDataManager.getInstance().getUserInfo().getUserName(), page
                , size);
        idolObservable = idolObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return idolObservable;
    }

    public Observable<PayLoad<FollowOperationResp>> unfollow(String username) {
        Observable<PayLoad<FollowOperationResp>> unFollowObservable = retrofit.create(FollowApi.class).unFollow(GlobalDataManager.getInstance().getSessionId(), username, GlobalDataManager.getInstance().getUserInfo().getUserName());
        unFollowObservable = unFollowObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return unFollowObservable;
    }


    public Observable<PayLoad<FollowOperationResp>> follow(String username) {
        Observable<PayLoad<FollowOperationResp>> unFollowObservable = retrofit.create(FollowApi.class).doFollow(GlobalDataManager.getInstance().getSessionId(), username, GlobalDataManager.getInstance().getUserInfo().getUserName());
        unFollowObservable = unFollowObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return unFollowObservable;
    }
}
