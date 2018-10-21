package com.ifreedomer.cplus.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.ifreedomer.cplus.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogCategoryActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_category);
        ButterKnife.bind(this);

    }
}
