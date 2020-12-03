package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataRestArea {
    @GET("locationinfoRest?")
    Call<LoadDataRestArea> getInstanceRestArea(@Query("key") String key, @Query("type") String type,@Query("numOfRows")String count, @Query("pageNo")String page);
}
