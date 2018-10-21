package com.ifreedomer.cplus.activity;

import android.os.Bundle;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.HistoryAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.HistoryResp;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.history_view));

        fetchData();
    }

    public void fetchData() {
        Observable<PayLoad<List<HistoryResp>>> historyObservable = HttpManager.getInstance().getHistory();
        Disposable subscribe = historyObservable.subscribe(listPayLoad -> {
            if (listPayLoad.getCode() == PayLoad.SUCCESS) {
                recycleview.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                recycleview.setAdapter(new HistoryAdapter(R.layout.item_blog_list, listPayLoad.getData()));
            } else {
                WidgetUtil.showSnackBar(HistoryActivity.this, listPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(HistoryActivity.this, throwable.getMessage()));


    }
}
