package com.df.smallwater.smallwater.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.mob.MobSDK;

/**
 * APPLICATION
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        MobSDK.init(this);

    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }



}
