package com.ifreedomer.cplus.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.BlogContentActivity;
import com.ifreedomer.cplus.entity.BlogContentInfo;
import com.ifreedomer.cplus.http.protocol.resp.SearchDetailResp;
import com.ifreedomer.cplus.widget.PicTextItem;

import java.util.List;

import androidx.annotation.Nullable;


public class SearchDetailListAdapter extends BaseQuickAdapter<SearchDetailResp.HitsBean, BaseViewHolder> {
    public SearchDetailListAdapter(int layoutResId, @Nullable List<SearchDetailResp.HitsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchDetailResp.HitsBean item) {
        helper.setText(R.id.titleTv, item.get_source().getTitle());
        helper.setText(R.id.summaryTv, item.get_source().getDescription());
        PicTextItem lookItem = helper.getView(R.id.lookPicItem);
        lookItem.setText(item.get_source().getView_count() + "");
        lookItem.setIcon(R.mipmap.ic_view);


        PicTextItem commentItem = helper.getView(R.id.commentPicItem);
        commentItem.setText(item.get_source().getViewcount() + "");
        commentItem.setIcon(R.mipmap.ic_comment);
        commentItem.setVisibility(View.GONE);


        PicTextItem userItem = helper.getView(R.id.userPicItem);
        userItem.setText(item.get_source().getNickname());
        userItem.setImageUrl(item.get_source().getAvatar());
        helper.setOnClickListener(R.id.rootRelayout, v -> {
            Intent intent = new Intent(mContext, BlogContentActivity.class);
            intent.putExtra(BlogContentActivity.USER_NAME, item.get_source().getUser_name());
            intent.putExtra(BlogContentActivity.ARTICLE_ID, item.get_source().getId() + "");
            mContext.startActivity(intent);
        });
    }
}
