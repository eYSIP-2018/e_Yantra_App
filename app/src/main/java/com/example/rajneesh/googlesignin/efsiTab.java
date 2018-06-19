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



public class efsiTab extends Fragment {


    Button register,login;
    OnRegisterSelectedCallBack callBack;

    // TODO: Rename and change types of parameters



    interface OnRegisterSelectedCallBack{
        void onRegisterSelected(View view);
        void onLoginSelected(View view);
    }

    public efsiTab() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBack = (efsiTab.OnRegisterSelectedCallBack) context;
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
        View view= inflater.inflate(R.layout.fragment_efsi_tab,container,false);
        register= view.findViewById(R.id.register);
        login= view.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onLoginSelected(view);
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
