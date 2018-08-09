package com.yh.ydd.server.print;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("Registered")
public class PrintService extends IntentService {
   private LocalBroadcastManager localBroadcastManager;
   private DynamicReceiver dynamicReceiver;

    public PrintService(){
        super("printProcess1");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        Log.e("DOAING","onHandleIntent");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //注册本地广播接收器（测试））
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        dynamicReceiver = new DynamicReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("cn.programmer.test");
        localBroadcastManager.registerReceiver(dynamicReceiver,filter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //这个方法允许启动多次，创建多个线程，所以可以进行耗时操作
        Log.e("DOAING","启动服务");
        return START_STICKY;

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销掉广播监听
        localBroadcastManager.unregisterReceiver(dynamicReceiver);
        Log.e("DOAING","服务关闭");

    }

    /**
     * 本地动态广播接收测试
     */
    public class DynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("test"), Toast.LENGTH_LONG).show();
        }
    }
}
