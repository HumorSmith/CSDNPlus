package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.CategoryListAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class BlogCategoryFragment extends BasePullRefreshPageFragment<BlogCategoryResp> {

    private String mUserName;


    @Override
    protected void initAdapter() {
        mUserName = getArguments().getString(USERNAME_KEY);
        getRecycleview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getRecycleview().setAdapter(new CategoryListAdapter(mUserName,R.layout.item_blog_category, mDataList));
    }

    @Override
    public void fetchData(int page) {

        refreshLayout.setRefreshing(true);

        Observable<PayLoad<List<BlogCategoryResp>>> blogCatergoryObserver = HttpManager.getInstance().getBlogCatergory(mUserName);
        blogCatergoryObserver.subscribe(listPayLoad -> {
            refreshLayout.setRefreshing(false);
            mDataList.clear();
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, throwable ->{
            refreshLayout.setRefreshing(false);
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
        });
    }
}
