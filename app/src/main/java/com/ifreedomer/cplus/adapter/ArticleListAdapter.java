package com.ifreedomer.cplus.adapter;

import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.BlogContentActivity;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.widget.PicTextItem;

import java.util.List;

import androidx.annotation.Nullable;


public class ArticleListAdapter extends BaseQuickAdapter<ArticleResp, BaseViewHolder> {
    public ArticleListAdapter(int layoutResId, @Nullable List<ArticleResp> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleResp item) {
        helper.setText(R.id.titleTv, item.getTitle());
        helper.setText(R.id.summaryTv, item.getSummary());
        PicTextItem lookItem = helper.getView(R.id.lookPicItem);
        lookItem.setText(item.getViews());
        lookItem.setIcon(R.mipmap.ic_view);


        PicTextItem commentItem = helper.getView(R.id.commentPicItem);
        commentItem.setText(item.getComments());
        commentItem.setIcon(R.mipmap.ic_comment);


        PicTextItem userItem = helper.getView(R.id.userPicItem);
        userItem.setText(item.getNickname());
        userItem.setImageUrl(item.getAvatar());
        helper.setOnClickListener(R.id.rootRelayout, v -> {
            Intent intent = new Intent(mContext, BlogContentActivity.class);
            intent.putExtra(BlogContentActivity.USER_NAME_KEY, item.getUser_name());
            intent.putExtra(BlogContentActivity.NICK_NAME_KEY, item.getNickname());
            intent.putExtra(BlogContentActivity.ARTICLE_ID_KEY, item.getId() + "");
            intent.putExtra(BlogContentActivity.TITLE_KEY, item.getTitle() + "");
            intent.putExtra(BlogContentActivity.AVATAR_KEY, item.getAvatar() + "");
            intent.putExtra(BlogContentActivity.DATE_KEY, item.getShown_time() + "");

            mContext.startActivity(intent);
        });
    }
}
