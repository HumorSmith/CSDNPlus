package com.ifreedomer.cplus.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;
import com.ifreedomer.cplus.widget.PicTextItem;

import java.util.List;

import androidx.annotation.Nullable;


public class MessageAdapter extends BaseQuickAdapter<ArticleResp, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<ArticleResp> data) {
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


//        helper.setText(R.id.zanAndCommentTv, String.format(mContext.getString(R.string.zan_comment_wrap), item.getViews(), item.getComments()));
//        try {
//            helper.setText(R.id.dateTv, DateUtil.timeStamp2DateStringWithMonth(mContext, Long.parseLong(item.getShown_time()) * 1000));
//        } catch (Exception e) {
//            helper.setVisible(R.id.dateTv, false);
//            e.printStackTrace();
//        }
//        Glide.with((View) helper.getView(R.id.avatarIv)).load(item.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.avatarIv));
//        helper.setOnClickListener(R.id.rootRelayout, v -> {
//            Intent intent = new Intent(mContext, BlogContentActivity.class);
//            intent.putExtra(BlogContentActivity.USER_NAME_KEY, item.getUser_name());
//            intent.putExtra(BlogContentActivity.ARTICLE_ID_KEY, item.getId() + "");
//            intent.putExtra(BlogContentActivity.TITLE_KEY, item.getTitle() + "");

//            mContext.startActivity(intent);
//        });
    }
}
