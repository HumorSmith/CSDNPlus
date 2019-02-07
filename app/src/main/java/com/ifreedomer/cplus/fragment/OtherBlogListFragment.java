package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.MyBlogListAdapter;
import com.ifreedomer.cplus.adapter.OtherBlogListAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.MyBlogItemResp;
import com.ifreedomer.cplus.http.protocol.resp.OtherBlogItemResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class OtherBlogListFragment extends BasePullRefreshPageFragment<OtherBlogItemResp> {

    private String mUserName;

    @Override
    protected void initAdapter() {


        mUserName = getArguments().getString(USERNAME_KEY);

        getRecycleview().setAdapter(new OtherBlogListAdapter(R.layout.item_blog_list, mDataList,mUserName));
        getRecycleview().setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchData(mFirstPage);
    }

    @Override
    public void fetchData(int page) {

        refreshLayout.setRefreshing(true);
        Observable<PayLoad<List<OtherBlogItemResp>>> payLoadObservable = HttpManager.getInstance().getOtherBlogInfo(getArguments().getString(USERNAME_KEY));
        payLoadObservable.subscribe(listPayLoad -> {
            refreshLayout.setRefreshing(false);
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                refreshLayout.setRefreshing(false);
                WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
            }
        });
    }


}
