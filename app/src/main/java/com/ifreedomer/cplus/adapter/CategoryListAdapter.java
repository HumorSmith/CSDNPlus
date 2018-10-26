package com.ifreedomer.cplus.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.CategoryDetailActivity;
import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;

import java.util.List;

import androidx.annotation.Nullable;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class CategoryListAdapter extends BaseQuickAdapter<BlogCategoryResp, BaseViewHolder> {
    private String mUserName;

    public CategoryListAdapter(String userName, int layoutResId, @Nullable List<BlogCategoryResp> data) {
        super(layoutResId, data);
        this.mUserName = userName;
    }

    @Override
    protected void convert(BaseViewHolder helper, BlogCategoryResp item) {
        helper.setText(R.id.titleTv, item.getName());
        helper.setText(R.id.countTv, item.getArticle_count() + "");
        helper.setOnClickListener(R.id.rootRelayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra(CategoryDetailActivity.CATEGORY_ID_KEY, item.getId());
                intent.putExtra(CategoryDetailActivity.CATEGORY_NAME_KEY, item.getName());
                intent.putExtra(USERNAME_KEY, mUserName);
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
