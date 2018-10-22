package com.ifreedomer.cplus.fragment.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BasePullRefreshPageFragment<T> extends BasePageFragment {
    private int mCurPage;
    protected List<T> mDataList = new ArrayList<>();
    @BindView(R.id.recycleview)
    protected RecyclerView mRecycleview;
    @BindView(R.id.refreshLayout)
    protected SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private static final String TAG = BasePullRefreshPageFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pullrefresh, null);
        unbinder = ButterKnife.bind(this, view);
        initListener();
        initAdapter();
        return view;
    }


    protected abstract void initAdapter();

    private void initListener() {
        mRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                    // 和上面同理
                    mCurPage++;
                    Log.d(TAG,"page = "+mCurPage);
                    fetchData(mCurPage);
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurPage = 0;
                fetchData(mCurPage);
            }
        });
    }

    public void setBackground(int color) {
        mRecycleview.setBackgroundColor(color);
    }


    public RecyclerView getRecycleview() {
        return mRecycleview;
    }

    public int getCurPage() {
        return mCurPage;
    }


    public void refreshList(int code, String message, List<T> list) {
        refreshLayout.setRefreshing(false);
//            LogUtil.d(TAG, "listpayload = " + listPayLoad.toString());
        if (code == PayLoad.SUCCESS) {

            if (getCurPage() == 0) {
                mDataList.clear();
            }
            if (list.size() == 0) {
                WidgetUtil.showSnackBar(getActivity(), getString(R.string.no_more));
                return;
            }
            mDataList.addAll(list);
            mRecycleview.getAdapter().notifyDataSetChanged();
        } else {
            WidgetUtil.showSnackBar(getActivity(), message);
        }
    }


}
