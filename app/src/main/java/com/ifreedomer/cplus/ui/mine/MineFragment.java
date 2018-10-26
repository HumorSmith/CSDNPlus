package com.ifreedomer.cplus.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ifreedomer.cplus.activity.BlogCategoryActivity;
import com.ifreedomer.cplus.activity.CollectActivity;
import com.ifreedomer.cplus.activity.FeedbackActivity;
import com.ifreedomer.cplus.activity.FollowActivity;
import com.ifreedomer.cplus.activity.HistoryActivity;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.SettingActivity;
import com.ifreedomer.cplus.entity.UserInfo;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.UserBlogInfoResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.SettingItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class MineFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = MineFragment.class.getSimpleName();
    @BindView(R.id.mineTv)
    TextView mineTv;
    @BindView(R.id.userLinLayout)
    LinearLayout userLinLayout;
    @BindView(R.id.collectNumTv)
    TextView collectNumTv;
    @BindView(R.id.blogNumTv)
    TextView blogNumTv;

    @BindView(R.id.contentRelayout)
    RelativeLayout contentRelayout;
    @BindView(R.id.historyItem)
    SettingItem historyItem;
    @BindView(R.id.feedbackItem)
    SettingItem feedbackItem;
    @BindView(R.id.settingItem)
    SettingItem settingItem;
    @BindView(R.id.avatarIv)
    ImageView avatarIv;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.signTv)
    TextView signTv;

    @BindView(R.id.idolTv)
    TextView idolTv;
    private MineViewModel mViewModel;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        ButterKnife.bind(this, view);
        initItems();
        initHeadView();

        return view;
    }

    private void initHeadView() {
        UserInfo userInfo = GlobalDataManager.getInstance().getUserInfo();
        nameTv.setText(userInfo.getNickName());
        Glide.with((View) avatarIv).load(userInfo.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) avatarIv);
        signTv.setText(userInfo.getSign());
        LogUtil.d(TAG, "userInfo = " + userInfo.toString());
        collectNumTv.setOnClickListener(this);
        blogNumTv.setOnClickListener(this);
        idolTv.setOnClickListener(this);


    }

    private void initItems() {
        historyItem.setText(getString(R.string.history));
        historyItem.setOnClickListener(this);
        settingItem.setText(getString(R.string.setting));
        settingItem.setOnClickListener(this);
        feedbackItem.setText(getString(R.string.feedback));
        feedbackItem.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        Observable<UserBlogInfoResp> userBlogInfoObserver = HttpManager.getInstance().getUserBlogInfo(GlobalDataManager.getInstance().getUserInfo().getUserName());
        Disposable subscribe = userBlogInfoObserver.subscribe(userBlogInfoRespPayLoad -> {
            LogUtil.d(TAG, "user blog info = " + userBlogInfoRespPayLoad.toString());
            String blogNumWrapStr = String.format(getString(R.string.blogNumWrap), userBlogInfoRespPayLoad.getArticle_count().getAll());
            blogNumTv.setText(blogNumWrapStr);
            String idolWrapStr = String.format(getString(R.string.idolWrap), userBlogInfoRespPayLoad.getStatistic().getDiggCount());
            idolTv.setText(idolWrapStr);
        }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));


        Disposable collectNumDisposable = HttpManager.getInstance().getCollectNum().subscribe(collectNumRespPayLoad -> {
            if (collectNumRespPayLoad.getCode() == PayLoad.SUCCESS) {
                String collectBlogNumStr = String.format(getString(R.string.collectWrap), collectNumRespPayLoad.getData().getData().get(0).getNum());
                collectNumTv.setText(collectBlogNumStr);
            } else {
                WidgetUtil.showSnackBar(getActivity(), collectNumRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));



        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.historyItem:
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
            case R.id.feedbackItem:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.collectNumTv:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.blogNumTv:
                Intent intent = new Intent(getActivity(), BlogCategoryActivity.class);
                intent.putExtra(USERNAME_KEY, GlobalDataManager.getInstance().getUserInfo().getUserName());
                startActivity(intent);
                break;
            case R.id.idolTv:
                intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtra(USERNAME_KEY, GlobalDataManager.getInstance().getUserInfo().getUserName());
                startActivity(intent);
                break;
            case R.id.settingItem:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;

        }
    }
}
