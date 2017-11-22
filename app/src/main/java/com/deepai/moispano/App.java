package com.deepai.moispano;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.deepai.moispano.cache.UserCache;
import com.deepai.moispano.data.rest.WorkApiService;
import com.deepai.moispano.utils.BackgroundUtil;
import com.deepai.moispano.utils.LogUtil;
import com.google.gson.Gson;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * @author ZhaoZaigang
 * @Description
 * @date 2017/9/11  15:59
 */

public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    private static App instance;

    private int activityCount;


    private LogUtil log;
    private Gson gson;
    private UserCache userCache;
    private WorkApiService workApiService;

    public final static boolean DEBUG = BuildConfig.DEBUG;

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
        if (DEBUG) {
            CustomActivityOnCrash.install(this);
        }
        instance = this;
        // 注册ActivityLifecycleCallbacks
        registerActivityLifecycleCallbacks(this);
        userCache = new UserCache(this);
        log = new LogUtil(BuildConfig.DEBUG);
        gson = new Gson();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCount--;
        if (activityCount <= 0) {

        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

    public int getActivityCount() {
        return activityCount;
    }

    public boolean isForeground() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return BackgroundUtil.getRunningTask(this, getPackageName());
        } else {
            return activityCount > 0;
        }
    }

    public UserCache getUserCache() {
        return userCache;
    }

    public LogUtil getLog() {
        return log;
    }

    public WorkApiService getWorkApiService() {
        return workApiService;
    }

   /* public SurveyApiService getSurveyApiService() {
        return surveyApiService;
    }

    public FoodApiService getFoodApiService() {
        return foodApiService;
    }

    public UploadApiService getUploadApiService() {
        return uploadApiService;
    }*/

    public Gson getDefaultGson() {
        return gson;
    }

    /**
     * 是否是登录状态
     *
     * @return true 登录状态
     */
    public boolean isLogin() {
        return !TextUtils.isEmpty(userCache.getUUID());
    }

    /**
     * 获取当前进程名
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        if (context == null)
            return null;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null)
            return null;
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess != null && appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
