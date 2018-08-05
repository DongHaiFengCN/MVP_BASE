package com.example.login;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.ReplicatorChange;
import com.yh.ydd.base.config.BasePresenter;
import com.yh.ydd.base.config.ILifecyclePresenter;
import com.yh.ydd.base.httpservice.RetrofitServiceFactory;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.MyReplicatorChangeListener;
import com.yh.ydd.database.factory.couchebase.CoucheBaseInstant;
import com.yh.ydd.utils.Untils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter extends BasePresenter implements ILifecyclePresenter {
    private AbDatabaseUtils currentDatabaseUtils;


    public void submit(String name, String age) {


        currentDatabaseUtils.startReplication("b8b2f10e", "123456");

    }

    public void httpTest() {

        //获取网络地址
        String url = Untils.getStringFromRaw(getBaseApplication(), R.raw.base_http_url);
       Retrofit retrofit = RetrofitServiceFactory.createRetrofitByBaseUrl(url);

       BlogService blogService = retrofit.create(BlogService.class);
       Call<ResponseBody> call = blogService.getBlog();

       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {

                   Log.e("DOAING",response.body().string());


               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });

    }

    /**
     * 监听生命周期，可以初始化一下东西
     */
    @Override
    public void onCreate() {

        //这里开启并注册指定的数据库，例如couchebase，如果已经开启就直接返回当前数据库的操作工具。
        currentDatabaseUtils = getBaseApplication().getCurrentDatabase(CoucheBaseInstant.class);

        Objects.requireNonNull(currentDatabaseUtils, "数据库操作对象不能为空!!!");

        currentDatabaseUtils.openReplicatorChangeListener(new MyReplicatorChangeListener() {
            @Override
            public void changed(Object change) {
                ReplicatorChange replicatorChange = (ReplicatorChange) change;

                Log.i("DOAING", "同步：" + replicatorChange.getStatus().toString());

                CouchbaseLiteException error = replicatorChange.getStatus().getError();

                if (error != null) {

                    com.couchbase.lite.internal.support.Log.w("DOAING", "Error code:: %d", error.getCode());

                }

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
