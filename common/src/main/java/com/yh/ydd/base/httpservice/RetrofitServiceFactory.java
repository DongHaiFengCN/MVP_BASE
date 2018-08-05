package com.yh.ydd.base.httpservice;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建网络访问工厂
 */
public class RetrofitServiceFactory {

    private static Retrofit retrofit;

    public static Retrofit createRetrofitByBaseUrl(String url) {

      Log.e("DOAING",url);

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }

}
