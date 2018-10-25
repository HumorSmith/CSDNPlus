package com.ifreedomer.cplus.activity;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.common.PullRefreshActivity;
import com.ifreedomer.cplus.adapter.SearchDetailListAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.SearchDetailResp;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;

public class SearchDetailActivity extends PullRefreshActivity<SearchDetailResp.HitsBean> {
    public static final String CATEGORY_ID_KEY = "category_id";
    public static final String CATEGORY_NAME_KEY = "category_name";
    public static final String TAG_KEY = "tag";

    @Override
    public void fetchData(int page) {
        setLoading(true);

        Observable<PayLoad<SearchDetailResp>> searchTagObserver = HttpManager.getInstance().getSearchDetailListByTag(getIntent().getStringExtra(TAG_KEY));
        searchTagObserver.subscribe(listPayLoad -> {
            setLoading(false);
            if (listPayLoad.getCode() == PayLoad.SUCCESS) {
                if (mCurPage == 0) {
                    mDataList.clear();
                    mDataList.addAll(listPayLoad.getData().getHits());
                }
            } else {
                mDataList.addAll(listPayLoad.getData().getHits());
                WidgetUtil.showSnackBar(SearchDetailActivity.this, listPayLoad.getMessage());
            }
            recycleview.getAdapter().notifyDataSetChanged();
        }, throwable -> WidgetUtil.showSnackBar(SearchDetailActivity.this, throwable.getMessage()));
    }

    @Override
    public void initTitleAndAdapter() {
        String name = getIntent().getStringExtra(TAG_KEY);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, name);
        getRecycleview().setLayoutManager(new LinearLayoutManager(this));
        getRecycleview().setAdapter(new SearchDetailListAdapter(R.layout.item_article_list, mDataList));
        fetchData(mFirstPage);

    }


}
