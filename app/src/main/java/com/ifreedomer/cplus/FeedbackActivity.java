package com.ifreedomer.cplus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifreedomer.cplus.mail.MailStateListener;
import com.ifreedomer.cplus.mail.MailThread;
import com.ifreedomer.cplus.util.ToolbarUtil;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.contentEt)
    EditText contentEt;
    @BindView(R.id.mailEt)
    EditText mailEt;
    @BindView(R.id.commitBtn)
    Button commitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        ToolbarUtil.setTitleBarWithBack(this, toolbar, getString(R.string.feedback));
        commitBtn.setOnClickListener(this);
        mailEt.addTextChangedListener(mTextWatcher);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            commitBtn.setEnabled(!TextUtils.isEmpty(contentEt.getText().toString()) && !TextUtils.isEmpty(mailEt.getText().toString()));

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commitBtn:
                MailThread mailThread = new MailThread(FeedbackActivity.this, contentEt.getText().toString(), contentEt.getText().toString(), mailEt.getText().toString());
                mailThread.setMailStateListener(new MailStateListener() {
                    @Override
                    public void onSuccess() {
                        runOnUiThread(() -> WidgetUtil.showSnackBar(FeedbackActivity.this, getString(R.string.feedback_success)));
                        FeedbackActivity.this.finish();
                    }

                    @Override
                    public void onError() {
                        runOnUiThread(() -> WidgetUtil.showSnackBar(FeedbackActivity.this, getString(R.string.feedback_failed)));

                    }
                });
                mailThread.start();
                break;

        }
    }
}
