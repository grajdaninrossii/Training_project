package com.example.samsung55;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("user/")
    Call<List<Model>> getUser();

    @PATCH("user/")
    Call<Model> setUser(@Query("name") String name,
                        @Query("lastname") String lastname,
                        @Query("classname") String classname);
}
