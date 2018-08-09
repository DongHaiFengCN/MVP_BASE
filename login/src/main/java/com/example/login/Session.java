package com.example.login;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Session {
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("_session")
    Call<ResponseBody> getSession(@Body SessionRequest sessionRequest);
}
