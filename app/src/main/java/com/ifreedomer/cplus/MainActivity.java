package com.ifreedomer.cplus;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.entity.NewsTabInfo;
import com.ifreedomer.cplus.fragment.ArticleListFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.deployTv)
    TextView deployTv;
    @BindView(R.id.searchEt)
    EditText searchEt;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;


    private List<NewsTabInfo> mTabInfoList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setupViewPagerAndTab();
        initBottomBar();


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
        tab.setupWithViewPager(viewpager);
        for (int i = 0; i < tab.getTabCount(); i++) {
            tab.getTabAt(i).setText(mTabInfoList.get(i).getTabName());
        }
    }

    private void initBottomBar() {


    }
}
