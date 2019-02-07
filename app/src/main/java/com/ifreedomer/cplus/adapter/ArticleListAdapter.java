package com.ifreedomer.cplus.adapter;

import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.BlogContentActivity;
import com.ifreedomer.cplus.entity.BlogContentInfo;
import com.ifreedomer.cplus.fragment.OtherUserActivity;
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
        userItem.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, OtherUserActivity.class);
            intent.putExtra(OtherUserActivity.USERNAME_KEY, item.getUser_name());
            intent.putExtra(OtherUserActivity.NICKNAME_KEY, item.getNickname());
            intent.putExtra(OtherUserActivity.AVATAR_KEY, item.getAvatar());
            mContext.startActivity(intent);
        });


        userItem.setText(item.getNickname());
        userItem.setImageUrl(item.getAvatar());
        helper.setOnClickListener(R.id.rootRelayout, v -> {
            Intent intent = new Intent(mContext, BlogContentActivity.class);
            intent.putExtra(BlogContentActivity.USER_NAME, item.getUser_name());
            intent.putExtra(BlogContentActivity.ARTICLE_ID, item.getId() + "");
            mContext.startActivity(intent);
        });
    }
}
