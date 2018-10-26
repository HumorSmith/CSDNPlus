package com.ifreedomer.cplus.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.BlogCategoryActivity;
import com.ifreedomer.cplus.activity.CollectActivity;
import com.ifreedomer.cplus.activity.FeedbackActivity;
import com.ifreedomer.cplus.activity.FollowActivity;
import com.ifreedomer.cplus.activity.HistoryActivity;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.resp.UserBlogInfoResp;
import com.ifreedomer.cplus.ui.mine.MineViewModel;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.SettingItem;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class OtherUserActivity extends Activity implements View.OnClickListener {
    public static final String TAG = OtherUserActivity.class.getSimpleName();
    @BindView(R.id.avatarIv)
    ImageView avatarIv;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.signTv)
    TextView signTv;
    @BindView(R.id.userLinLayout)
    LinearLayout userLinLayout;
    @BindView(R.id.followTv)
    TextView followTv;
    @BindView(R.id.blogNumTv)
    TextView blogNumTv;
    @BindView(R.id.rankNumTv)
    TextView rankNumTv;
    @BindView(R.id.contentRelayout)
    RelativeLayout contentRelayout;
    @BindView(R.id.historyItem)
    SettingItem historyItem;
    @BindView(R.id.feedbackItem)
    SettingItem feedbackItem;
    @BindView(R.id.settingItem)
    SettingItem settingItem;

    private MineViewModel mViewModel;
    public static final String USERNAME_KEY = "username";
    public static final String NICKNAME_KEY = "nickname";
    public static final String AVATAR_KEY = "avatar";
    private String mUserName;
    private String mNickName;
    private String mAvatar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_user_fragment);
        ButterKnife.bind(this);
        initItems();
        initHeadView();
        initData();
    }

    private void initHeadView() {
        mUserName = (String) getIntent().getStringExtra(USERNAME_KEY);
        mNickName = getIntent().getStringExtra(NICKNAME_KEY);
        mAvatar = getIntent().getStringExtra(AVATAR_KEY);
        nameTv.setText(mNickName);
        Glide.with((View) avatarIv).load(mAvatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) avatarIv);
//        signTv.setText(mUserInfo.getSign());
//        LogUtil.d(TAG, "userInfo = " + mUserInfo.toString());
        rankNumTv.setOnClickListener(this);
        blogNumTv.setOnClickListener(this);
        followTv.setOnClickListener(this);


    }

    private void initItems() {
        historyItem.setText(getString(R.string.history));
        historyItem.setOnClickListener(this);
        settingItem.setText(getString(R.string.setting));
        feedbackItem.setText(getString(R.string.feedback));
        feedbackItem.setOnClickListener(this);
    }

    public void initData() {
        Observable<UserBlogInfoResp> userBlogInfoObserver = HttpManager.getInstance().getUserBlogInfo(mUserName);
        Disposable subscribe = userBlogInfoObserver.subscribe(userBlogInfoRespPayLoad -> {
            LogUtil.d(TAG, "user blog info = " + userBlogInfoRespPayLoad.toString());
            String blogNumWrapStr = String.format(getString(R.string.blogNumWrap), userBlogInfoRespPayLoad.getArticle_count().getAll());
            blogNumTv.setText(blogNumWrapStr);
            String idolWrapStr = String.format(getString(R.string.idolWrap), userBlogInfoRespPayLoad.getStatistic().getDiggCount());
            followTv.setText(idolWrapStr);

            String rank = userBlogInfoRespPayLoad.getStatistic().getRank();
            String rankWrapStr = String.format(getString(R.string.rankWrap), rank);
            rankNumTv.setText(rankWrapStr);
        }, throwable -> WidgetUtil.showSnackBar(this, throwable.getMessage()));


//        HttpManager.getInstance().getIdol(mUserName, 0, 10);

        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.historyItem:
                Intent intent = new Intent(this, HistoryActivity.class);
                intent.putExtra(USERNAME_KEY, mUserName);
                startActivity(intent);
                break;
            case R.id.feedbackItem:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.collectNumTv:
                startActivity(new Intent(this, CollectActivity.class));
                break;
            case R.id.blogNumTv:
                intent = new Intent(this, BlogCategoryActivity.class);
                intent.putExtra(USERNAME_KEY,mUserName);
                startActivity(intent);
                break;
            case R.id.followTv:
                intent = new Intent(this, FollowActivity.class);
                intent.putExtra(USERNAME_KEY,mUserName);
                startActivity(intent);
                break;

        }
    }
}
