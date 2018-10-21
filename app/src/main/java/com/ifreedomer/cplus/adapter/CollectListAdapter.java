package com.ifreedomer.cplus.adapter;

import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.activity.BlogContentActivity;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.entity.BlogContentInfo;
import com.ifreedomer.cplus.http.protocol.resp.CollectListResp;
import com.ifreedomer.cplus.util.DateUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class CollectListAdapter extends BaseQuickAdapter<CollectListResp.CollectItem, BaseViewHolder> {
    public CollectListAdapter(int layoutResId, @Nullable List<CollectListResp.CollectItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectListResp.CollectItem item) {
        helper.setText(R.id.titleTv, item.getTitle());
        helper.setText(R.id.summaryTv, item.getDescription());
        try {
            helper.setText(R.id.dateTv, DateUtil.convertToMonth(mContext, item.getPostTime()));
        } catch (Exception e) {
            helper.setVisible(R.id.dateTv, false);
            e.printStackTrace();
        }
        helper.setOnClickListener(R.id.rootRelayout, v -> {
            Intent intent = new Intent(mContext, BlogContentActivity.class);
            intent.putExtra(BlogContentActivity.DATA, BlogContentInfo.convert(item));
            mContext.startActivity(intent);
        });
    }
}
