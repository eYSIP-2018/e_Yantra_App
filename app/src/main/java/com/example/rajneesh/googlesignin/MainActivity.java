package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
     SignInButton signIn;
     ViewFlipper viewFlipper;
     GoogleApiClient googleApiClient;
     LinearLayout profile;
     int image[]= {R.drawable.ey1,R.drawable.ey2,R.drawable.ey5,R.drawable.ey4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences= getSharedPreferences("googlesignin", Context.MODE_PRIVATE);
        String name= sharedPreferences.getString("name","");
        String email= sharedPreferences.getString("email","");
        if(name!=null && email!=null){
            Intent intent= new Intent(this,Main2Activity.class);
           // Intent intent= new Intent(this,GalleryActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString("name",name);
            bundle.putString("email",email);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn= findViewById(R.id.signInButton);
        viewFlipper= findViewById(R.id.viewflipper);

        for(int i=0;i<image.length;i++){
            flipimages(image[i]);
        }
        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,1);


            }
        });






    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Check your Internet Connection",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account= result.getSignInAccount();
                String name= account.getDisplayName();
                String email= account.getEmail();
                String method="signup";
                BackgroundTask backgroundTask= new BackgroundTask(this);
                backgroundTask.execute(method,email,name);



                SharedPreferences sharedPreferences= getSharedPreferences("googlesignin",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("email",email);
                editor.apply();

                Intent intent= new Intent(this,Main2Activity.class);
                Bundle bundle= new Bundle();
                bundle.putString("name",name);
                bundle.putString("email",email);
                intent.putExtras(bundle);
                startActivity(intent);

             //   String imgurl= account.getPhotoUrl().toString();
             //   Name.setText(name);
             //   Glide.with(this).load(imgurl).into(photo);
             //   profile.setVisibility(View.VISIBLE);
               // signIn.setVisibility(View.GONE);



            }
            else{
                profile.setVisibility(View.GONE);
                signIn.setVisibility(View.VISIBLE);
            }
        }
    }

    public  void flipimages(int image){
        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
      //  viewFlipper.setFlipInterval(2000);

        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}
