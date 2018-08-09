package com.yh.ydd.database.factory.couchebase;

import android.support.annotation.NonNull;

import com.couchbase.lite.Authenticator;
import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.SessionAuthenticator;
import com.couchbase.lite.URLEndpoint;
import com.couchbase.lite.internal.support.Log;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.MyReplicatorChangeListener;
import com.yh.ydd.database.config.DatabaseURLCollector;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CoucheBaseUtils extends AbDatabaseUtils {

    private URI uri;

    private Replicator replication;

    private Database database;

    private MyReplicatorChangeListener myReplicatorChangeListener;

    protected CoucheBaseUtils(Database database) {
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
    public void startReplication(String sessionId) {



        //发起http请求
        if (uri == null) {

            try {
                uri = new URI(DatabaseURLCollector.COUCHEBASAURL);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Endpoint endpoint = new URLEndpoint(uri);

            ReplicatorConfiguration config = new ReplicatorConfiguration(database, endpoint);

            config.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);

            Authenticator authenticator;

            authenticator = new SessionAuthenticator(sessionId);

            config.setAuthenticator(authenticator);

            replication = new Replicator(config);

            replication.addChangeListener(new ReplicatorChangeListener() {
                @Override
                public void changed(ReplicatorChange change) {

                    try {
                        myReplicatorChangeListener.changed(change);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        replication.start();
    }

    @Override
    public void closeDatabase() {
        if (database != null) {
            try {
                database.close();
            } catch (CouchbaseLiteException e) {
                Log.e(TAG, "Failed to close Database", e);
            }
        }
    }

}
