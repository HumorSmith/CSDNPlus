package com.ifreedomer.cplus.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.SearchActivity;
import com.ifreedomer.cplus.activity.markdown.MarkdownEditorActivity;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.entity.NewsTabInfo;
import com.ifreedomer.cplus.fragment.ArticleListFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.FollowOperationResp;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.deployTv)
    TextView deployTv;
    @BindView(R.id.searchEt)
    EditText searchEt;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private MainViewModel mViewModel;
    private List<NewsTabInfo> mTabInfoList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        setupViewPagerAndTab();
        doFollow();
        return view;
    }

    private void doFollow() {
        HttpManager.getInstance().follow("aa375809600").subscribe(new Consumer<PayLoad<FollowOperationResp>>() {
            @Override
            public void accept(PayLoad<FollowOperationResp> followOperationRespPayLoad) throws Exception {
                Log.d(TAG,"success = "+followOperationRespPayLoad.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG,"error = "+throwable.toString());
            }
        });
    }

    private void initView() {
        searchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        deployTv.setOnClickListener(v -> {
            if (getContext() != null && getContext().getApplicationContext() != null) {
                MobclickAgent.onEvent(getContext().getApplicationContext(), "create_article_create", "create_article_create");
            }
            startActivity(new Intent(getActivity(), MarkdownEditorActivity.class));
        });

    }

    private void setupViewPagerAndTab() {
        // init view pager

        String[] tabKeys = getResources().getStringArray(R.array.tab_key);
        String[] tabNames = getResources().getStringArray(R.array.tab_name);

        for (int i = 0; i < getResources().getStringArray(R.array.tab_key).length; i++) {
            NewsTabInfo newsTabInfo = new NewsTabInfo(tabNames[i], tabKeys[i]);
            mTabInfoList.add(newsTabInfo);
            ArticleListFragment fragment = new ArticleListFragment();
            fragment.setTabKey(tabKeys[i]);
            mFragmentList.add(fragment);
        }



        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(2);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab.setupWithViewPager(viewpager);
        for (int i = 0; i < tab.getTabCount(); i++) {
            tab.getTabAt(i).setText(mTabInfoList.get(i).getTabName());
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

}
