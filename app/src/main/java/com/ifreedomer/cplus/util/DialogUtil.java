package com.ifreedomer.cplus.util;

import android.app.Activity;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ifreedomer.cplus.R;

public class DialogUtil {
    public static MaterialDialog createEditDialog(String title, Activity activity, MaterialDialog.InputCallback inputCallback) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(activity)
                .title(title)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .positiveText(R.string.confirm)
                .negativeText(R.string.cancel)
                .input(R.string.input_hint, R.string.input_prefill, inputCallback).build();
        return materialDialog;
    }
}
