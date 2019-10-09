package com.df.smallwater.smallwater.app;


import com.df.smallwater.smallwater.base.BaseApplication;

/**
 * Created by hasee on 2017/12/13.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();

//        NetStateReceiver.unRegisterNetworkStateReceiver(this);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        exit(0);
    }
}
