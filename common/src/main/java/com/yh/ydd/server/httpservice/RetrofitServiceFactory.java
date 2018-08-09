package com.yh.ydd.server.httpservice;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建网络访问工厂
 */
public class RetrofitServiceFactory {

    private static Retrofit retrofit;

    private static String URL;

    /**
     *
     * @param url 需要链接的基础地址，如果是新的就返回新的，如果不是就返回之前创建的
     * @return 返回一个retrofit实例
     */
    public static Retrofit createRetrofitByBaseUrl(String url) {

        if (url != null && url.equals(URL)) {

            return retrofit;
        }
        assert url != null;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        URL = url;

        return retrofit;
    }

    /**
     * @return 直接返回一个retrofit实例
     */
    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            throw new RuntimeException("retrofit是空的!");

        }
        return retrofit;
    }

}
