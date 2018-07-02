package com.example.rajneesh.googlesignin;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {
    @GET("dummy/new.php")
    Call<List<MapResponse>> getMaps();
    @POST("dummy/login.php")
    @FormUrlEncoded
    Call<Response> getResponse( @Field("username") String name, @Field("password") String pass);
    @GET("server/public/youtube")
    Call<List<YoutubeVideoResponse>> getVideos();
    @GET("dummy/calender.php")
    Call<List<CalenderResponse>> getEvents();
    @GET("server/public/tutorials")
    Call<List<VideoTutorialResponse>> getvideoTuts();
    @GET("server/public/projects")
    Call<List<YoutubeVideoResponse>> getProjectVideos();
    @POST("server/public/image")
    @FormUrlEncoded
    Call<Response> getImageuploadResponse(@Field("image")byte[] photo);

    @POST("server/public/sign")
    @FormUrlEncoded
    Call<ResponseBody> gettesting(@Field("name")String name,@Field("email")String email);


    @Multipart
    @POST("server/public/image")
    Call<ResponseBody> uploadphoto(@Part MultipartBody.Part photo);


    @POST("server/public/post")
    @FormUrlEncoded
    Call<Response> putPost( @Field("username") String username,@Field("Post") String post);

    @GET("server/public/showpost")
    Call<List<PostsResponse>> getPosts();

    @POST("server/public/comment")
    @FormUrlEncoded
    Call<ResponseBody> putComments(@Field("username")String username, @Field("Comments") String comment,@Field("Pid") int postid);

    @POST("server/public/commentshow")
    @FormUrlEncoded
    Call<List<CommentResponse>> getComments(@Field("pid") int postid);

}
