package com.ifreedomer.cplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.SettingItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

public class ForumDeployActivity extends AppCompatActivity {
    public static final int SCORE_REQUEST_CODE = 1;

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.titleIv)
    ImageView titleIv;
    @BindView(R.id.titleEt)
    EditText titleEt;
    @BindView(R.id.titleRelayout)
    RelativeLayout titleRelayout;
    @BindView(R.id.contentIv)
    ImageView contentIv;
    @BindView(R.id.contentEt)
    EditText contentEt;
    @BindView(R.id.editRelayout)
    RelativeLayout editRelayout;
    @BindView(R.id.contentRelayout)
    RelativeLayout contentRelayout;
    @BindView(R.id.scoreItem)
    SettingItem scoreItem;
    @BindView(R.id.selectScoreLayout)
    CardView selectScoreLayout;
    @BindView(R.id.editTv)
    TextView editTv;
    @BindView(R.id.forumTagGroup)
    TagContainerLayout forumTagGroup;
    @BindView(R.id.scoreTv)
    TextView scoreTv;
    private int mSelectScore = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_deploy);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.deploy_forum));
        scoreItem.setText(getString(R.string.select_score));
        scoreItem.setOnClickListener(v -> startActivityForResult(new Intent(ForumDeployActivity.this, SelectScoreActivity.class), SCORE_REQUEST_CODE));

        String[] tags = getResources().getStringArray(R.array.blog_category);
        forumTagGroup.setTags(tags);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.forum_deploy_menu);
        toolbar.getMenu().findItem(R.id.sendItem).setOnMenuItemClickListener(item -> {
            createTopic();
            return false;
        });
        return super.onPrepareOptionsMenu(menu);
    }

    private void createTopic() {
        if (TextUtils.isEmpty(titleEt.getText().toString())) {
            WidgetUtil.showSnackBar(ForumDeployActivity.this, getString(R.string.forum_title_cannot_null));
            return;
        }

        if (TextUtils.isEmpty(contentEt.getText().toString())) {
            WidgetUtil.showSnackBar(ForumDeployActivity.this, getString(R.string.forum_content_cannot_null));
            return;
        }

        if (mSelectScore < 0) {
            WidgetUtil.showSnackBar(ForumDeployActivity.this, getString(R.string.forum_score_must_select));
            return;
        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpManager.getInstance().forumCreateTopic(titleEt.getText().toString(), contentEt.getText().toString(), "MSSQL_NewTech", mSelectScore);
//            }
//        }).start();

//
       HttpManager.getInstance().forumCreateTopic(titleEt.getText().toString(), contentEt.getText().toString(), "MSSQL_NewTech", mSelectScore);
//        createTopicObserver.subscribe(createTopicRespPayLoad -> {
//            WidgetUtil.showSnackBar(ForumDeployActivity.this, createTopicRespPayLoad);
//        }, throwable -> WidgetUtil.showSnackBar(ForumDeployActivity.this, throwable.getMessage()));

//        Observable<PayLoad<CreateTopicResp>> createTopicObserver = HttpManager.getInstance().forumCreateTopic( titleEt.getText().toString(), contentEt.getText().toString(),"MSSQL_NewTech", mSelectScore);
//        createTopicObserver.subscribe(createTopicRespPayLoad -> {
//            if (createTopicRespPayLoad.getCode() == PayLoad.SUCCESS) {
//                WidgetUtil.showSnackBar(ForumDeployActivity.this, getString(R.string.create_forum_success));
//                ForumDeployActivity.this.finish();
//            } else {
//                WidgetUtil.showSnackBar(ForumDeployActivity.this, createTopicRespPayLoad.getMessage());
//            }
//        }, throwable -> WidgetUtil.showSnackBar(ForumDeployActivity.this, throwable.getMessage()));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SCORE_REQUEST_CODE && data != null) {
            mSelectScore = data.getIntExtra(SelectScoreActivity.SELECT_KEY, -1);
            scoreTv.setText(mSelectScore + "");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
