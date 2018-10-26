package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.MyBlogListAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.MyBlogItemResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class MyBlogListFragment extends BasePullRefreshPageFragment<MyBlogItemResp> {

    @Override
    protected void initAdapter() {
        getRecycleview().setAdapter(new MyBlogListAdapter(R.layout.item_blog_list, mDataList));
        getRecycleview().setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchData(mFirstPage);
    }

    @Override
    public void fetchData(int page) {
        Observable<PayLoad<List<MyBlogItemResp>>> payLoadObservable = HttpManager.getInstance().getMyBlogList(getArguments().getString(USERNAME_KEY), getCurPage(), 20);
        payLoadObservable.subscribe(listPayLoad -> {
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
            }
        });
    }


}
