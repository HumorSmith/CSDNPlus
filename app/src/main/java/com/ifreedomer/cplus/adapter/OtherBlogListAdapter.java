package com.ifreedomer.cplus.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.BlogContentActivity;
import com.ifreedomer.cplus.http.protocol.resp.MyBlogItemResp;
import com.ifreedomer.cplus.http.protocol.resp.OtherBlogItemResp;

import java.util.List;

import androidx.annotation.Nullable;

public class OtherBlogListAdapter extends BaseQuickAdapter<OtherBlogItemResp, BaseViewHolder> {
    private String userName;

    public OtherBlogListAdapter(int layoutResId, @Nullable List<OtherBlogItemResp> data,String userName) {
        super(layoutResId, data);
        this.userName = userName;
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherBlogItemResp item) {
        helper.setText(R.id.titleTv, item.getTitle());
        helper.setText(R.id.summaryTv, item.getDesc());
        helper.setOnClickListener(R.id.rootRelayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BlogContentActivity.class);
                intent.putExtra(BlogContentActivity.USER_NAME, userName);
                intent.putExtra(BlogContentActivity.ARTICLE_ID, item.getArticleId());
                mContext.startActivity(intent);
//                Intent intent = new Intent(mContext, BlogContentActivity.class);
//                intent.putExtra(BlogContentActivity.USER_NAME_KEY, item.getUserName());
//                intent.putExtra(BlogContentActivity.ARTICLE_ID_KEY, item.getArticleId()+"");
//                intent.putExtra(BlogContentActivity.TITLE_KEY, item.getTitle()+"");

//                mContext.startActivity(intent);
            }
        });
//        helper.setText(R.id.dateTv,new Date(item.getPostTime()).toString().);
    }
}
