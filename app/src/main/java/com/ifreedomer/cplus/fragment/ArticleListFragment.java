package com.ifreedomer.cplus.fragment;

import android.util.Log;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.ArticleListAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.ArticleListResp;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;


public class ArticleListFragment extends ArticlePullrefreshFragment {
    public static final int PAGE_SIZE = 20;
    public static final String TAG = ArticleListFragment.class.getSimpleName();
    private String tabKey;

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    @Override
    protected void initAdapter() {
        mRecycleview.setAdapter(new ArticleListAdapter(R.layout.item_article_list, mDataList));
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        setBackground(getResources().getColor(R.color.searhbarColor));
    }

    @Override
    public void fetchData(String type, long offset) {
        Log.d(TAG, "type = " + type + "   offset = " + offset + "   page = " + getCurPage());
//        type = "new";
//        offset = 0;
        refreshLayout.setRefreshing(true);
        Observable<PayLoad<ArticleListResp<ArticleResp>>> newsObservable = HttpManager.getInstance().getArticleListByCategory(tabKey, type, offset, PAGE_SIZE);
        newsObservable.subscribe(listPayLoad -> {
            refreshLayout.setRefreshing(false);
//            LogUtil.d(TAG, "listpayload = " + listPayLoad.toString());
            if (listPayLoad.getCode() == 200) {

                if (getCurPage() == 0) {
                    mDataList.clear();
                }
                if (listPayLoad.getData().getArticles().size() == 0) {
                    WidgetUtil.showSnackBar(getActivity(), getString(R.string.no_more));
                    return;
                }
                mDataList.addAll(listPayLoad.getData().getArticles());
                mRecycleview.getAdapter().notifyDataSetChanged();
            } else {
                WidgetUtil.showSnackBar(getActivity(), listPayLoad.getMessage());
            }
        }, throwable -> {
            refreshLayout.setRefreshing(false);
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
        });

    }


}
