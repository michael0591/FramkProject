package com.utils.util;

import android.app.Activity;
import java.util.Stack;

public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getScreenManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    //退出栈顶Activity 
    public void popActivity(Activity activity) {
        LogUtil.e("ActivityManager", "popActivity: " + activity.getClass().getName());
        if (activity != null) {
            //在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    //获得当前栈顶Activity 
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }

    //将当前Activity推入栈中 
    public void pushActivity(Activity activity) {
        System.out.println("pushActivity");
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 退出栈中 除了 HomeActivity
     * 之外所有Activity
     * @param cls
     */
    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {  //当前页和首页除外
                break;
            }
            popActivity(activity);
        }
    }

    //退出栈中所有Activity
    public void popAllActivityExceptOne() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 退出栈中 除了当前activity
     * @param cls
     */
    public void popAllActivityExceptnow(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {  //当前页和首页除外
                break;
            }
            popActivity(activity);
        }
    }

    //退出栈中所有Activity
    public boolean popAllActivityExceptOne(Class cls, boolean isPopCls) {
        boolean isPopActivity = false;
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                if (isPopCls) popActivity(activity);
                break;
            }
            popActivity(activity);
            isPopActivity = true;
        }
        return isPopActivity;
    }
}
