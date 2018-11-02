package com.ifreedomer.cplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.ForumDeployActivity;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.entity.NewsTabInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.writeIv)
    ImageView writeIv;
    @BindView(R.id.searchIv)
    ImageView searchIv;

    private List<NewsTabInfo> mTabInfoList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, null);
        ButterKnife.bind(this, view);
        setupViewPagerAndTab();
        initView();
        return view;
    }

    private void initView() {
        writeIv.setOnClickListener(v -> startActivity(new Intent(getActivity(), ForumDeployActivity.class)));
    }


    public static ForumFragment newInstance() {
        return new ForumFragment();
    }

    private void setupViewPagerAndTab() {
        // init view pager

        String[] tabKeys = getResources().getStringArray(R.array.forum_key);
        String[] tabValues = getResources().getStringArray(R.array.forum_value);

        for (int i = 0; i < tabKeys.length; i++) {
            NewsTabInfo newsTabInfo = new NewsTabInfo(tabKeys[i], tabValues[i]);
            mTabInfoList.add(newsTabInfo);
            ForumContentFragment fragment = new ForumContentFragment();
            fragment.setTabKey(tabValues[i]);
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

}
