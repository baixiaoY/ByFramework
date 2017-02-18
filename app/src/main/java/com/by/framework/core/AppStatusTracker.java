package com.by.framework.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.by.framework.constants.ConstantValues;
import com.by.framework.utils.L;

/**
 * 应用状态
 * Created by asus-pc on 2017/2/16.
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks{

    private static AppStatusTracker tracker;
    private int mAppStatus = ConstantValues.STATUS_FORCE_KILLED;

    private AppStatusTracker() {

    }

    public static AppStatusTracker getInstance() {
        if (tracker == null) {
            tracker = new AppStatusTracker();
        }
        return tracker;
    }

    public void setAppStatus(int status) {
        this.mAppStatus = status;
    }

    public int getAppStatus() {
        return this.mAppStatus;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        L.i(activity.toString() + " onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        L.i(activity.toString() + " onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        L.i(activity.toString() + " onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        L.i(activity.toString() + " onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        L.i(activity.toString() + " onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        L.i(activity.toString() + " onActivityDestroyed");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
