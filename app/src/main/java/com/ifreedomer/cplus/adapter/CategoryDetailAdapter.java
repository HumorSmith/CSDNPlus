package com.ifreedomer.cplus.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;

import java.util.List;

import androidx.annotation.Nullable;

public class CategoryDetailAdapter extends BaseQuickAdapter<BlogResp, BaseViewHolder> {


    public CategoryDetailAdapter(int layoutResId, @Nullable List<BlogResp> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlogResp item) {
        helper.setText(R.id.titleTv, item.getTitle());
        helper.setText(R.id.summaryTv, item.getDescription());
        helper.setOnClickListener(R.id.rootRelayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BlogContentInfo.convert(item);
            }
        });
    }
}
