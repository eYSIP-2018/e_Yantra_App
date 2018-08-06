package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rajneesh.googlesignin.Activities.PostsActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;


public class PostsFragment extends Fragment {
    ArrayList<PostsResponse> posts;
    RecyclerView recyclerView;
    PostsRecyclerAdapter adapter;
    String pref,photo;
    SharedPreferences  sharedPreferences1;



    public PostsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_posts, container, false);
        posts= new ArrayList<>();
        recyclerView= view.findViewById(R.id.postlist);

        sharedPreferences1= getActivity().getSharedPreferences("choiceSelected",Context.MODE_PRIVATE);
        pref= sharedPreferences1.getString("optionSelected","null");

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("googlesignin", Context.MODE_PRIVATE);
        final String name= sharedPreferences.getString("name","");
        photo= sharedPreferences.getString("photo","");
        if(photo==null){
            photo= "https://www.daviker.co.uk/wp-content/uploads/profile-blank.png";
        }

//        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (pref.equals("null")) {
//                    Log.d("prefget", pref);
//                    AlertDialog alertDialog = new AlertDialog.Builder(PostsActivity.this).create();
//                    alertDialog.setMessage("Please fill the form to enable posting...");
//                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Fill Form", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            display_form();
//                        }
//                    });
//                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
//                        }
//                    });
//                    alertDialog.show();
//                } else {
//
//                    view1 = inflater.inflate(R.layout.newsfeed_alertdialog_layout, null);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(PostsActivity.this);
//                    builder.setView(view1);
//
//                    builder.setPositiveButton("Post", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            feedtext = view1.findViewById(R.id.feedText);
//                            String feed = feedtext.getText().toString();
//                            Call<Response> call = APIClient.getInstance().getApi().putPost(name, feed,photo);
//                            call.enqueue(new Callback<Response>() {
//                                @Override
//                                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                                    Response response1= response.body();
//                                    //   Toast.makeText(PostsActivity.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onFailure(Call<Response> call, Throwable t) {
//                                    Toast.makeText(PostsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                            fetchPosts();
//                        }
//                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
//                        }
//                    }).show();
//
//                }
//            }
//        });

        return view;
    }







}
