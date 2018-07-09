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
    @GET("server/public/maps")
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
    Call<Response> putPost( @Field("username") String username,@Field("Post") String post,@Field("photo") String photo);

    @GET("server/public/showpost")
    Call<List<PostsResponse>> getPosts();

    @POST("server/public/comment")
    @FormUrlEncoded
    Call<ResponseBody> putComments(@Field("username")String username, @Field("Comments") String comment,@Field("Pid") int postid,@Field("photo") String photo);

    @POST("server/public/newscom")
    @FormUrlEncoded
    Call<ResponseBody> putfeedComments(@Field("username")String username, @Field("Comments") String comment,@Field("Fid") int feedid);

    @POST("server/public/commentshow")
    @FormUrlEncoded
    Call<List<CommentResponse>> getComments(@Field("pid") int postid);

    @POST("server/public/newscomshow")
    @FormUrlEncoded
    Call<List<CommentnewsResponse>> getfeedcom(@Field("Fid") int fid);

    @POST("server/public/news")
    @FormUrlEncoded
    Call<ResponseBody> putfeed(@Field("username") String username,@Field("desc") String description);

    @GET("server/public/newshow")
    Call<List<NewsFeedResponse>> getfeeds();

    @GET("map.json")
    Call<List<mapclgResponse>> getclgs();

    @POST("server/public/commentcount")
    @FormUrlEncoded
    Call<ResponseBody> getpostcount(@Field("pid") int pid);

    @POST("server/public/profile")
    @FormUrlEncoded
    Call<Response> putprofile (@Field("Profile_Pic") String photo,@Field("name") String name,@Field("college") String college,@Field("email") String email,@Field("phone") String phone,@Field("job") String job,@Field("company") String company,@Field("year") String year,@Field("branch") String branch,@Field("type") String type);

    @POST("server/public/profshow")
    @FormUrlEncoded
    Call<List<ProfileResponse>> getprofile(@Field("unique") String uniqueid);


    @POST("server/public/newscount")
    @FormUrlEncoded
    Call<Response> getnewscomno(@Field("Fid") int id);

}

