package com.ifreedomer.cplus.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.ForumDetailResp;
import com.ifreedomer.cplus.widget.PicTextItem;

import java.util.List;

import androidx.annotation.Nullable;

public class ForumDetailAdapter extends BaseQuickAdapter<ForumDetailResp, BaseViewHolder> {


    public ForumDetailAdapter(int layoutResId, @Nullable List<ForumDetailResp> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ForumDetailResp item) {
        helper.setText(R.id.nameTv, item.getNickname());
        String floorStr = item.getFloor() + mContext.getString(R.string.floor);
        if (item.getFloor() == 1) {
            floorStr = mContext.getString(R.string.floor_owner);
        }
        helper.setText(R.id.labelTv, floorStr);
        TextView contentTv = helper.getView(R.id.contentTv);
        contentTv.setText(Html.fromHtml(item.getBody()));
//        helper.setText(R.id.contentTv, item.getBody());
        helper.setText(R.id.timeTv, item.getCreated_at());
        PicTextItem picTextItem = helper.getView(R.id.diggPicItem);
        picTextItem.setText(mContext.getString(R.string.digg));
        picTextItem.setIcon(R.drawable.selector_digg);
        Glide.with((View) helper.getView(R.id.avatarIv)).load(item.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.avatarIv));


    }
}
