package com.example.login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BlogService {
    @GET("doaing")
    Call<ResponseBody> getBlog();
}
