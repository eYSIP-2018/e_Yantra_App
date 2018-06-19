package com.example.rajneesh.googlesignin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LginAuthActivity extends AppCompatActivity {
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin_auth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username= findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user,pass,method="auth";
                user= username.getText().toString();
                pass= password.getText().toString();
              BackgroundTask backgroundTask= new BackgroundTask(LginAuthActivity.this);backgroundTask.execute(method,user);
                BackgroundTask gettask= new BackgroundTask(LginAuthActivity.this);
                gettask.execute("abc");
            }
        });


    }

}
