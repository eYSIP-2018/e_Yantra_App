package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsfeedRecyclerAdapter extends RecyclerView.Adapter<NewsfeedRecyclerAdapter.ViewHolder>{

    int height,width;
    String comno;
    public interface OnItemClickListner{
        void OnItemClicked(int position);
        void OnCommentSelected(int position);
        String getid(int position);

    }

    Context context;
    ArrayList<NewsFeedResponse> feeds;
    NewsfeedRecyclerAdapter.OnItemClickListner listner;
    WindowManager windowManager;

    public NewsfeedRecyclerAdapter(Context context, ArrayList<NewsFeedResponse> feeds, NewsfeedRecyclerAdapter.OnItemClickListner listner, WindowManager windowManager) {
        this.context = context;
        this.feeds = feeds;
        this.listner = listner;
        this.windowManager=windowManager;
    }

    public void getScreenSize(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height=displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
    }

    @Override
    public NewsfeedRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView= inflater.inflate(R.layout.newsfeed_row,parent,false);
        NewsfeedRecyclerAdapter.ViewHolder holder= new NewsfeedRecyclerAdapter.ViewHolder(itemView);
       // Toast.makeText(context, "blablabla", Toast.LENGTH_SHORT).show();

        getScreenSize();
        return holder;

    }

    @Override
    public void onBindViewHolder(final NewsfeedRecyclerAdapter.ViewHolder holder, int position) {

        NewsFeedResponse feed= feeds.get(position);
        holder.username.setText("By~ "+feed.getUsername());
      //  Toast.makeText(context, feed.getUsername(), Toast.LENGTH_SHORT).show();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnItemClicked(holder.getAdapterPosition());

            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnCommentSelected(holder.getAdapterPosition());
            }
        });
        comno= listner.getid(holder.getAdapterPosition());

           holder.commentno.setText(comno);

        holder.feeddesc.setText(feed.getDesc());
        holder.title.setText(feed.getTitle());





        //Picasso.get().load(.getPhoto()).resize(width,0).into(holder.photo);
        Glide.with(context).load(feed.getPhotolink()).into(holder.postpic);



    }




    @Override
    public int getItemCount() {
        return (feeds==null)?0:feeds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView title;
        TextView feeddesc;
        ImageView postpic;
        ImageView photo;
        Button comment;
        TextView commentno;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView= itemView;
            username= itemView.findViewById(R.id.username);
            photo= itemView.findViewById(R.id.picture);
            feeddesc= itemView.findViewById(R.id.feeddesc);
            postpic= itemView.findViewById(R.id.postpic);
            title= itemView.findViewById(R.id.title);
            comment= itemView.findViewById(R.id.comment);
            commentno= itemView.findViewById(R.id.commentno);

        }
    }
}
