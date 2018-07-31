package com.example.administrator.myapplication;

import android.util.Log;

import com.example.mvplib.application.BaseApplication;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("DOAING","MyApplication");
    }
}
