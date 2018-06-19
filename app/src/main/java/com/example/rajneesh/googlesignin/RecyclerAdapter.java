package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    int height,width;
    interface OnItemClickListner{
        void OnItemClicked(int position);
    }


    Context context;
    OnItemClickListner listner;

    public RecyclerAdapter( Context context, OnItemClickListner listener, ArrayList<Integer> images, WindowManager windowManager) {

        this.context = context;
        this.listner = listener;
        this.images = images;
        this.windowManager = windowManager;
    }

    ArrayList<Integer> images;
    WindowManager windowManager;



    public void getScreenSize(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height=displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView= inflater.inflate(R.layout.row,parent,false);
        ViewHolder holder= new ViewHolder(itemView);
        getScreenSize();
        return holder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        int image= images.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnItemClicked(holder.getAdapterPosition());

            }
        });
        Picasso.get().load(image).resize(width,0).into(holder.poster);
        // Glide.with(context).load("http://image.tmdb.org/t/p/original"+movie.getBackdrop_path()).into(holder.poster);



    }




    @Override
    public int getItemCount() {
        return images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView= itemView;

            poster= itemView.findViewById(R.id.poster);


        }
    }
}
