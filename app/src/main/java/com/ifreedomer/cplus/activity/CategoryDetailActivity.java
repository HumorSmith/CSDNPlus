package com.ifreedomer.cplus.activity;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.common.PullRefreshActivity;
import com.ifreedomer.cplus.adapter.BlogListAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.BlogResp;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.ifreedomer.cplus.fragment.OtherUserActivity.USERNAME_KEY;

public class CategoryDetailActivity extends PullRefreshActivity<BlogResp> {
    public static final String CATEGORY_ID_KEY = "category_id";
    public static final String CATEGORY_NAME_KEY = "category_name";

    @Override
    public void fetchData(int page) {
        setLoading(true);
        Observable<PayLoad<List<BlogResp>>> blogListByCategoryObservable = HttpManager.getInstance().getBlogListByCategory(getIntent().getIntExtra(CATEGORY_ID_KEY, 0),
                getIntent().getStringExtra(USERNAME_KEY), page, 20);
        blogListByCategoryObservable.subscribe(listPayLoad -> {
            setLoading(false);
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());
        }, throwable -> {
            setLoading(false);
            WidgetUtil.showSnackBar(CategoryDetailActivity.this, throwable.getMessage());

        });
    }

    @Override
    public void initTitleAndAdapter() {
        String name = getIntent().getStringExtra(CATEGORY_NAME_KEY);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, name);
        getRecycleview().setLayoutManager(new LinearLayoutManager(this));
        getRecycleview().setAdapter(new BlogListAdapter(R.layout.item_blog_list, mDataList));
        fetchData(mFirstPage);

    }


}
