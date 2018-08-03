package com.yh.ydd.database.config;

import android.support.annotation.NonNull;

import com.couchbase.lite.Database;

import java.util.Objects;

public abstract class AbDatabaseUtils {
    public abstract <T> void save(@NonNull T t);
    public abstract <T> Object selectById(@NonNull T t);

    public abstract void openReplicatorChangeListener(Object o);

    /**
     * 开启同步服务
     */
    public abstract void startReplication();

}
