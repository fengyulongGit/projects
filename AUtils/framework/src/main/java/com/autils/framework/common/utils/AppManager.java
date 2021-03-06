package com.autils.framework.common.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by fengyulong on 2018/5/12.
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    private Class<?> mainClass;

    public void setMainClass(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            //activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束结束所有的Activity
     */
    public void finishAllActivity() {
        finishAllActivity(false);
    }

    /**
     * 结束结束所有的Activity
     */
    public void finishAllActivity(boolean isAll) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                if (!isAll && mainClass != null) {
                    if (activityStack.get(i).getClass() != mainClass) {
                        activityStack.get(i).finish();
                        activityStack.remove(i);
                        i--;
                    }
                } else {
                    activityStack.get(i).finish();
                    activityStack.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * mList是否包含有webview的页面
     *
     * @param activitycon
     * @return
     */
    public boolean containActivity(String activitycon) {
        boolean isContain = false;
        for (Activity activity : activityStack) {
            if (activity != null && activity.getClass().getName().equals(activitycon)) {
                isContain = true;
            }
        }
        return isContain;
    }
}
