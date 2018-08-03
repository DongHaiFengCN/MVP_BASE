package com.example.login;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
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

        MutableDocument mutableDoc = new MutableDocument();
        mutableDoc.setString("name", name)
                .setString("age", age);

        currentDatabaseUtils.save(mutableDoc);
        currentDatabaseUtils.startReplication();

        currentDatabaseUtils.openReplicatorChangeListener(new MyReplicatorChangeListener() {
            @Override
            public void changed(Object change) {

                ReplicatorChange replicatorChange = (ReplicatorChange) change;
                Log.e("DOAING",  replicatorChange.getStatus().toString());

            }


        });


        Document document = (Document) currentDatabaseUtils.selectById(mutableDoc.getId());

        Log.e("DOAING", document.getString("name"));
        Log.e("DOAING", document.getString("age"));
    }

    /**
     * 监听生命周期，可以初始化一下东西
     */
    @Override
    public void onCreate() {

        //这里开启并注册指定的数据库，例如couchebase，如果已经开启就直接返回当前数据库的操作工具。
        currentDatabaseUtils = getBaseApplication().getCurrentDatabase(CoucheBaseInstant.class);

        Objects.requireNonNull(currentDatabaseUtils, "数据库操作对象不能为空!!!");

    }

    @Override
    public void onDestroy() {

        Log.e("DOAING", "onDestroy");


    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }
}
