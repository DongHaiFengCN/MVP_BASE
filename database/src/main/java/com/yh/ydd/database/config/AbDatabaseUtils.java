package com.yh.ydd.database.config;

import android.support.annotation.NonNull;

import com.yh.ydd.database.factory.couchebase.CoucheBaseUtils;

public abstract class AbDatabaseUtils {
    public abstract <T> void save(@NonNull T t);
    public abstract <T> Object selectById(@NonNull T t);

    public abstract void openReplicatorChangeListener(Object o);

    /**
     * 开启同步服务
     */
    public abstract CoucheBaseUtils startReplication();

    public abstract void closeDatabase();

}
