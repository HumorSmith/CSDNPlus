package com.ifreedomer.cplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.widget.SettingItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

public class ForumDeployActivity extends AppCompatActivity {

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
    @BindView(R.id.markdownEt)
    EditText markdownEt;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_deploy);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.deploy_forum));
        scoreItem.setText(getString(R.string.select_score));
        scoreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForumDeployActivity.this, SelectScoreActivity.class));
            }
        });

        String[] tags = getResources().getStringArray(R.array.blog_category);
        forumTagGroup.setTags(tags);


    }
}
