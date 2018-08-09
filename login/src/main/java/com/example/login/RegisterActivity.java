package com.example.login;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

@SuppressLint("Registered")
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("DOAING","RegisterActivity 启动了");
       /*  localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent();
        intent.putExtra("test","董海峰");
        intent.setAction("cn.programmer.test");
        localBroadcastManager.sendBroadcast(intent);*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("DOAING","RegisterActivity 关闭了");
    }
}
