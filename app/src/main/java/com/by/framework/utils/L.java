package com.by.framework.utils;

import android.util.Log;

import com.by.framework.BuildConfig;

/**
 * Created by asus-pc on 2017/2/17.
 */

public class L {

    private static final String TAG = "by";
    private static boolean DEBUG  = BuildConfig.DEBUG;

    public static void d(String msg){
        if (DEBUG){
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg){
        if (DEBUG){
            Log.i(TAG, msg);
        }
    }

}
