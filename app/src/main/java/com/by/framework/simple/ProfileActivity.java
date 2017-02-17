package com.by.framework.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.core.BaseActivity;
import com.by.framework.core.CustomApplication;

/**
 * Created by asus-pc on 2017/2/16.
 */

public class ProfileActivity extends BaseActivity {

    private TextView mProfileLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_profile,R.string.profile_title,R.menu.main_menu,MODE_BACK);
        mProfileLabel = (TextView) findViewById(R.id.mProfileLabel);
        mProfileLabel.setText(CustomApplication.mTestNullPointers.toString());
    }
}
