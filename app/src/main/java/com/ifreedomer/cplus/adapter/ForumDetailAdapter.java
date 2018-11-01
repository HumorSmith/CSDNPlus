package com.ifreedomer.cplus.adapter;

import android.app.Activity;
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
import com.ifreedomer.cplus.activity.ForumDetailActivity;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.resp.ForumDetailResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.PicTextItem;

import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.disposables.Disposable;

public class ForumDetailAdapter extends BaseQuickAdapter<ForumDetailResp, BaseViewHolder> {
    private String mTopicId;
    private ForumDetailActivity.ReplyClickListener mReplyOnclickListener;
    private ForumDetailActivity.ReportClickListener reportClickListener;

    public ForumDetailAdapter(String topicId, int layoutResId, @Nullable List<ForumDetailResp> data) {
        super(layoutResId, data);
        mTopicId = topicId;
    }


    public void setReplyOnclickListener(ForumDetailActivity.ReplyClickListener onclickListener) {
        this.mReplyOnclickListener = onclickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ForumDetailResp item) {
        String userName = GlobalDataManager.getInstance().getUserInfo().getUserName();
        helper.setText(R.id.nameTv, item.getNickname());
        String floorStr = item.getFloor() + mContext.getString(R.string.floor);
        if (item.getFloor() == 1) {
            floorStr = mContext.getString(R.string.floor_owner);
        }
        helper.setText(R.id.labelTv, floorStr);
        helper.getView(R.id.replyTv).setOnClickListener(v -> {
            if (mReplyOnclickListener != null) {
                mReplyOnclickListener.onClick(item);
            }
        });
        helper.getView(R.id.reportTv).setOnClickListener(v -> report(item, userName));
        TextView contentTv = helper.getView(R.id.contentTv);
        contentTv.setText(Html.fromHtml(item.getBody()));
//        helper.setText(R.id.contentTv, item.getBody());
        helper.setText(R.id.timeTv, item.getCreated_at());
        PicTextItem picTextItem = helper.getView(R.id.diggPicItem);

        picTextItem.setOnClickListener(v -> digg(item, userName));

        picTextItem.setText(mContext.getString(R.string.digg));
        picTextItem.setIcon(R.drawable.selector_digg);
        picTextItem.setSelect(item.isIs_digged());
        Glide.with((View) helper.getView(R.id.avatarIv)).load(item.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.avatarIv));
    }

    private void report(ForumDetailResp item, String userName) {
        if (reportClickListener!=null){
            reportClickListener.onClick(item);
        }
//        Observable<PayLoad<Boolean>> payLoadObservable = HttpManager.getInstance().forumReport(0, mTopicId, item.getPost_id() + "", userName);
//        Disposable subscribe = payLoadObservable.subscribe(booleanPayLoad -> WidgetUtil.showSnackBar((Activity) mContext, booleanPayLoad.getMessage()), throwable -> WidgetUtil.showSnackBar((Activity) mContext, throwable.getMessage()));
    }

    private void digg(ForumDetailResp item, String userName) {
        Disposable subscribe = HttpManager.getInstance().forumDigg(userName, mTopicId, item.getPost_id() + "").subscribe(booleanPayLoad -> {
            if (booleanPayLoad.getData()) {
                item.setIs_digged(!item.isIs_digged());
                notifyDataSetChanged();
            } else {
                WidgetUtil.showSnackBar((Activity) mContext, booleanPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar((Activity) mContext, throwable.getMessage()));
    }


    public void setReportClickListener(ForumDetailActivity.ReportClickListener reportClickListener) {
        this.reportClickListener = reportClickListener;
    }

    public ForumDetailActivity.ReportClickListener getReportClickListener() {
        return reportClickListener;
    }
}
