package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

/**
 * Created by RAJNEESH on 15-06-2018.
 */

public class ImageSliderAdapter extends PagerAdapter {
    Context context;
    int height,width;
    WindowManager windowManager;
    LayoutInflater layoutInflater;
    Integer[] images = {R.drawable.gal11, R.drawable.gal13, R.drawable.gal14, R.drawable.ey1, R.drawable.ey2, R.drawable.ey4, R.drawable.ey5};

    public ImageSliderAdapter(Context context,WindowManager windowManager) {
        this.context = context;
        this.windowManager=windowManager;
    }


    public void getScreenSize(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height=(displayMetrics.heightPixels)/2;
        width=displayMetrics.widthPixels;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout, container,false);
        ImageView imageView = view.findViewById(R.id.image);
        Glide.with(context).load(images[position]).override(600,500).into(imageView);
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        ViewPager viewPager= (ViewPager)container;
//        View view= (View) object;
//        viewPager.removeView(view);
        container.removeView((LinearLayout)object);
    }

}