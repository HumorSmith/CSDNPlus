package com.ifreedomer.cplus.ui.mine;

import android.content.Intent;
import android.didikee.donate.AlipayDonate;
import android.net.Uri;
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
import com.google.android.material.snackbar.Snackbar;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.BlogCategoryActivity;
import com.ifreedomer.cplus.activity.CollectActivity;
import com.ifreedomer.cplus.activity.FeedbackActivity;
import com.ifreedomer.cplus.activity.FollowActivity;
import com.ifreedomer.cplus.activity.HistoryActivity;
import com.ifreedomer.cplus.activity.SettingActivity;
import com.ifreedomer.cplus.entity.UserInfo;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.UserBlogInfoResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.SettingItem;
import com.umeng.analytics.MobclickAgent;

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
    @BindView(R.id.donateItem)
    SettingItem donateItem;
    @BindView(R.id.joinGroupItem)
    SettingItem joinGroupItem;
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
        if (userInfo == null) {
            if (getActivity() != null) {
                WidgetUtil.showSnackBar(getActivity(), getString(R.string.get_userinfo_error));
            }
            return;
        }
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
        donateItem.setText(getString(R.string.donate));
        donateItem.setOnClickListener(this);
        joinGroupItem.setText(getString(R.string.joingroup));
        joinGroupItem.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        Observable<PayLoad<UserBlogInfoResp>> userBlogInfoObserver = HttpManager.getInstance().getUserBlogInfo(GlobalDataManager.getInstance().getUserInfo().getUserName());
        Disposable subscribe = userBlogInfoObserver.subscribe(payLoad -> {
            UserBlogInfoResp userBlogInfoRespPayLoad = payLoad.getData();
            LogUtil.d(TAG, "user blog info = " + userBlogInfoRespPayLoad.toString());
            String blogNumWrapStr = String.format(getString(R.string.blogNumWrap), userBlogInfoRespPayLoad.getAll().toString());
            blogNumTv.setText(blogNumWrapStr);
            String idolWrapStr = String.format(getString(R.string.idolWrap), userBlogInfoRespPayLoad.getPrivateX());
            idolTv.setText(idolWrapStr);


            String collectBlogNumStr = String.format(getString(R.string.collectWrap), userBlogInfoRespPayLoad.getCollectNum());
            collectNumTv.setText(collectBlogNumStr);

        }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));


        Disposable collectNumDisposable = HttpManager.getInstance().getCollectNum().subscribe(collectNumRespPayLoad -> {
            if (collectNumRespPayLoad.getCode() == PayLoad.SUCCESS) {

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
                MobclickAgent.onEvent(getContext(), "my_history", "my_history");
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
            case R.id.feedbackItem:
                MobclickAgent.onEvent(getContext(), "my_feedback", "my_feedback");
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.collectNumTv:
                MobclickAgent.onEvent(getContext(), "my_collect", "my_collect");
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.blogNumTv:
                MobclickAgent.onEvent(getContext(), "my_blog", "my_blog");
                Intent intent = new Intent(getActivity(), BlogCategoryActivity.class);
                intent.putExtra(USERNAME_KEY, GlobalDataManager.getInstance().getUserInfo().getUserName());
                startActivity(intent);
                break;
            case R.id.idolTv:
                MobclickAgent.onEvent(getContext(), "my_follow", "my_follow");
                intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtra(USERNAME_KEY, GlobalDataManager.getInstance().getUserInfo().getUserName());
                startActivity(intent);
                break;
            case R.id.settingItem:
                MobclickAgent.onEvent(getContext(), "my_setting", "my_setting");
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.donateItem:
                MobclickAgent.onEvent(getContext(), "my_donate", "my_donate");
                donateAlipay("FKX04593OORFSACYVCL0BA");
                break;
            case R.id.joinGroupItem:
                joinQQGroup("nJ3SA6MJDYvjsE31CAx_Vc0y2ikx2vAo");
                break;

        }
    }


    /**
     * 支付宝支付
     *
     * @param payCode 收款码后面的字符串；例如：收款二维码里面的字符串为 https://qr.alipay.com/stx00187oxldjvyo3ofaw60 ，则
     *                payCode = stx00187oxldjvyo3ofaw60
     *                注：不区分大小写
     */
    private void donateAlipay(String payCode) {
        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(getActivity());
        if (hasInstalledAlipayClient) {
            AlipayDonate.startAlipayClient(getActivity(), payCode);
        }
    }


    /****************
     *
     * 发起添加群流程。群号：卸载大师(587521420) 的 key 为： oFQHttYFV9YyAkf-PzlqDsBSYreEpSWo
     * 调用 joinQQGroup(oFQHttYFV9YyAkf-PzlqDsBSYreEpSWo) 即可发起手Q客户端申请加群 卸载大师(587521420)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }
}
