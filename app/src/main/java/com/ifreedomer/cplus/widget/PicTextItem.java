package com.ifreedomer.cplus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ifreedomer.cplus.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PicTextItem extends LinearLayout {
    @BindView(R.id.itemIv)
    ImageView itemIv;
    @BindView(R.id.itemTv)
    TextView itemTv;

    public PicTextItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.layout_pic_text, this);
        ButterKnife.bind(this);
    }

    public void setText(String text) {
        itemTv.setText(text);
    }

    public void setImageUrl(String url) {
        Glide.with((View) itemIv).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(itemIv);
    }

    public void setIcon(int icon) {
        itemIv.setImageResource(icon);
    }


}
