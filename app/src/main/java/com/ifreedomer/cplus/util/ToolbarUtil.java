package com.ifreedomer.cplus.util;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ToolbarUtil {

    public static void setTitleBarWhiteBack(AppCompatActivity context, Toolbar toolbar, String title) {
        context.setSupportActionBar(toolbar);
        if (context.getSupportActionBar() != null) {
            context.getSupportActionBar().setTitle(title);
        }
        toolbar.setNavigationOnClickListener(v -> context.finish());
    }
}
