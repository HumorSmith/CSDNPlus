package com.ifreedomer.cplus.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.TextListAdapter;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleCategorySelectActivity extends AppCompatActivity {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private List<String> mDataList = new ArrayList<>();
    public static final String SELECT_KEY = "select";
    private String mSelectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_category_select);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.article_category));
        toolbar.setNavigationOnClickListener(v -> {
            getBackIntent();
            ArticleCategorySelectActivity.this.finish();
        });
        mDataList.add(getString(R.string.origin));
        mDataList.add(getString(R.string.reprint));
        mDataList.add(getString(R.string.translate));

        recycleview.setLayoutManager(new LinearLayoutManager(this));
        TextListAdapter textListAdapter = new TextListAdapter(R.layout.item_text_select, mDataList);
        recycleview.setAdapter(textListAdapter);
        textListAdapter.setOnItemClickListener((adapter, view, position) -> {
            mSelectText = mDataList.get(position);
            WidgetUtil.showSnackBar(ArticleCategorySelectActivity.this,mSelectText);
        });
    }

    @Override
    public void onBackPressed() {
        getBackIntent();
        super.onBackPressed();
    }

    private void getBackIntent() {
        Intent intent = new Intent();
        intent.putExtra(SELECT_KEY, mSelectText);
        setResult(Activity.RESULT_OK, intent);
    }
}
