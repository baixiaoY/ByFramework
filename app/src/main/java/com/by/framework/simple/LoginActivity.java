package com.by.framework.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.by.framework.R;
import com.by.framework.constants.ConstantValues;
import com.by.framework.core.AppStatusTracker;
import com.by.framework.core.BaseActivity;

/**
 * Created by asus-pc on 2017/2/16.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button mLoginSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_login,R.string.login_title);
        mLoginSubmitBtn = (Button) findViewById(R.id.mLoginSubmitBtn);
        mLoginSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_ONLINE);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
