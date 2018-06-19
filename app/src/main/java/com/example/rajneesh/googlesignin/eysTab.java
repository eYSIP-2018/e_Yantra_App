package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class eysTab extends Fragment {
    Button register;
    OnRegisterSelectedCallBack callBack;

    // TODO: Rename and change types of parameters



    interface OnRegisterSelectedCallBack{
        void onRegisterSelected(View view);
    }

    public eysTab() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBack = (eysTab.OnRegisterSelectedCallBack) context;
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
        View view= inflater.inflate(R.layout.fragment_eys_tab,container,false);
        register= view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onRegisterSelected(view);
            }
        });
        return view;
    }
}
