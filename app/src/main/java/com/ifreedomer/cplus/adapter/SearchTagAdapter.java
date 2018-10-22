package com.ifreedomer.cplus.adapter;

import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.SearchActivity;
import com.ifreedomer.cplus.activity.SearchDetailActivity;
import com.ifreedomer.cplus.http.protocol.resp.SearchResp;

import java.util.List;

import androidx.annotation.Nullable;

public class SearchTagAdapter extends BaseQuickAdapter<SearchResp.DataBean, BaseViewHolder> {


    public SearchTagAdapter(int layoutResId, @Nullable List<SearchResp.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResp.DataBean item) {
        if (item == null) {
            return;
        }
        helper.setText(R.id.titleTv, item.getName());
        helper.setText(R.id.countTv, item.getQuestion_count() + "");
        helper.setOnClickListener(R.id.rootRelayout, v -> {
            ((SearchActivity) mContext).addHistoryTag(item.getName());
            Intent intent = new Intent(mContext, SearchDetailActivity.class);
            intent.putExtra(SearchDetailActivity.TAG_KEY, item.getName());
            ((SearchActivity) mContext).startActivityForResult(intent,SearchActivity.REQUEST_DETAIL_CODE);
        });
    }


}
