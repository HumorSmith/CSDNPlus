package com.ifreedomer.cplus;

import android.os.Bundle;

import com.ifreedomer.cplus.ui.forgetpassword.ForgetPasswordFragment;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ForgetPasswordFragment.newInstance())
                    .commitNow();
        }
    }
}
