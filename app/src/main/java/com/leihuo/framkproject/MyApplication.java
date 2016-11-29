package com.leihuo.framkproject;

import android.app.Application;
import com.utils.util.ActivityManager;

/**
 * 自定义Application，目前主要为facebook的初始化，项目语言初始化
 */
public class MyApplication extends Application {
    private ActivityManager activityManager = null;

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityManager = ActivityManager.getScreenManager();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
