package com.ifreedomer.cplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.DeployArticleActivity;
import com.ifreedomer.cplus.entity.DeployBlogContentInfo;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.cplus.widget.MarkdownPreviewView;
import com.ifreedomer.cplus.widget.TabIconView;
import com.umeng.analytics.MobclickAgent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkdownEditorFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.titleRelayout)
    RelativeLayout titleRelayout;
    @BindView(R.id.editRelayout)
    RelativeLayout editRelayout;
    @BindView(R.id.markdownPreview)
    MarkdownPreviewView markdownPreview;
    @BindView(R.id.markdowneditor)
    RelativeLayout markdowneditor;
    @BindView(R.id.titleIv)
    ImageView titleIv;
    @BindView(R.id.contentIv)
    ImageView contentIv;
    @BindView(R.id.markdownEt)
    EditText markdownEt;
    @BindView(R.id.tabIconView)
    TabIconView tabIconView;
    @BindView(R.id.titleEt)
    EditText titleEt;
    @BindView(R.id.nextTv)
    TextView nextTv;
    @BindView(R.id.previewTv)
    TextView previewTv;
    @BindView(R.id.titleLinLayout)
    RelativeLayout titleLinLayout;
    private TabIconView mTabIconView;
    private View mRootView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.markdown_editor_activity);
//        ButterKnife.bind(this);
//        initTab();
//        ToolbarUtil.setTitleBarWithBack(this, toolbar, "");
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = View.inflate(getContext(), R.layout.markdown_editor_fragment, null);
        ButterKnife.bind(this, mRootView);
        initTab();
        initView();
        return mRootView;

    }

    private void initView() {
        nextTv.setOnClickListener(this);
        previewTv.setOnClickListener(this);
    }


    private void doPreview(TextView item) {
        if (item.getText().equals(getString(R.string.preview))) {
            editRelayout.setVisibility(View.GONE);
            markdownPreview.setVisibility(View.VISIBLE);
            markdownPreview.parseMarkdown(markdownEt.getText().toString(), true);
            item.setText(getString(R.string.edit));
            MobclickAgent.onEvent(getContext(), "create_article_preview", "create_article_preview");
        } else {
            editRelayout.setVisibility(View.VISIBLE);
            markdownPreview.setVisibility(View.GONE);
            item.setText(getString(R.string.preview));
        }
    }

    private boolean doNext() {
        if (TextUtils.isEmpty(titleEt.getText().toString())) {
            WidgetUtil.showSnackBar(getActivity(), getString(R.string.title_cannot_null));
            return false;
        }
        if (TextUtils.isEmpty(markdownEt.getText().toString())) {
            WidgetUtil.showSnackBar(getActivity(), getString(R.string.write_something));
            return false;
        }
        ShowDeployDialog();
        return false;
    }


    private void ShowDeployDialog() {
        DeployBlogContentInfo deployBlogContentInfo = new DeployBlogContentInfo();
        deployBlogContentInfo.setMarkdownContent(markdownEt.getText().toString());
        deployBlogContentInfo.setContent(markdownEt.getText().toString());
        deployBlogContentInfo.setTitle(titleEt.getText().toString());
        GlobalDataManager.getInstance().setDeployBlogContentInfo(deployBlogContentInfo);
        startActivity(new Intent(getActivity(), DeployArticleActivity.class));

    }

    private void initTab() {
        mTabIconView = (TabIconView) mRootView.findViewById(R.id.tabIconView);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_list_bulleted, R.id.id_shortcut_list_bulleted, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_list_numbers, R.id.id_shortcut_format_numbers, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_insert_link, R.id.id_shortcut_insert_link, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_insert_photo, R.id.id_shortcut_insert_photo, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_console, R.id.id_shortcut_console, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_bold, R.id.id_shortcut_format_bold, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_italic, R.id.id_shortcut_format_italic, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_header_1, R.id.id_shortcut_format_header_1, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_header_2, R.id.id_shortcut_format_header_2, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_header_3, R.id.id_shortcut_format_header_3, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_quote, R.id.id_shortcut_format_quote, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_xml, R.id.id_shortcut_xml, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_minus, R.id.id_shortcut_minus, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_strikethrough, R.id.id_shortcut_format_strikethrough, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_grid, R.id.id_shortcut_grid, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_header_4, R.id.id_shortcut_format_header_4, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_header_5, R.id.id_shortcut_format_header_5, this);
        mTabIconView.addTab(R.mipmap.ic_shortcut_format_header_6, R.id.id_shortcut_format_header_6, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_shortcut_list_bulleted:
//                markdownEt.setText(markdownEt.getText().toString() + "\n" + "- ");
//                editRelayout.setVisibility(View.GONE);
//                markdownPreview.setVisibility(View.VISIBLE);
//                markdownPreview.parseMarkdown(markdownEt.getText().toString(), true);
                break;
            case R.id.id_shortcut_format_numbers:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "1. ");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_format_header_1:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "#");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_format_header_2:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "##");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_format_header_3:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "###");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_format_header_4:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "####");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_format_header_5:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "#####");
                markdownEt.setSelection(markdownEt.getText().length() );
                break;
            case R.id.id_shortcut_format_header_6:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "######");
                markdownEt.setSelection(markdownEt.getText().length() );
                break;
            case R.id.id_shortcut_format_bold:
                markdownEt.setText(markdownEt.getText().toString() + "**加粗**");
                markdownEt.setSelection(markdownEt.getText().length() - 2);
                break;
            case R.id.id_shortcut_grid:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "|  |  |\n" +
                        "|--|--|\n" +
                        "|  |  |");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_format_quote:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + ">");
                markdownEt.setSelection(markdownEt.getText().length());
                break;
            case R.id.id_shortcut_insert_link:
                markdownEt.setText(markdownEt.getText().toString() + getString(R.string.append_desc));
                markdownEt.setSelection(markdownEt.getText().length());

                break;
            case R.id.id_shortcut_xml:
                markdownEt.setText(markdownEt.getText().toString() + "```java\n```");
                markdownEt.setSelection(markdownEt.getText().length() - 3);
                break;
            case R.id.id_shortcut_format_strikethrough:
                markdownEt.setText(markdownEt.getText().toString() + "~~划线~~");
                markdownEt.setSelection(markdownEt.getText().length() - 2);
                break;
            case R.id.id_shortcut_format_italic:
                markdownEt.setText(markdownEt.getText().toString() + "*斜体*");
                markdownEt.setSelection(markdownEt.getText().length() - 1);
                break;
            case R.id.previewTv:
                doPreview(previewTv);
                break;
            case R.id.nextTv:
                doNext();
                break;
        }

    }

    public static MarkdownEditorFragment newInstance() {
        return new MarkdownEditorFragment();
    }
}
