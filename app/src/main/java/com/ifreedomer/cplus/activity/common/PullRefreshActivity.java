package com.ifreedomer.cplus.activity.common;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class PullRefreshActivity<T> extends AppCompatActivity {
    public static final String TAG = PullRefreshActivity.class.getSimpleName();
    @BindView(R.id.recycleview)
    public RecyclerView recycleview;
    @BindView(R.id.refreshLayout)
    public SwipeRefreshLayout refreshLayout;
    protected int mCurPage;
    @BindView(R.id.titleTv)
    public TextView titleTv;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    public List<T> mDataList = new ArrayList<>();

    public abstract void fetchData(int page);

    public abstract void initTitleAndAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initTitleAndAdapter();
        initListener();
    }


    public int getLayoutId() {
        return R.layout.activity_pull_refresh;
    }

    private void initListener() {
        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    Log.d(TAG, "page = " + mCurPage);
                    fetchData(mCurPage);
                }
            }
        });

        refreshLayout.setOnRefreshListener(() -> {
            mCurPage = 0;
            fetchData(mCurPage);
        });
    }


    public void refreshList(int code, String message, List<T> list) {
        refreshLayout.setRefreshing(false);
//            LogUtil.d(TAG, "listpayload = " + listPayLoad.toString());
        if (code == PayLoad.SUCCESS) {

            if (getCurPage() == 0) {
                mDataList.clear();
            }
            if (list.size() == 0) {
                WidgetUtil.showSnackBar(this, getString(R.string.no_more));
                return;
            }
            mDataList.addAll(list);
            recycleview.getAdapter().notifyDataSetChanged();
        } else {
            WidgetUtil.showSnackBar(this, message);
        }
    }



    public int getCurPage() {
        return mCurPage;
    }

    public RecyclerView getRecycleview() {
        return recycleview;
    }

    public void setBackground(int color) {
        recycleview.setBackgroundColor(color);
    }

    public void setLoading(boolean isLoading) {
        refreshLayout.setRefreshing(isLoading);
    }

}
