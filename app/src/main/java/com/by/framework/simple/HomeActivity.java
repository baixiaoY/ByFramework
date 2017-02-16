package com.by.framework.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.by.framework.R;
import com.by.framework.constants.ConstantValues;
import com.by.framework.core.BaseActivity;
import com.by.framework.core.CustomApplication;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/16.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private Button mHomeProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_home,R.string.home_title,R.menu.home_menu,MODE_HOME);
        mHomeProfileBtn = (Button) findViewById(R.id.mHomeProfileBtn);
        mHomeProfileBtn.setOnClickListener(this);
        CustomApplication.mTestNullPointers = new ArrayList<>();
        CustomApplication.mTestNullPointers.add("profile");
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_BACK_TO_HOME);
        switch (action) {
            case ConstantValues.ACTION_KICK_OUT:
                break;
            case ConstantValues.ACTION_LOGOUT:
                break;
            case ConstantValues.ACTION_RESTART_APP:
                protectApp();
                break;
            case ConstantValues.ACTION_BACK_TO_HOME:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onMenuItemClick(item);
    }

    @Override
    protected void protectApp() {
        startActivity(new Intent(this,WelcomeActivity.class));
        finish();
    }
}
