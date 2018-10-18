package com.ifreedomer.cplus.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.widget.SettingItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends Fragment {

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
        return view;
    }

    private void initItems() {
        historyItem.setText(getString(R.string.history));
        settingItem.setText(getString(R.string.setting));
        feedbackItem.setText(getString(R.string.feedback));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        // TODO: Use the ViewModel
    }

}
