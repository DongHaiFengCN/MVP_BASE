package com.yh.ydd.database.factory.couchebase;

import android.support.annotation.NonNull;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.URLEndpoint;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.MyReplicatorChangeListener;

import java.net.URI;
import java.net.URISyntaxException;

import static android.content.ContentValues.TAG;

public class CoucheBaseUtils extends AbDatabaseUtils {

    private static CoucheBaseUtils coucheBaseUtils;

    private Database database;

    private MyReplicatorChangeListener myReplicatorChangeListener;

    private CoucheBaseUtils(Database database) {
        this.database = database;
    }

    @Override
    public <T> void save(@NonNull T t) {

        if (database != null) {

            MutableDocument mutableDocument = (MutableDocument) t;
            try {
                database.save(mutableDocument);
            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }

        } else {

            throw new RuntimeException("没有初始化数据库");
        }


    }


    @Override
    public <T> Document selectById(@NonNull T t) {

        database.getDocument(t.toString());

        return database.getDocument(t.toString());
    }

    @Override
    public void openReplicatorChangeListener(Object o) {

        this.myReplicatorChangeListener = (MyReplicatorChangeListener) o;
    }

    @Override
    public void startReplication() {

        URI uri = null;
        try {
            uri = new URI("ws://localhost:4984/db");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Endpoint endpoint = new URLEndpoint(uri);
        ReplicatorConfiguration config = new ReplicatorConfiguration(database, endpoint);
        config.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PULL);
        Replicator replication = new Replicator(config);
        replication.addChangeListener(new ReplicatorChangeListener() {
            @Override
            public void changed(ReplicatorChange change) {

                myReplicatorChangeListener.changed(change);
                }
        });
        replication.start();

    }


    public static CoucheBaseUtils getInstant(Database database){

        if(coucheBaseUtils == null){

            coucheBaseUtils = new CoucheBaseUtils(database);
        }

        return coucheBaseUtils;
    }



}
