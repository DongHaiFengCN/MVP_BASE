package com.yh.ydd.base.config;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.yh.ydd.database.config.AbDataBase;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.DataBaseFactory;
import com.yh.ydd.database.config.MyDatabaseFactory;

import java.util.HashMap;

/**
 * 基础初始化的application
 */
public class BaseApplication extends Application {

    private DataBaseFactory dataBaseFactory;
    private HashMap<String, AbDataBase> dataMap;


    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCanary();

        dataMap = new HashMap<>();

        dataBaseFactory = MyDatabaseFactory.getInstant();


    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


    /**
     * 设置新的数据库
     *
     * @param t 新数据库
     */
    public <T extends AbDataBase> AbDatabaseUtils getCurrentDatabase(Class<T> t) {

        AbDataBase abDataBase = dataMap.get(t.getName());

        if (abDataBase == null) {

            abDataBase = dataBaseFactory.createDatabase(t, this);

            dataMap.put(t.getName(), abDataBase);
        }

        return abDataBase.getDatabaseUtilsInstant();


    }


}
