package com.by.framework.model;

import com.by.framework.core.BaseActivity;

/**
 * Created by asus-pc on 2017/2/22.
 */

public class Module {
    public String moduleName;
    public String moduleIcon;
    public Class<? extends BaseActivity> moduleTargetClass;

    public Module(String moduleName, Class<? extends BaseActivity> moduleTargetClass) {
        this.moduleName = moduleName;
        this.moduleTargetClass = moduleTargetClass;
    }
}
