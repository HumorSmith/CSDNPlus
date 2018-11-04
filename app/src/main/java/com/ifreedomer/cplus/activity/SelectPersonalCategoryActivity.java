package com.ifreedomer.cplus.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.TextListAdapter;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.BlogCategoryResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.DialogUtil;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class SelectPersonalCategoryActivity extends AppCompatActivity {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.myScoreTv)
    TextView myScoreTv;
    private List<String> mDataList = new ArrayList<>();
    public static final String SELECT_KEY = "select";
    public static final String LIST_KEY = "list";
    private String mSelectText = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_score);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.person_category));
        toolbar.setNavigationOnClickListener(v -> {
            getBackIntent();
            SelectPersonalCategoryActivity.this.finish();
        });


        recycleview.setLayoutManager(new LinearLayoutManager(this));
        TextListAdapter textListAdapter = new TextListAdapter(R.layout.item_text_select, mDataList);
        recycleview.setAdapter(textListAdapter);
        textListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSelectText = mDataList.get(position);
                WidgetUtil.showSnackBar(SelectPersonalCategoryActivity.this, mSelectText);
            }

        });
        WidgetUtil.showSnackBar(SelectPersonalCategoryActivity.this, getString(R.string.load_category));
        loadCategory();

    }

    private void loadCategory() {
        mDataList.clear();
        Observable<PayLoad<List<BlogCategoryResp>>> blogCatergoryObserver = HttpManager.getInstance().getBlogCatergory(GlobalDataManager.getInstance().getUserInfo().getUserName());
        blogCatergoryObserver.subscribe(listPayLoad -> {
            if (listPayLoad.getCode() == PayLoad.SUCCESS) {
                List<BlogCategoryResp> data = listPayLoad.getData();
                for (int i = 0; i < data.size(); i++) {
                    mDataList.add(data.get(i).getName());
                }
                recycleview.getAdapter().notifyDataSetChanged();
            } else {
                WidgetUtil.showSnackBar(SelectPersonalCategoryActivity.this, listPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(SelectPersonalCategoryActivity.this, throwable.getMessage()));
    }

    @Override
    public void onBackPressed() {
        getBackIntent();
        super.onBackPressed();
    }

    private void getBackIntent() {
        Intent intent = new Intent();
        intent.putExtra(SELECT_KEY, mSelectText);
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.select_personal_category_menu);
        toolbar.getMenu().findItem(R.id.addItem).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                MaterialDialog editDialog = DialogUtil.createEditDialog(getString(R.string.create_personal_category), SelectPersonalCategoryActivity.this, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (TextUtils.isEmpty(input)) {
                            WidgetUtil.showSnackBar(SelectPersonalCategoryActivity.this, getString(R.string.no_content));
                            return;
                        }
                        WidgetUtil.showSnackBar(SelectPersonalCategoryActivity.this, getString(R.string.create_category_success));
                        mDataList.add(0, input.toString());
                        recycleview.getAdapter().notifyDataSetChanged();
                    }
                });
                editDialog.show();
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
}
