package com.example.login;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.ReplicatorChange;
import com.yh.ydd.base.config.BasePresenter;
import com.yh.ydd.base.config.ILifecyclePresenter;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.MyReplicatorChangeListener;
import com.yh.ydd.database.factory.couchebase.CoucheBaseInstant;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginPresenter extends BasePresenter implements ILifecyclePresenter {
    private AbDatabaseUtils currentDatabaseUtils;

    public void submit(String name, String age) {

        Log.e("DOAING",name+age);

        currentDatabaseUtils.save(new MutableDocument());

    }

    /**
     * 监听生命周期，可以初始化一下东西
     */
    @Override
    public void onCreate() {




        //这里开启并注册指定的数据库，例如couchebase，如果已经开启就直接返回当前数据库的操作工具。
        currentDatabaseUtils = getBaseApplication().getCurrentDatabase(CoucheBaseInstant.class);

        Objects.requireNonNull(currentDatabaseUtils, "数据库操作对象不能为空!!!");
        currentDatabaseUtils.startReplication().openReplicatorChangeListener(new MyReplicatorChangeListener() {
            @Override
            public void changed(Object change) {
                ReplicatorChange replicatorChange = (ReplicatorChange) change;
                Log.e("DOAING",  "同步信息："+replicatorChange.getStatus().toString());
            }
        });
    }

    @Override
    public void onDestroy() {

        Log.e("DOAING", "onDestroy");


    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }
}
