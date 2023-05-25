package com.example.myapplication.api;

import com.example.myapplication.models.UserDetail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebApi {

    String BASE_URL = "http://192.168.165.183/fyp/api/" ;

    @POST("db/login")
    public Call<UserDetail> logIn(@Body UserDetail u);

    @POST("db/signup")
    public Call<String> signup(@Body UserDetail user);

    @GET("db/getallusers")
    public Call<ArrayList<UserDetail>> getAllUsers();


    @POST("fyp/getlist")
    public Call<ArrayList<String>> getList();


    @GET("Math/add")
    public Call<Integer> sum(
            @Query("n1") int a1,
            @Query("n2") int a2
    );
}
