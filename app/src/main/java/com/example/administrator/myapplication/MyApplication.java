package com.example.administrator.myapplication;

import android.util.Log;

import com.yh.ydd.base.config.BaseApplication;


public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("DOAING", "MyApplication");
    }



}
