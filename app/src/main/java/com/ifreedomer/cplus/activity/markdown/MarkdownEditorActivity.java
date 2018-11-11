package com.ifreedomer.cplus.activity.markdown;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.DeployArticleActivity;
import com.ifreedomer.cplus.entity.DeployBlogContentInfo;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.MarkdownPreviewView;
import com.ifreedomer.cplus.widget.TabIconView;
import com.umeng.analytics.MobclickAgent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkdownEditorActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markdown_editor_activity);
        ButterKnife.bind(this);
    }



}
