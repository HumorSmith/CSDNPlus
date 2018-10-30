package com.ifreedomer.cplus.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.common.PullRefreshActivity;
import com.ifreedomer.cplus.adapter.ForumDetailAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.ForumDetailResp;
import com.ifreedomer.cplus.util.ShareUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ForumDetailActivity extends PullRefreshActivity<ForumDetailResp> {

    public static final String TAG = ForumDetailActivity.class.getSimpleName();
    public static final String TOPIC_ID_KEY = "topic_id";
    private String mTopicId;


    @Override
    public int getLayoutId() {
        return R.layout.activity_forum_detail;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.blog_content_menu);
        toolbar.getMenu().findItem(R.id.collectItem).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ShareUtil.shareString(ForumDetailActivity.this, "");
                return false;
            }
        });

        toolbar.getMenu().findItem(R.id.collectItem).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void fetchData(int page) {
        Observable<PayLoad<List<ForumDetailResp>>> forumDetailObserver = HttpManager.getInstance().getForumDetail(mTopicId, page, 20);
        Disposable subscribe = forumDetailObserver.subscribe(listPayLoad -> refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData()), throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage()));
    }

    @Override
    public void initTitleAndAdapter() {
        ToolbarUtil.setTitleBarWithBack(this,toolbar,"");
        mTopicId = getIntent().getIntExtra(TOPIC_ID_KEY, -1)+"";
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new ForumDetailAdapter(R.layout.item_rv_forumdetail, mDataList));
        fetchData(mFirstPage);
    }
}
