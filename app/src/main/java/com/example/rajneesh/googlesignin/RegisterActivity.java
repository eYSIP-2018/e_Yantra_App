package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText e_name, e_email, e_password,e_confirm_pass;
    String name,email,password,password_confirmation;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(this,"done",Toast.LENGTH_LONG).show();

        e_name=findViewById(R.id.name);
        e_email=findViewById(R.id.email);
        e_confirm_pass= findViewById(R.id.confirm_password);
        e_password=findViewById(R.id.password);
        register= findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=e_name.getText().toString();
                email= e_email.getText().toString();
                password=e_password.getText().toString();
                password_confirmation=e_confirm_pass.getText().toString();

                Call<String> call= APIClient.getInstance().getApi().getRegisterResult(name,email,password,password_confirmation);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String response1= response.body();
                        Toast.makeText(RegisterActivity.this,response1,Toast.LENGTH_LONG);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


//                String method="register";
//                BackgroundTask backgroundTask=new BackgroundTask(RegisterActivity.this);
//                backgroundTask.execute(method,name,branch,clg,phone,username,password);


                finish();

            }
        });
       // radioGroup= findViewById(R.id.radioGroup);
    }




}
