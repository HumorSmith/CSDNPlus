package com.ifreedomer.cplus.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.fragment.FansFragment;
import com.ifreedomer.cplus.fragment.IdolFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowActivity extends AppCompatActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> mFollowFragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        ButterKnife.bind(this);
        titleList.add(getString(R.string.my_idol));
        titleList.add(getString(R.string.my_fans));
        setupViewPagerAndTab();
    }


    private void setupViewPagerAndTab() {
        Fragment idolFragment = new IdolFragment();
        Fragment fansFragment = new FansFragment();
        mFollowFragmentList.add(idolFragment);
        mFollowFragmentList.add(fansFragment);
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFollowFragmentList);
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
        tablayout.setupWithViewPager(viewpager);
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            tablayout.getTabAt(i).setText(titleList.get(i));
        }


    }
}
