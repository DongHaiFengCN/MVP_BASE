package com.yh.ydd.server.mvp;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.yh.ydd.database.config.AbDataBase;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.DataBaseFactory;
import com.yh.ydd.database.config.MyDatabaseFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础初始化的application
 */
public class BaseApplication extends Application {

    private DataBaseFactory dataBaseFactory;
    ///private HashMap<String, AbDataBase> dataMap;

    private AbDataBase abDataBase;
    private AbDatabaseUtils abDatabaseUtils;
    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("DOAING", "APPLICATION启动了");

        initLeakCanary();

       // dataMap = new HashMap<>();

        dataBaseFactory = MyDatabaseFactory.getInstant();


    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.e("DOAING", "APPLICATION关闭了");
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


    /**
     *
     * @param t 新数据库
     */
    public <T extends AbDataBase> AbDatabaseUtils createCurrentDatabase(Class<T> t) {


        if (abDataBase == null) {

            abDataBase = dataBaseFactory.createDatabase(t, this);

            abDatabaseUtils = abDataBase.getDatabaseUtilsInstant();
        }

        return abDatabaseUtils;


    }

}
