package com.by.framework.core;

import com.by.framework.constants.ConstantValues;

/**
 * 应用状态
 * Created by asus-pc on 2017/2/16.
 */

public class AppStatusTracker {

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
}
