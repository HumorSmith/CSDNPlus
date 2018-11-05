package com.ifreedomer.cplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

import static com.ifreedomer.cplus.activity.BlogCategorySelectActivity.SELECT_KEY;

public class DeployArticleActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int ARTICLE_CATEGORY_REQUEST_CODE = 1;
    public static final int BLOG_CATEGORY_REQUEST_CODE = 2;
    public static final int PERSONAL_CATEGORY_REQUEST_CODE = 3;

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.articleLabelTv)
    TextView articleLabelTv;
    @BindView(R.id.articleLabelTagGroup)
    TagGroup articleLabelTagGroup;
    @BindView(R.id.appendTv)
    TextView appendTv;
    @BindView(R.id.blogCategoryCardView)
    CardView blogCategoryCardView;
    @BindView(R.id.articleCategoryCardView)
    CardView articleCategoryCardView;
    @BindView(R.id.privateSwith)
    SwitchCompat privateSwith;
    @BindView(R.id.blogCategoryTv)
    TextView blogCategoryTv;
    @BindView(R.id.articleCategoryTv)
    TextView articleCategoryTv;
    @BindView(R.id.personalTv)
    TextView personalTv;
    @BindView(R.id.personalCardView)
    CardView personalCardView;
    private List<String> mSelectTagList = new ArrayList<>();
    private Map<String, String> articleMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delopy_article);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, "");
        initData();
        initListener();
    }

    private void initListener() {
        appendTv.setOnClickListener(this);
//        personalTagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
//            @Override
//            public void onTagClick(int position, String text) {
//                TagView tagView = personalTagGroup.getTagView(position);
//                if (mSelectTagList.contains(text)) {
//                    tagView.setTagTextColor(getResources().getColor(R.color.tagUnselectColor));
//                    tagView.setTagBackgroundColor(getResources().getColor(R.color.colorTranlate));
//                    mSelectTagList.remove(text);
//                } else {
//                    tagView.setTagBackgroundColor(getResources().getColor(R.color.tagSelectColor));
//                    tagView.setTagTextColor(getResources().getColor(R.color.whiteColor));
//                    mSelectTagList.add(text);
//                }
//
//            }
//
//            @Override
//            public void onTagLongClick(int position, String text) {
//
//            }
//
//            @Override
//            public void onTagCrossClick(int position) {
//
//            }
//        });

        blogCategoryCardView.setOnClickListener(this);
        articleCategoryCardView.setOnClickListener(this);
        personalCardView.setOnClickListener(this);


    }

    private void initData() {


        articleMap.put(getString(R.string.origin), "original");
        articleMap.put(getString(R.string.reprint), "repost");
        articleMap.put(getString(R.string.translate), "translated");
    }

//    private void addPersonalTag(List<BlogCategoryResp> categoryList) {
//        List<String> tagList = new ArrayList<>();
//        for (int i = 0; i < categoryList.size(); i++) {
//            tagList.add(categoryList.get(i).getName());
//        }
//        personalTagGroup.setTags(tagList);
//    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.blog_deploy_menu);
        toolbar.getMenu().findItem(R.id.sendItem).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                deploy();
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }


    private void deploy() {
        MobclickAgent.onEvent(DeployArticleActivity.this.getApplicationContext(), "create_article_send", "create_article_send");
        String[] articleLabelTagGroupTags = articleLabelTagGroup.getTags();
        if (articleLabelTagGroupTags == null || articleLabelTagGroupTags.length == 0) {
            WidgetUtil.showSnackBar(DeployArticleActivity.this, getString(R.string.not_select_article_tag));
            return;
        }
        if (TextUtils.isEmpty(personalTv.getText().toString())) {
            WidgetUtil.showSnackBar(DeployArticleActivity.this, getString(R.string.not_select_personal_tag));
            return;
        }
        if (TextUtils.isEmpty(blogCategoryTv.getText().toString())) {
            WidgetUtil.showSnackBar(DeployArticleActivity.this, getString(R.string.not_select_blog_category));
            return;
        }

        if (TextUtils.isEmpty(articleCategoryTv.getText().toString())) {
            WidgetUtil.showSnackBar(DeployArticleActivity.this, getString(R.string.not_select_article_category));
            return;
        }

        GlobalDataManager.getInstance().getDeployBlogContentInfo().setTags(getStringByTags(articleLabelTagGroupTags));
        GlobalDataManager.getInstance().getDeployBlogContentInfo().setCategories(getStringByTags(new String[]{personalTv.getText().toString()}));
        GlobalDataManager.getInstance().getDeployBlogContentInfo().setPrivate(privateSwith.isChecked());
        GlobalDataManager.getInstance().getDeployBlogContentInfo().setDescription(privateSwith.isChecked() ? getString(R.string.privateStr) : getString(R.string.publicStr));
        GlobalDataManager.getInstance().getDeployBlogContentInfo().setType(articleMap.get(articleCategoryTv.getText().toString()));
        startActivity(new Intent(this, WebLoginActivity.class));
    }


    public String getStringByTags(String[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            builder.append(arr[i]);
            if (i < arr.length - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appendTv:
                articleLabelTagGroup.submitTag();
                break;
            case R.id.articleCategoryCardView:
                Intent intent = new Intent(this, ArticleCategorySelectActivity.class);
                startActivityForResult(intent, ARTICLE_CATEGORY_REQUEST_CODE);
                break;
            case R.id.blogCategoryCardView:
                intent = new Intent(this, BlogCategorySelectActivity.class);
                startActivityForResult(intent, BLOG_CATEGORY_REQUEST_CODE);
                break;
            case R.id.personalCardView:
                intent = new Intent(this, SelectPersonalCategoryActivity.class);
                startActivityForResult(intent, PERSONAL_CATEGORY_REQUEST_CODE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ARTICLE_CATEGORY_REQUEST_CODE) {
            articleCategoryTv.setText(data.getStringExtra(SELECT_KEY));
        } else if (requestCode == BLOG_CATEGORY_REQUEST_CODE) {
            blogCategoryTv.setText(data.getStringExtra(SELECT_KEY));
        } else if (requestCode == PERSONAL_CATEGORY_REQUEST_CODE) {
            personalTv.setText(data.getStringExtra(SELECT_KEY));
        }
    }
}
