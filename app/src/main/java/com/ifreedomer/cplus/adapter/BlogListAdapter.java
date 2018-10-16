package com.ifreedomer.cplus.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;

import java.util.List;

import androidx.annotation.Nullable;

public class BlogListAdapter extends BaseQuickAdapter<BlogResp, BaseViewHolder> {


    public BlogListAdapter(int layoutResId, @Nullable List<BlogResp> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlogResp item) {
        helper.setText(R.id.titleTv, item.getTitle());
        helper.setText(R.id.summaryTv, item.getDescription());
        helper.setOnClickListener(R.id.rootRelayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
