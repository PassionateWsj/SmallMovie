package com.sha1607.smallmovie.app;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/25 下午 9:37
 * name:
 * desc:
 * step:
 **********************************
 */

import android.app.Activity;
import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

public class IApp extends Application {
    public static boolean debugOn = false;
    private static IApp instance;
    private Set<Activity> allActivities;


    @Override
    public void onCreate() {
        super.onCreate();
        // mob短信验证
        SMSSDK.initSDK(this, "188422b3f315c", "16f7bcaa10b39fe958dbe6889de2fb8c");
        // mob分享
        ShareSDK.initSDK(this);
        // LeakCanary
        LeakCanary.install(this);
        // 程序异常信息报错
       /* CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/

    }

    public static synchronized IApp getInstance() {
        return instance;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);

    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

}
