package com.ifreedomer.cplus.fragment;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.FollowAdapter;
import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.FollowResp;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class IdolFragment extends BasePullRefreshPageFragment<FollowResp> {

    @Override
    protected void initAdapter() {
        this.getRecycleview().setAdapter(new FollowAdapter(FollowAdapter.IDOL_TYPE, R.layout.item_follow, mDataList));
        this.getRecycleview().setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchData(0);
    }

    @Override
    public void fetchData(int page) {
        Disposable subscribe = HttpManager.getInstance().getIdol(getCurPage(), 20).subscribe(listPayLoad -> {
            refreshLayout.setRefreshing(false);
//            LogUtil.d(TAG, "listpayload = " + listPayLoad.toString());
            if (listPayLoad.getCode() == PayLoad.SUCCESS) {

                if (getCurPage() == 0) {
                    mDataList.clear();
                }
                if (listPayLoad.getData().size() == 0) {
                    WidgetUtil.showSnackBar(getActivity(), getString(R.string.no_more));
                    return;
                }
                mDataList.addAll(listPayLoad.getData());
                mRecycleview.getAdapter().notifyDataSetChanged();
            } else {
                WidgetUtil.showSnackBar(getActivity(), listPayLoad.getMessage());
            }
        }, (Consumer<Throwable>) throwable -> {
            throwable.printStackTrace();
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
        });
    }
}