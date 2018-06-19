package com.example.rajneesh.googlesignin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("colleges_json.json")
    Call<List<MapResponse>> getMaps();
    @POST("login/public/register")
    Call<String> getRegisterResult(String name, String email,String password,String password_confirmation);
}
