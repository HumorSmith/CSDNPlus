package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.FollowAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.resp.FollowResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.disposables.Disposable;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class FansFragment extends BasePullRefreshPageFragment<FollowResp> {

    @Override
    protected void initAdapter() {
        mFirstPage = 1;
        this.getRecycleview().setAdapter(new FollowAdapter(FollowAdapter.FANS_TYPE, R.layout.item_follow, mDataList));
        this.getRecycleview().setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchData(mFirstPage);
    }

    @Override
    public void fetchData(int page) {
        refreshLayout.setRefreshing(true);
        Disposable subscribe = HttpManager.getInstance().getFans(getArguments().getString(USERNAME_KEY), getCurPage(), 20).subscribe(listPayLoad -> {
            refreshLayout.setRefreshing(false);
//            LogUtil.d(TAG, "listpayload = " + listPayLoad.toString());
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, throwable -> {
            refreshLayout.setRefreshing(false);
            throwable.printStackTrace();
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
        });
    }
}