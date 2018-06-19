package com.example.rajneesh.googlesignin;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class photoviewerActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageSliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoviewer);
        viewPager= findViewById(R.id.viewpager);
        adapter = new ImageSliderAdapter(this,getWindowManager());
        viewPager.setAdapter(adapter);


    }
}