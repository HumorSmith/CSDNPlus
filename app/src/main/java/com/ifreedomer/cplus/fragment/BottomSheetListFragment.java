package com.ifreedomer.cplus.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.adapter.TextListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomSheetListFragment extends BottomSheetDialogFragment {
    public static final String TAG = BottomSheetListFragment.class.getSimpleName();

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<String> mDataList = new ArrayList<>();

    public BottomSheetListFragment() {
        Log.d(TAG, "BottomSheetListFragment");
    }

    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener;

    public BaseQuickAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_list, null);
        ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView");
        initData();
        return view;
    }

    private void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        TextListAdapter textListAdapter = new TextListAdapter(R.layout.item_bottom_sheet, mDataList);
        textListAdapter.setOnItemClickListener(mOnItemClickListener);
        recyclerview.setAdapter(textListAdapter);

    }

    public List<String> getDataList() {
        return mDataList;
    }

    public void setDataList(List<String> dataList) {
        Log.d(TAG, "setDataList");
        this.mDataList = dataList;
        if (recyclerview != null && recyclerview.getAdapter() != null) {
            recyclerview.getAdapter().notifyDataSetChanged();
        }
    }


}
