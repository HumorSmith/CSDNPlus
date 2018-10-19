package com.ifreedomer.cplus.util;

import android.app.Activity;
import android.content.Intent;

import com.ifreedomer.cplus.R;

public class ShareUtil {
    public static void shareString(Activity activity, String text) {
        //创建分享Intent
        Intent sharedIntent = new Intent();
        //设置动作为Intent.ACTION_SEND
        sharedIntent.setAction(Intent.ACTION_SEND);
        //设置为文本类型
        sharedIntent.setType("text/*");
        sharedIntent.putExtra(Intent.EXTRA_TEXT, text);    //设置要分享的内容
        activity.startActivity(Intent.createChooser(sharedIntent, activity.getString(R.string.share_to)));

    }
}
