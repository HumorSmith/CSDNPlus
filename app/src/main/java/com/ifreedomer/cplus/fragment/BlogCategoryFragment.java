package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.CategoryListAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;

public class BlogCategoryFragment extends BasePullRefreshPageFragment<BlogCategoryResp> {
    @Override
    protected void initAdapter() {
        getRecycleview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getRecycleview().setAdapter(new CategoryListAdapter(R.layout.item_blog_category, mDataList));
    }

    @Override
    public void fetchData(int page) {
        Observable<PayLoad<List<BlogCategoryResp>>> blogCatergoryObserver = HttpManager.getInstance().getBlogCatergory(GlobalDataManager.getInstance().getUserInfo().getUserName());
        blogCatergoryObserver.subscribe(listPayLoad -> {
            mDataList.clear();
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));
    }
}
