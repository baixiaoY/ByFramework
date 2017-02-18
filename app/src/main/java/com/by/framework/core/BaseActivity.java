package com.by.framework.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.constants.ConstantValues;
import com.by.framework.simple.HomeActivity;

/**
 * Created by asus-pc on 2017/2/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    protected Toolbar toolbar;
    protected TextView toolbar_title;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;
    public static final int MODE_HOME = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (AppStatusTracker.getInstance().getAppStatus()){
            case ConstantValues.STATUS_FORCE_KILLED:
                protectApp();
                break;
            case ConstantValues.STATUS_KICK_OUT:
            case ConstantValues.STATUS_LOGOUT:
            case ConstantValues.STATUS_OFFLINE:
            case ConstantValues.STATUS_ONLINE:
                setUpContentView();
                setUpView();
                setUpData();
                break;
        }
    }

    protected abstract void setUpContentView();

    protected abstract void setUpView();

    protected abstract void setUpData();

    protected void protectApp() {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_RESTART_APP);
        startActivity(intent);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(layoutResID, -1, -1, MODE_BACK);
    }

    protected void setContentView(int layoutResID,int titleResID){
        setContentView(layoutResID, titleResID, -1, MODE_BACK);
    }

    public void setContentView(int layoutResID, int titleResId, int menuId, int mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResId, menuId, mode);
    }

    protected void setUpToolbar(int titleResId, int menuId, int mode){
        if (mode!=MODE_NONE){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar_title = (TextView) findViewById(R.id.toolbar_title);
            toolbar_title.setText("");
            if (mode == MODE_BACK){
                toolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNavigationBtnClicked();
                    }
                });
            }
            if (mode==MODE_HOME){
                setSupportActionBar(toolbar);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            setUpTitle(titleResId);
            setUpMenu(menuId);
        }


    }

    protected void setUpMenu(int menuId) {
        if (toolbar!=null){
            toolbar.getMenu().clear();
            if (menuId>0){
                toolbar.inflateMenu(menuId);
                toolbar.setOnMenuItemClickListener(this);
            }
        }

    }

    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && toolbar_title != null) {
            toolbar_title.setText(titleResId);
        }
    }


    protected void onNavigationBtnClicked() {
        finish();
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
