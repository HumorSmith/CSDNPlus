package com.ifreedomer.cplus.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.fragment.ForumFragment;
import com.ifreedomer.cplus.ui.main.MainFragment;
import com.ifreedomer.cplus.ui.message.MessageFragment;
import com.ifreedomer.cplus.ui.mine.MineFragment;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setupViewPager();
        setBottomView();

    }

    private void setBottomView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();
            menuItem.setChecked(true);
            switch (itemId) {
                case R.id.homeTab:
                    viewpager.setCurrentItem(0);
                    break;
                case R.id.messageTab:
                    viewpager.setCurrentItem(1);
                    WidgetUtil.showSnackBar(MainActivity.this,getString(R.string.wait_for));
                    break;
                case R.id.forumItem:
                    viewpager.setCurrentItem(2);
                    break;
                case R.id.mineTab:
                    viewpager.setCurrentItem(3);
                    break;


            }
            return false;
        });
    }

    private void setupViewPager() {
        mFragmentList.add(MainFragment.newInstance());
        mFragmentList.add(MessageFragment.newInstance());
        mFragmentList.add(ForumFragment.newInstance());
        mFragmentList.add(MineFragment.newInstance());
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(4);
        viewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0){
            bottomNavigationView.getMenu().findItem(R.id.homeTab).setChecked(true);
        }
        if (position == 1){
            bottomNavigationView.getMenu().findItem(R.id.messageTab).setChecked(true);
        }

        if (position == 2){
            bottomNavigationView.getMenu().findItem(R.id.forumItem).setChecked(true);
        }
        if (position == 3) {
            bottomNavigationView.getMenu().findItem(R.id.mineTab).setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
