package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_RestArea {
    private static final String baseUrl ="https://data.ex.co.kr/openapi/locationinfo/";
    private static Retrofit retrofit = null;

    private Retrofit_RestArea(){}

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
