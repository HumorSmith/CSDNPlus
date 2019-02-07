package com.ifreedomer.cplus.activity;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.common.PullRefreshActivity;
import com.ifreedomer.cplus.adapter.CollectListAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.CollectListResp;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CollectActivity extends PullRefreshActivity {
    private List<CollectListResp> mCollectItemList = new ArrayList<>();

    @Override
    public void fetchData(int page) {
        setLoading(true);
        HttpManager.getInstance().getCollectList(page, 20).subscribe(collectListRespPayLoad -> {
            setLoading(false);
            if (collectListRespPayLoad.getCode() == PayLoad.SUCCESS) {
                if (mCurPage == 0) {
                    mCollectItemList.clear();
                    mCollectItemList.addAll(collectListRespPayLoad.getData());
                }
            } else {
                mCollectItemList.addAll(collectListRespPayLoad.getData());
                WidgetUtil.showSnackBar(CollectActivity.this, collectListRespPayLoad.getMessage());
            }
            recycleview.getAdapter().notifyDataSetChanged();
        }, throwable -> {
            setLoading(false);
            WidgetUtil.showSnackBar(CollectActivity.this, throwable.getMessage());
        });
    }

    @Override
    public void initTitleAndAdapter() {
        setBackground(getResources().getColor(R.color.searhbarColor));
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.collect));
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new CollectListAdapter(R.layout.item_blog_list, mCollectItemList));
        fetchData(mFirstPage);
    }
}
