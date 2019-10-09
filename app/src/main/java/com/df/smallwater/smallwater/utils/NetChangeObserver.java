package com.df.smallwater.smallwater.utils;

/**
 * Created by hasee on 2017/10/20.
 */

public interface NetChangeObserver {
    /**
     * 网络连接回调 type为网络类型
     */
    void onNetConnected();
    /**
     * 没有网络
     */
    void onNetDisConnect();
}