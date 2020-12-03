package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataHighway {
    @GET("trafficAmountByCongest?")
    Call<LoadDataHighway>getInstanceHighway(@Query("key") String key,@Query("type") String type);
}
