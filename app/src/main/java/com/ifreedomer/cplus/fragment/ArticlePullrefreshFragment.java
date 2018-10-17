package com.ifreedomer.cplus.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifreedomer.cplus.fragment.common.BasePullRefreshPageFragment;
import com.ifreedomer.cplus.http.protocol.resp.ArticleResp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class ArticlePullrefreshFragment extends BasePullRefreshPageFragment<ArticleResp> {
    public static final String TAG = ArticlePullrefreshFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initAdapter();
        return view;
    }

    protected abstract void initAdapter();


    @Override
    public void fetchData() {
        long offset = 0;
        String type = "more";
        if (mDataList.size() > 0) {
            offset = mDataList.get(mDataList.size() - 1).getShown_offset();
            if (offset == 0 && mDataList.size() > 1) {
                offset = mDataList.get(mDataList.size() - 2).getShown_offset();
            }
            Log.d(TAG, "offset right = " + offset);
        } else {
            Log.d(TAG, "offset wrong = " + 0);

        }

        if (mCurPage == 0) {
            offset = 0;
            type = "new";
        }
        Log.d(TAG, "type =" + type + " offset = " + offset + "  page = " + mCurPage + "  size = " + mDataList.size() + "  data = " + mDataList.toString());
        fetchData(type, offset);
    }



    public abstract void fetchData(String type, long offset);
}
