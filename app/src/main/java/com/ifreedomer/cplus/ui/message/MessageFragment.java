package com.ifreedomer.cplus.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.widget.MessageTopItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFragment extends Fragment {

    @BindView(R.id.topLayout)
    LinearLayout topLayout;
    @BindView(R.id.titleRelayout)
    RelativeLayout titleRelayout;
    @BindView(R.id.leaveMessageItem)
    MessageTopItem leaveMessageItem;
    @BindView(R.id.collectItem)
    MessageTopItem collectItem;
    @BindView(R.id.notifiItem)
    MessageTopItem notifiItem;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MessageViewModel mViewModel;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);
        ButterKnife.bind(this, view);
        initItems();
        return view;
    }

    private void initToolbar() {
        ToolbarUtil.setTitleBar((AppCompatActivity) getActivity(), toolbar, getString(R.string.message));
    }

    private void initItems() {
        leaveMessageItem.setText(getString(R.string.commentLeaveMessage));
        leaveMessageItem.setIcon(R.mipmap.ic_leave_message);
        collectItem.setText(getString(R.string.collection));
        collectItem.setIcon(R.mipmap.ic_collect);
        notifiItem.setText(getString(R.string.systemNotify));
        notifiItem.setIcon(R.mipmap.ic_ring);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        initToolbar();
        // TODO: Use the ViewModel
    }

}
