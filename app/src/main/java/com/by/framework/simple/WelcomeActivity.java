package com.by.framework.simple;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.by.framework.R;
import com.by.framework.constants.ConstantValues;
import com.by.framework.core.AppStatusTracker;
import com.by.framework.core.BaseActivity;
import com.by.framework.core.CustomApplication;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_OFFLINE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpData() {
        setContentView(R.layout.activity_welcome,-1,-1,MODE_NONE);
        handler.sendEmptyMessageDelayed(0,1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }

}
