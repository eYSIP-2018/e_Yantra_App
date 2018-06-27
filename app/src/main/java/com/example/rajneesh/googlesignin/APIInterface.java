package com.example.rajneesh.googlesignin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("dummy/new.php")
    Call<List<MapResponse>> getMaps();
    @POST("dummy/login.php")
    @FormUrlEncoded
    Call<Response> getResponse( @Field("username") String name, @Field("password") String pass);
    @GET("dummy/youtube.php")
    Call<List<YoutubeVideoResponse>> getVideos();
    @GET("dummy/calender.php")
    Call<List<CalenderResponse>> getEvents();

}
