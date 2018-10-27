package com.ifreedomer.cplus.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;

import java.util.List;

import androidx.annotation.Nullable;

public class TextListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public TextListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.titleTv, item);
    }
}
