package com.ifreedomer.cplus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageTopItem extends LinearLayout {
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.iconIv)
    ImageView iconIv;

    public MessageTopItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.layout_item_message_top, this);
        ButterKnife.bind(this);
    }

    public void setText(String text) {
        titleTv.setText(text);
    }

    public void setIcon(int icon) {
        iconIv.setImageResource(icon);
    }

}
