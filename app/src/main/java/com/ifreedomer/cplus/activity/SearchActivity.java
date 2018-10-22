package com.ifreedomer.cplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.SearchTagAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.SearchResp;
import com.ifreedomer.cplus.util.SPUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.cancelTv)
    TextView cancelTv;
    @BindView(R.id.searchEt)
    EditText searchEt;
    @BindView(R.id.searchRelayout)
    RelativeLayout searchRelayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.historyLinlayout)
    LinearLayout historyLinlayout;
    @BindView(R.id.recommandTagGroup)
    TagGroup recommandTagGroup;
    @BindView(R.id.historyTagGroup)
    TagGroup historyTagGroup;
    @BindView(R.id.clearTv)
    TextView clearTv;
    private List<SearchResp.DataBean> mDataList = new ArrayList<>();
    private static final String HISTORY_KEY = "history";
    public static final int REQUEST_DETAIL_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        cancelTv.setOnClickListener(this);
        searchEt.addTextChangedListener(mTextWatcher);
        recyclerview.setAdapter(new SearchTagAdapter(R.layout.item_search, mDataList));
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        clearTv.setOnClickListener(this);
        recommandTagGroup.setTags(new String[]{"Android", "IOS", getString(R.string.ai), getString(R.string.link_quote), getString(R.string.alorthim), "TensorFlow", getString(R.string.face_recognize)});
        recommandTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                addHistoryTag(tag);
                Intent intent = new Intent(SearchActivity.this, SearchDetailActivity.class);
                intent.putExtra(SearchDetailActivity.TAG_KEY, tag);
                startActivityForResult(intent, REQUEST_DETAIL_CODE);
            }
        });

        historyTagGroup.setTags(loadHistoryTag());

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s)) {
                recyclerview.setVisibility(View.GONE);
                historyLinlayout.setVisibility(View.VISIBLE);
            } else {
                recyclerview.setVisibility(View.VISIBLE);
                historyLinlayout.setVisibility(View.GONE);
                searchTag(s.toString());

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void searchTag(String tag) {
        HttpManager.getInstance().search(tag).subscribe(new Consumer<PayLoad<SearchResp>>() {
            @Override
            public void accept(PayLoad<SearchResp> searchRespPayLoad) throws Exception {
                mDataList.clear();
                mDataList.addAll(searchRespPayLoad.getData().getData());
                recyclerview.getAdapter().notifyDataSetChanged();
            }
        }, throwable -> WidgetUtil.showSnackBar(SearchActivity.this, throwable.getMessage()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelTv:
                this.finish();
                break;
            case R.id.clearTv:
                SPUtil.put(this, HISTORY_KEY, "");
                historyTagGroup.setTags(new String[]{});
                break;
        }
    }


    public String[] loadHistoryTag() {
        String history = (String) SPUtil.get(this, HISTORY_KEY, "");
        if (TextUtils.isEmpty(history)) {
            return new String[]{};
        } else {
            String[] split = history.split("\\|");
            return split;
        }
    }

    public void addHistoryTag(String tag) {
        String history = (String) SPUtil.get(this, HISTORY_KEY, "");
        history = tag + "|" + history;
        SPUtil.put(this, HISTORY_KEY, history);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == REQUEST_DETAIL_CODE) {
            historyTagGroup.setTags(loadHistoryTag());
        }
    }
}
