package com.ifreedomer.cplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.BlogCategoryActivity;
import com.ifreedomer.cplus.activity.FollowActivity;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.UserBlogInfoResp;
import com.ifreedomer.cplus.ui.mine.MineViewModel;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class OtherUserActivity extends AppCompatActivity implements View.OnClickListener {
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
    @BindView(R.id.doFollowTv)
    TextView doFollowTv;
    @BindView(R.id.fragment)
    FrameLayout fragment;


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
        mUserName =  getIntent().getStringExtra(USERNAME_KEY);
        mNickName = getIntent().getStringExtra(NICKNAME_KEY);
        mAvatar = getIntent().getStringExtra(AVATAR_KEY);
        nameTv.setText(mNickName);
        Glide.with((View) avatarIv).load(mAvatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) avatarIv);
//        signTv.setText(mUserInfo.getSign());
//        LogUtil.d(TAG, "userInfo = " + mUserInfo.toString());
        rankNumTv.setOnClickListener(this);
        blogNumTv.setOnClickListener(this);
        followTv.setOnClickListener(this);
        doFollowTv.setOnClickListener(this);




    }

    private void initItems() {

    }

    public void initData() {
        Observable<PayLoad<UserBlogInfoResp>> userBlogInfoObserver = HttpManager.getInstance().getUserBlogInfo(mUserName);
        Disposable subscribe = userBlogInfoObserver.subscribe(resp -> {
            UserBlogInfoResp userBlogInfoRespPayLoad = resp.getData();
            LogUtil.d(TAG, "user blog info = " + userBlogInfoRespPayLoad.toString());
            String blogNumWrapStr = String.format(getString(R.string.blogNumWrap), userBlogInfoRespPayLoad.getAll()+"");
            blogNumTv.setText(blogNumWrapStr);
            String idolWrapStr = String.format(getString(R.string.idolWrap), userBlogInfoRespPayLoad.getPrivateX());
            followTv.setText(idolWrapStr);


            String rank = userBlogInfoRespPayLoad.getRank();
            String rankWrapStr = String.format(getString(R.string.rankWrap), rank);
            rankNumTv.setText(rankWrapStr);
        }, throwable -> WidgetUtil.showSnackBar(this, throwable.getMessage()));


        HttpManager.getInstance().getV2Profile(mUserName).subscribe(v2ProfileRespPayLoad -> {
            if (v2ProfileRespPayLoad.getCode() == 200) {
                signTv.setText(v2ProfileRespPayLoad.getData().getSelfdesc());
            } else {
                WidgetUtil.showSnackBar(OtherUserActivity.this, v2ProfileRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(OtherUserActivity.this, throwable.getMessage()));


        OtherBlogListFragment categoryFragment = new OtherBlogListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME_KEY, mUserName);
        categoryFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, categoryFragment)
                .commitNow();


        // TODO: Use the ViewModel
        Disposable subscribe1 = HttpManager.getInstance().getRelation(mUserName).subscribe(getRelationRespPayLoad -> {
            if (getRelationRespPayLoad.getCode() == PayLoad.SUCCESS) {
                if (getRelationRespPayLoad.getData().getStatus() == 1) {
                    doFollowTv.setText(getString(R.string.followed));
                } else {
                    doFollowTv.setText(getString(R.string.follow));
                }
            } else {
                WidgetUtil.showSnackBar(OtherUserActivity.this, getRelationRespPayLoad.getMessage());
            }
        }, throwable -> {

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.blogNumTv:
                Intent intent = new Intent(this, BlogCategoryActivity.class);
                intent.putExtra(USERNAME_KEY, mUserName);
                startActivity(intent);
                break;
            case R.id.followTv:
                intent = new Intent(this, FollowActivity.class);
                intent.putExtra(USERNAME_KEY, mUserName);
                startActivity(intent);
                break;
            case R.id.doFollowTv:
                if (doFollowTv.getText().equals(getString(R.string.follow))) {
                    HttpManager.getInstance().follow(mUserName).subscribe(followOperationRespPayLoad -> {
                        if (followOperationRespPayLoad.getCode() == PayLoad.SUCCESS) {
                            doFollowTv.setText(getString(R.string.followed));
                        } else {
                            WidgetUtil.showSnackBar(OtherUserActivity.this, followOperationRespPayLoad.getMessage());
                        }
                    }, throwable -> WidgetUtil.showSnackBar(OtherUserActivity.this, throwable.getMessage()));
                } else {
                    HttpManager.getInstance().unfollow(mUserName).subscribe(followOperationRespPayLoad -> {
                        if (followOperationRespPayLoad.getCode() == PayLoad.SUCCESS) {
                            doFollowTv.setText(getString(R.string.follow));
                        } else {
                            WidgetUtil.showSnackBar(OtherUserActivity.this, followOperationRespPayLoad.getMessage());
                        }
                    }, throwable -> WidgetUtil.showSnackBar(OtherUserActivity.this, throwable.getMessage()));
                }



        }
    }
}
