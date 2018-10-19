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
import com.ifreedomer.cplus.FeedbackActivity;
import com.ifreedomer.cplus.HistoryActivity;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.entity.UserInfo;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.CountResp;
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

public class MineFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = MineFragment.class.getSimpleName();
    @BindView(R.id.mineTv)
    TextView mineTv;
    @BindView(R.id.userLinLayout)
    LinearLayout userLinLayout;
    @BindView(R.id.collectNumTv)
    TextView collectNumTv;
    @BindView(R.id.blog_num_tv)
    TextView blogNumTv;
    @BindView(R.id._num_tv)
    TextView NumTv;
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


    }

    private void initItems() {
        historyItem.setText(getString(R.string.history));
        historyItem.setOnClickListener(this);
        settingItem.setText(getString(R.string.setting));
        feedbackItem.setText(getString(R.string.feedback));
        feedbackItem.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);

        Observable<PayLoad<CountResp>> countProfileObserver = HttpManager.getInstance().getCountProfile(GlobalDataManager.getInstance().getUserInfo().getUserName());
        countProfileObserver.subscribe(countRespPayLoad -> {
            if (countRespPayLoad.getCode() == PayLoad.SUCCESS) {

            } else {
                WidgetUtil.showSnackBar(getActivity(), countRespPayLoad.getMessage());
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
        }
    }
}
