package com.example.administrator.myapplication;

import android.util.Log;

import com.yh.ydd.server.mvp.BaseApplication;

import java.util.Map;


public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();


    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

        Log.e("DOAING","内存偏低。。。");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.e("DOAING","app关闭了。。。");

    }

}
