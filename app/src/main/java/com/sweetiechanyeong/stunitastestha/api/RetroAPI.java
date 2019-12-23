package com.sweetiechanyeong.stunitastestha.api;


import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import retrofit2.http.Query;

public interface RetroAPI {

    @Headers("Authorization: KakaoAK df5503c44ca2a6089351568a015d32ac")
    @GET("/v2/search/image")
    Call<Example> getData(@Query("query")  String query,  @Query("page") int page);



}// interface

