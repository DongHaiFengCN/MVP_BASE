package com.yh.ydd.database.factory.couchebase;

import android.content.Context;
import android.os.Environment;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.URLEndpoint;
import com.yh.ydd.database.config.AbDataBase;
import com.yh.ydd.database.config.AbDatabaseUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 初始化couchebase 数据库实例
 */
public class CoucheBaseInstant extends AbDataBase {


    private Database database = null;


    public CoucheBaseInstant(Context context){

        initDataBase(context);

    }
    @Override
    public void initDataBase(Context context) {

        DatabaseConfiguration config = new DatabaseConfiguration(context);

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            File file = new File(String.format("%s/kitchen",Environment.getExternalStorageDirectory()));

            config.setDirectory(file.getAbsolutePath());
        }
        try {

             database = new Database("kitchendb", config);


        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


    }

    /**
     *  返回当前数据的操作对象
     * @return 操作对象
     */
    @Override
    public AbDatabaseUtils getDatabaseUtilsInstant() {
        return  CoucheBaseUtils.getInstant(database);
    }


}
