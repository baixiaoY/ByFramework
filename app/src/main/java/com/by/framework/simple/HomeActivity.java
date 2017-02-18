package com.by.framework.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.by.framework.R;
import com.by.framework.constants.ConstantValues;
import com.by.framework.core.BaseActivity;
import com.by.framework.core.CustomApplication;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/16.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Button mHomeProfileBtn;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_home,R.string.home_title,R.menu.main_menu,MODE_HOME);
    }

    @Override
    protected void setUpView() {
        mHomeProfileBtn = (Button) findViewById(R.id.mHomeProfileBtn);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mHomeProfileBtn.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

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
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, SimpleListActivity.class));
                break;
        }
        return super.onMenuItemClick(item);
    }

    @Override
    protected void protectApp() {
        startActivity(new Intent(this,WelcomeActivity.class));
        finish();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        toolbar.getMenu().clear();
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_one:
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame,new FragmentOne()).commit();
                Toast.makeText(this,"item1",Toast.LENGTH_SHORT);
                break;
            case R.id.item_two:
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentTwo()).commit();
                Toast.makeText(this,"item2",Toast.LENGTH_SHORT);

                break;
            case R.id.item_three:
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentThree()).commit();
                Toast.makeText(this,"item3",Toast.LENGTH_SHORT);

                break;
        }

        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }
}
