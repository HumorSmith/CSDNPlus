package com.ifreedomer.cplus.activity.markdown;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.widget.MarkdownPreviewView;
import com.ifreedomer.cplus.widget.TabIconView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkdownEditorActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    private TabIconView mTabIconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markdown_editor_activity);
        ButterKnife.bind(this);
        initTab();

        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.deploy_title));


//        toolbar.setTitle(R.string.deploy_title);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.menu_editor_frag);
        return super.onPrepareOptionsMenu(menu);
    }

    private void initTab() {
        mTabIconView = (TabIconView) findViewById(R.id.tabIconView);
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
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "- ");
                editRelayout.setVisibility(View.GONE);
                markdownPreview.setVisibility(View.VISIBLE);
                markdownPreview.parseMarkdown(markdownEt.getText().toString(), true);
                break;
            case R.id.id_shortcut_format_numbers:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "1. ");
                break;
            case R.id.id_shortcut_format_header_1:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "#");
                break;
            case R.id.id_shortcut_format_header_2:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "##");
                break;
            case R.id.id_shortcut_format_header_3:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "###");
                break;
            case R.id.id_shortcut_format_header_4:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "####");
                break;
            case R.id.id_shortcut_format_header_5:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "#####");
                break;
            case R.id.id_shortcut_format_header_6:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "######");
                break;
            case R.id.id_shortcut_format_bold:
                markdownEt.setText(markdownEt.getText().toString() + "**加粗**");
                break;
            case R.id.id_shortcut_grid:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + "|  |  |\n" +
                        "|--|--|\n" +
                        "|  |  |");
                break;
            case R.id.id_shortcut_format_quote:
                markdownEt.setText(markdownEt.getText().toString() + "\n" + ">");
                break;
            case R.id.id_shortcut_insert_link:
                markdownEt.setText(markdownEt.getText().toString() + getString(R.string.append_desc));
                break;
            case R.id.id_shortcut_xml:
                markdownEt.setText(markdownEt.getText().toString() + "```java\n```");
                break;
            case R.id.id_shortcut_format_strikethrough:
                markdownEt.setText(markdownEt.getText().toString() + "~~划线~~");
                break;
            case R.id.id_shortcut_format_italic:
                markdownEt.setText(markdownEt.getText().toString() + "*斜体*");
                break;


        }
    }
}
