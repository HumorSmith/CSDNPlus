package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.HotForumAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.ForumHotResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;


public class ForumContentFragment extends BasePullRefreshPageFragment<ForumHotResp> {
    public static final int PAGE_SIZE = 20;
    public static final String TAG = ForumContentFragment.class.getSimpleName();
    private String tabKey;

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    @Override
    protected void initAdapter() {
        mRecycleview.setAdapter(new HotForumAdapter(R.layout.item_rv_forum, mDataList));
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        setBackground(getResources().getColor(R.color.searhbarColor));
    }


    @Override
    public void fetchData(int page) {
        Observable<PayLoad<List<ForumHotResp>>> forumObserver = HttpManager.getInstance().getForum(tabKey, page);
        forumObserver.subscribe(listPayLoad -> {
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, throwable -> {
            refreshLayout.setRefreshing(false);
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
        });
    }
}
