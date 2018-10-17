package com.ifreedomer.cplus;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ifreedomer.cplus.adapter.ViewPagerFragmentAdapter;
import com.ifreedomer.cplus.ui.main.MainFragment;
import com.ifreedomer.cplus.ui.message.MessageFragment;
import com.ifreedomer.cplus.ui.mine.MineFragment;

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
                    break;
                case R.id.mineTab:
                    viewpager.setCurrentItem(2);
                    break;
            }
            return false;
        });
    }

    private void setupViewPager() {
        mFragmentList.add(MainFragment.newInstance());
        mFragmentList.add(MessageFragment.newInstance());
        mFragmentList.add(MineFragment.newInstance());
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(3);
        viewpager.addOnPageChangeListener(null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationView.getMenu().findItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
