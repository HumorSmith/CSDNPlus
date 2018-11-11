package com.ifreedomer.cplus.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.common.PullRefreshActivity;
import com.ifreedomer.cplus.adapter.ForumDetailAdapter;
import com.ifreedomer.cplus.fragment.BottomSheetListFragment;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.AddCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.CheckCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.DeleteCollectResp;
import com.ifreedomer.cplus.http.protocol.resp.ForumDetailResp;
import com.ifreedomer.cplus.http.protocol.resp.ForumPostResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.ShareUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ForumDetailActivity extends PullRefreshActivity<ForumDetailResp> {

    public static final String TAG = ForumDetailActivity.class.getSimpleName();
    public static final String TOPIC_ID_KEY = "topic_id";
    public static final String TITLE_KEY = "title";
    @BindView(R.id.sendTv)
    TextView sendTv;
    @BindView(R.id.contentEt)
    EditText contentEt;
    @BindView(R.id.contentTv)
    TextView contentTv;
    private String mTopicId;
    private String mFavoriteId;
    private String mUrl;
    private String mTitle;
    private BottomSheetListFragment mBottomSheetDialogFragment;
    private ForumDetailResp mReplyForumDetailResp;


    @Override
    public int getLayoutId() {
        return R.layout.activity_forum_detail;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.blog_content_menu);
        toolbar.getMenu().findItem(R.id.shareItem).setOnMenuItemClickListener(item -> {
            ShareUtil.shareString(ForumDetailActivity.this, mUrl);
            return false;
        });

        toolbar.getMenu().findItem(R.id.collectItem).setOnMenuItemClickListener(item -> {
            if (item.isChecked()) {
                deleteCollect();
            } else {
                addCollect();
            }
            return false;
        });
        checkFavorite();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void fetchData(int page) {
        setLoading(true);
        Observable<PayLoad<List<ForumDetailResp>>> forumDetailObserver = HttpManager.getInstance().getForumDetail(mTopicId, page, 20);
        Disposable subscribe = forumDetailObserver.subscribe(listPayLoad -> {
            setLoading(false);
            refreshList(listPayLoad.getCode(), listPayLoad.getMessage(), listPayLoad.getData());

        }, throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage()));
    }

    @Override
    public void initTitleAndAdapter() {
        String userName = GlobalDataManager.getInstance().getUserInfo().getUserName();
        ToolbarUtil.setTitleBarWithBack(this, toolbar, "");
        mTopicId = getIntent().getIntExtra(TOPIC_ID_KEY, -1) + "";
        mTitle = getIntent().getStringExtra(TITLE_KEY);
        contentTv.setText(mTitle);
        mUrl = "http://bbs.csdn.net/topics/" + mTopicId;
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        ForumDetailAdapter forumDetailAdapter = new ForumDetailAdapter(mTopicId, R.layout.item_rv_forumdetail, mDataList);
        forumDetailAdapter.setReplyOnclickListener(forumDetailResp ->
        {
            mReplyForumDetailResp = forumDetailResp;
            contentEt.setHint(getString(R.string.reply) + forumDetailResp.getNickname() + ":");
        });
        forumDetailAdapter.setReportClickListener((forumDetailResp) -> {
            mBottomSheetDialogFragment = new BottomSheetListFragment();
            String[] stringArray = getResources().getStringArray(R.array.forum_reports);
            List<String> reports = Arrays.asList(stringArray);
            mBottomSheetDialogFragment.setDataList(reports);
            mBottomSheetDialogFragment.show(getSupportFragmentManager(), "dialog");
            mBottomSheetDialogFragment.setOnItemClickListener((adapter, view, position) -> report(position, forumDetailResp, userName));

        });
        recycleview.setAdapter(forumDetailAdapter);
        sendTv.setOnClickListener(v -> {
            if (mReplyForumDetailResp != null) {
                String replayCreate = replayCreate(mReplyForumDetailResp);
                postForum(replayCreate);
            } else {
                postForum("");
            }

        });
        fetchData(mFirstPage);
    }

    private void report(int position, ForumDetailResp forumDetailResp, String userName) {
        Observable<PayLoad<Boolean>> reportObserver = HttpManager.getInstance().forumReport(position, mTopicId, forumDetailResp.getPost_id() + "", userName);
        Disposable subscribe = reportObserver.subscribe(booleanPayLoad -> {
                    if (mBottomSheetDialogFragment != null && mBottomSheetDialogFragment.isAdded()) {
                        mBottomSheetDialogFragment.dismiss();
                    }
                    WidgetUtil.showSnackBar(ForumDetailActivity.this, booleanPayLoad.getMessage());
                }, throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage())
        );
    }

    private void postForum(String prefix) {
        if (TextUtils.isEmpty(contentEt.getText())) {
            WidgetUtil.showSnackBar(ForumDetailActivity.this, getString(R.string.comment_cannot_null));
            return;
        }
        if (contentEt.getText().toString().length() < 10) {
            WidgetUtil.showSnackBar(ForumDetailActivity.this, getString(R.string.comment_too_short));
            return;
        }
        String content = prefix + contentEt.getText().toString();
        Observable<PayLoad<ForumPostResp>> postForumObserver = HttpManager.getInstance().forumPost(mTopicId, content, GlobalDataManager.getInstance().getUserInfo().getUserName());
        Disposable subscribe = postForumObserver.subscribe(forumPostRespPayLoad -> {
            if (forumPostRespPayLoad.getCode() == PayLoad.SUCCESS) {
                WidgetUtil.showSnackBar(ForumDetailActivity.this, getString(R.string.comment_success));
                contentEt.setText("");
            } else {
                WidgetUtil.showSnackBar(ForumDetailActivity.this, forumPostRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage()));
    }


    private void checkFavorite() {
        Observable<PayLoad<CheckCollectResp>> checkFavoriteObserver = HttpManager.getInstance().checkFavorite(GlobalDataManager.getInstance().getUserInfo().getUserName(), mUrl);
        Disposable subscribeCheckFavorite = checkFavoriteObserver.subscribe(checkCollectRespPayLoad -> {
            if (checkCollectRespPayLoad.getCode() == PayLoad.SUCCESS) {
                mFavoriteId = checkCollectRespPayLoad.getData().getFavorite_id();
                setCollectIcon(checkCollectRespPayLoad.getData().getIs_exist() == 1);
            } else {
                WidgetUtil.showSnackBar(ForumDetailActivity.this, checkCollectRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage()));
    }

    private void setCollectIcon(boolean isChecked) {
        MenuItem item = toolbar.getMenu().findItem(R.id.collectItem);
        item.setChecked(isChecked);
        item.setIcon(isChecked ? R.mipmap.ic_collect_press : R.mipmap.ic_collect);
    }


    private void deleteCollect() {
        Observable<PayLoad<DeleteCollectResp>> collectObserver = HttpManager.getInstance().deleteCollect(mFavoriteId + "");
        Disposable subscribe = collectObserver.subscribe(deleteCollectRespPayLoad -> {
            if (deleteCollectRespPayLoad.getCode() == PayLoad.SUCCESS && deleteCollectRespPayLoad.getData().getSuccess() == 1) {
                WidgetUtil.showSnackBar(ForumDetailActivity.this, getString(R.string.cancel_success));
                setCollectIcon(false);
            } else {
                if (!TextUtils.isEmpty(deleteCollectRespPayLoad.getMessage())) {
                    WidgetUtil.showSnackBar(ForumDetailActivity.this, deleteCollectRespPayLoad.getMessage());
                }
                if (!TextUtils.isEmpty(deleteCollectRespPayLoad.getData().getMsg())) {
                    WidgetUtil.showSnackBar(ForumDetailActivity.this, deleteCollectRespPayLoad.getData().getMsg());
                }

            }
        }, throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage()));
    }

    private void addCollect() {
        Observable<PayLoad<AddCollectResp>> collectObserver = HttpManager.getInstance().addCollect(mTitle, mUrl, GlobalDataManager.getInstance().getUserInfo().getUserName());
        Disposable subscribe = collectObserver.subscribe(addCollectRespPayLoad -> {
            if (addCollectRespPayLoad.getCode() == PayLoad.SUCCESS && addCollectRespPayLoad.getData().getSuccess() == 1) {
                WidgetUtil.showSnackBar(ForumDetailActivity.this, getString(R.string.collect_success));
                mFavoriteId = addCollectRespPayLoad.getData().getData().getId() + "";
                setCollectIcon(true);
            } else {
                if (!TextUtils.isEmpty(addCollectRespPayLoad.getMessage())) {
                    WidgetUtil.showSnackBar(ForumDetailActivity.this, addCollectRespPayLoad.getMessage());
                }
                if (!TextUtils.isEmpty(addCollectRespPayLoad.getData().getMsg())) {
                    WidgetUtil.showSnackBar(ForumDetailActivity.this, addCollectRespPayLoad.getData().getMsg());
                }
            }
        }, throwable -> WidgetUtil.showSnackBar(ForumDetailActivity.this, throwable.getMessage()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public interface ReplyClickListener {
        void onClick(ForumDetailResp forumDetailResp);
    }

    public interface ReportClickListener {
        void onClick(ForumDetailResp forumDetailResp);
    }

    public String replayCreate(ForumDetailResp forumDetailResp) {
        return String.format("[quote=引用 %d楼 %s的回复:]%s[/quote]", forumDetailResp.getFloor(), forumDetailResp.getNickname(), forumDetailResp.getBody());
    }

}
