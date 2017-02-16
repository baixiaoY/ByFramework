package com.by.framework.core;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/16.
 */

public class CustomApplication extends Application {
    public static ArrayList<String> mTestNullPointers;
    public static int mAppStatus = -1;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
