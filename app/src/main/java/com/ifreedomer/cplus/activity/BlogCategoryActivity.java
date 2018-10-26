package com.ifreedomer.cplus.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.fragment.BlogCategoryFragment;
import com.ifreedomer.cplus.fragment.MyBlogListFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class BlogCategoryActivity extends AppCompatActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        ButterKnife.bind(this);
        mTitleList.add(getString(R.string.blog));
        mTitleList.add(getString(R.string.category));
        setupViewPagerAndTab();
    }


    private void setupViewPagerAndTab() {
        String userName = getIntent().getStringExtra(USERNAME_KEY);
        Fragment myBlogListFragment = new MyBlogListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME_KEY, userName);
        myBlogListFragment.setArguments(bundle);
        Fragment blogCategoryFragment = new BlogCategoryFragment();
        blogCategoryFragment.setArguments(bundle);
        mFragmentList.add(myBlogListFragment);
        mFragmentList.add(blogCategoryFragment);
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFragmentList);
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
            tablayout.getTabAt(i).setText(mTitleList.get(i));
        }


    }
}
