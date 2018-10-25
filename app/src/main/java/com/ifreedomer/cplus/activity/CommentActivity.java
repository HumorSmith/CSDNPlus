package com.ifreedomer.cplus.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.common.PullRefreshActivity;
import com.ifreedomer.cplus.adapter.CommentListAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.AddCommentResp;
import com.ifreedomer.cplus.http.protocol.resp.CommentListResp;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import io.reactivex.Observable;

public class CommentActivity extends PullRefreshActivity<CommentListResp.ListBean> {
    public static final String ARTICLE_ID = "articleId";
    public static final String COUNT = "count";
    @BindView(R.id.sendTv)
    TextView sendTv;
    @BindView(R.id.contentEt)
    EditText contentEt;
    private String mArticleId;


    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void fetchData(int page) {
        setLoading(true);
        Observable<PayLoad<CommentListResp>> commentListObserver = HttpManager.getInstance().getCommentList(mArticleId, page, 20);
        commentListObserver.subscribe(commentListRespPayLoad -> {
            setLoading(false);
            refreshList(commentListRespPayLoad.getCode(), commentListRespPayLoad.getMessage(), commentListRespPayLoad.getData().getList());
        }, throwable -> WidgetUtil.showSnackBar(CommentActivity.this, throwable.getMessage()));
    }

    @Override
    public void initTitleAndAdapter() {
        mArticleId = getIntent().getStringExtra(ARTICLE_ID);
        int count = getIntent().getIntExtra(COUNT, 0);
        String titleStr = String.format(getString(R.string.all_comment_wrap), count);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, titleStr);

        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new CommentListAdapter(R.layout.item_rv_comment, mDataList));

        sendTv.setOnClickListener(v -> {
            Observable<PayLoad<AddCommentResp>> addCommentObserver = HttpManager.getInstance().addComment(mArticleId, contentEt.getText().toString());
            addCommentObserver.subscribe(addCommentRespPayLoad -> {
                if (addCommentRespPayLoad.getCode() == PayLoad.SUCCESS) {
                    contentEt.setText("");
                    WidgetUtil.showSnackBar(CommentActivity.this, getString(R.string.comment_success));
                    fetchData(0);
                } else {
                    WidgetUtil.showSnackBar(CommentActivity.this, addCommentRespPayLoad.getMessage());
                }
            }, throwable -> WidgetUtil.showSnackBar(CommentActivity.this, throwable.getMessage()));
        });

        fetchData(0);
    }


}
