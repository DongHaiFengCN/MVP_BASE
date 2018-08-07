package com.example.login;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yh.ydd.base.print.IPrintConfig;
import com.yh.ydd.base.print.PrintManager;

@SuppressLint("Registered")
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent();
        intent.putExtra("test","董海峰");
        intent.setAction("cn.programmer.test");
        localBroadcastManager.sendBroadcast(intent);

    }


}
