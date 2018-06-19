package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


public class elsiTab extends Fragment {

        Button register, labs;
        OnRegisterSelectedCallBack callBack;

        ImageButton play;



    // TODO: Rename and change types of parameters


        interface OnRegisterSelectedCallBack {
            void onRegisterSelected(View view);
        }

        public elsiTab() {
            // Required empty public constructor
        }

        public void onAttach(Context context) {
            super.onAttach(context);
            try {
                callBack = (elsiTab.OnRegisterSelectedCallBack) context;
            } catch (ClassCastException e) {
                throw new ClassCastException("Activity should implement UserSelectedCallback");
            }

        }


        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_elsi_tab, container, false);
            register = view.findViewById(R.id.register);
            play = view.findViewById(R.id.play);
            labs= view.findViewById(R.id.alllabs);

            labs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(getContext(),MapsActivity.class);
                    startActivity(intent);
                }
            });

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(getContext(),YoutubePlayerActivity.class);
                    startActivity(intent);
                }
            });


            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onRegisterSelected(view);
                }
            });
            return view;
        }
    }

