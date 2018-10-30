package com.ifreedomer.cplus.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.ForumHotResp;
import com.ifreedomer.cplus.widget.PicTextItem;

import java.util.List;

import androidx.annotation.Nullable;


public class HotForumAdapter extends BaseQuickAdapter<ForumHotResp, BaseViewHolder> {
    public HotForumAdapter(int layoutResId, @Nullable List<ForumHotResp> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ForumHotResp item) {
        helper.setText(R.id.nameTv, item.getNickname());
        helper.setText(R.id.contentTv, item.getTitle());
        PicTextItem commentPicItem = helper.getView(R.id.commentPicItem);
        commentPicItem.setIcon(R.drawable.selector_comment);
        commentPicItem.setText(item.getPost_count() + "");
        PicTextItem digPicItem = helper.getView(R.id.digPicItem);
        digPicItem.setIcon(R.drawable.selector_digg);
        digPicItem.setText(item.getDigg_count() + "");

    }
}
