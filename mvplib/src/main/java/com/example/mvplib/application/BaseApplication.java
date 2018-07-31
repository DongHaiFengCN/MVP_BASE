package com.example.mvplib.application;

import android.app.Application;
import android.util.Log;

/**
 * 基础初始化的application
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("DOAING","BaseApplication");
    }
}
