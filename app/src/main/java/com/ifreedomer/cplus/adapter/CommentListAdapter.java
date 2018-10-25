package com.ifreedomer.cplus.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.CommentListResp;

import java.util.List;

import androidx.annotation.Nullable;

public class CommentListAdapter extends BaseQuickAdapter<CommentListResp.ListBean, BaseViewHolder> {


    public CommentListAdapter(int layoutResId, @Nullable List<CommentListResp.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListResp.ListBean item) {
        String nameStr = item.getInfo().getNickName();

        helper.setText(R.id.nameTv, nameStr);
        Glide.with((View) helper.getView(R.id.avatarIv)).load(item.getInfo().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.avatarIv));
        helper.setText(R.id.commentTv, item.getInfo().getContent());
        helper.setText(R.id.timeTv, item.getInfo().getPostTime());
    }
}
