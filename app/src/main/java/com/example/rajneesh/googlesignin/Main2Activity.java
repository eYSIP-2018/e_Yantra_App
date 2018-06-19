package com.example.rajneesh.googlesignin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, efsiTab.OnRegisterSelectedCallBack
        , elsiTab.OnRegisterSelectedCallBack, eyrcTab.OnRegisterSelectedCallBack, eyrdcTab.OnRegisterSelectedCallBack
        , eysTab.OnRegisterSelectedCallBack{

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    private final static String pageTitle[]= {"eFSI","eLSI","eYRC","eYS","eYRDC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("e-yantra");




        viewPager= findViewById(R.id.viewpager);
        adapter= new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new efsiTab(),pageTitle[0]);
        adapter.AddFragment(new elsiTab(),pageTitle[1]);
        adapter.AddFragment(new eyrcTab(),pageTitle[2]);
        adapter.AddFragment(new eysTab(),pageTitle[3]);
        adapter.AddFragment(new eyrdcTab(),pageTitle[4]);
        viewPager.setAdapter(adapter);
        tabLayout= findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View view= navigationView.getHeaderView(0);
        TextView name= view.findViewById(R.id.navname);
        TextView email= view.findViewById(R.id.navemail);
        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        Log.d("bundle main",bundle.getString("name"));
        String nm=bundle.getString("name");
        String em= bundle.getString("email");
        name.setText(nm);
        email.setText(em);

//        BackgroundTask backgroundTask= new BackgroundTask(this);
//        backgroundTask.execute("abc",nm,em);
        navigationView.setNavigationItemSelectedListener(this);


        //photo= findViewById(R.id.navphoto);
       // Name= findViewById(R.id.navname);
       // email= findViewById(R.id.navemail);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Team) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent= new Intent(this,GalleryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contactus) {

        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_publications) {

        } else if (id == R.id.nav_news) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onRegisterSelected(View view) {
        Intent intent= new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginSelected(View view) {
        Intent intent= new Intent(this,LginAuthActivity.class);
        startActivity(intent);
    }
}
