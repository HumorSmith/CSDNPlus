package com.ifreedomer.cplus.fragment.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ifreedomer.cplus.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author:eavawu
 * @since: 02/12/2017.
 * TODO:
 */

public class CommonRecycleFragment extends Fragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.load_tv)
    TextView loadTv;
    @BindView(R.id.lin_loading)
    LinearLayout linLoading;
    @BindView(R.id.tv_no_content)
    TextView tvNoContent;
    Unbinder unbinder;
    private BaseQuickAdapter mCommonAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receycle, container, false);
        unbinder = ButterKnife.bind(this, view);
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(mCommonAdapter);
        return view;
    }


    public void setAdapter(BaseQuickAdapter commonAdapter) {
        this.mCommonAdapter = commonAdapter;
    }

    public BaseQuickAdapter getAdapter() {
        return mCommonAdapter;
    }

    public void setCommonAdapter(BaseQuickAdapter mCommonAdapter) {
        this.mCommonAdapter = mCommonAdapter;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setNoContentVisible(int visible) {
        tvNoContent.setVisibility(visible);
    }

}
