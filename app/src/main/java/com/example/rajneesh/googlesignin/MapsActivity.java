package com.example.rajneesh.googlesignin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<MapResponse> responses;
    ArrayList<String> clg_names;
    ArrayList<String> addresses;
    ArrayList<LatLng> latslngs;
    MapsOpenHelper openHelper;
    SQLiteDatabase databaseRead;
    SQLiteDatabase databaseWrite;
    String clg_name;
   // LatlongDao lldao;
    //latlngTable obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
       // lldao=AppDatabase.getInstance(MapsActivity.this).getlatlngDao();
        openHelper= new MapsOpenHelper(this);
         databaseRead= openHelper.getReadableDatabase();
         databaseWrite= openHelper.getWritableDatabase();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


       LatLng base = new LatLng(22.9734, 78.6569);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Kerela"));
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(base,4));

        fetchColleges();

    }

    private void fetchColleges(){
        retrofit2.Call<List<MapResponse>> call= APIClient.getInstance().getApi().getMaps();
        responses= new ArrayList<>();
        addresses= new ArrayList<>();
        clg_names= new ArrayList<>();
        call.enqueue(new Callback<List<MapResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<MapResponse>> call, Response<List<MapResponse>> response) {
               // MapResponse response1= response.body();
                responses= response.body();
              //  ArrayList<MapResponse.Response> responses= response1.getResponses();


                if(responses!=null) {
                    Toast.makeText(MapsActivity.this,responses.get(1).getClg_name(),Toast.LENGTH_SHORT).show();
                    latlngAsyncTask[] asyncTask= new latlngAsyncTask[responses.size()];
                    for (int i = 0; i < responses.size(); i++) {
                        String add=responses.get(i).getAddress()+responses.get(i).getDistrict()+responses.get(i).getState()+"India";

                          clg_name= responses.get(i).getClg_name();
                        Mapss m= new Mapss(clg_name,add);

                        asyncTask[i].execute(m);

//                       latlngAsyncTask asyncTask= new latlngAsyncTask(new latlngAsyncTask.latlngListner() {
//                           @Override
//                           public void onlatlngGet(ArrayList<LatLng> latLngs) {
//                               if(latLngs!=null){
//                                   Toast.makeText(MapsActivity.this,"in async return",Toast.LENGTH_SHORT).show();
//                                   for(int i=0;i<latLngs.size();i++){
//                                       mMap.addMarker(new MarkerOptions().position(latLngs.get(i)).title(clg_names.get(i)));
//                                   }
//                               }
//                           }
//                       },MapsActivity.this);

                       //  getLocationFromAddress(add,clg_name);
                       // clg_names.add(responses.get(i).getClg_name());
                    }
                    Toast.makeText(MapsActivity.this,"fuck",Toast.LENGTH_SHORT).show();


                }
            //    Toast.makeText(MapsActivity.this,responses.get(1).getClg_name(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(retrofit2.Call<List<MapResponse>> call, Throwable t) {
                Toast.makeText(MapsActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getLocationFromAddress(String strAddress, String clg_name) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1=null;

        try {
            // May throw an IOException
             address = coder.getFromLocationName(strAddress, 5);
            if (address.isEmpty()) {
                Toast.makeText(MapsActivity.this,"address is null cant find latlong",Toast.LENGTH_SHORT).show();
            }
            else {

                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p1).title(clg_name));
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }




    }

    public class latlngAsyncTask extends AsyncTask<Mapss,Void,ReturnType>{

        @Override
        protected ReturnType doInBackground(Mapss... strings) {
            Geocoder coder = new Geocoder(MapsActivity.this);
            Mapss map= strings[0];
            String addr= map.getAddress();
            String clgname= map.getClg_name();
          //  String addresses= strings[0];
            List<Address> address;
            LatLng p1=null;
            ArrayList<LatLng> latlngs= new ArrayList<>();





                try {
                    // May throw an IOException

                    address = coder.getFromLocationName(addr, 5);
                    if (address.isEmpty()) {
                        //clg_names.remove(i);
                        return null;
                        //Toast.makeText(MapsActivity.this,"address is null cant find latlong",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        Address location = address.get(0);
                        p1 = new LatLng(location.getLatitude(), location.getLongitude());
                        latlngs.add(p1);
                        ReturnType ret= new ReturnType(p1,clgname);
                        return ret;

                    }

                } catch (IOException ex) {

                    ex.printStackTrace();
                }




            return null;

        }

        @Override
        protected void onPostExecute(ReturnType rett) {
            if(rett!=null) {

               //ArrayList<latlngTable> list = (ArrayList)lldao.getall();
                LatLng latLng= rett.getLatLng();
                String clgname= rett.getClg_name();
                String[] Tablecol={"clg_name"};
                String[] SelectionArgs={latLng.latitude+"",latLng.longitude+""};
                String clgnm=null;
                Cursor cursor= databaseRead.query("MapsTable",Tablecol,"lat=? AND long=?",SelectionArgs,null,null,null);
                while(cursor.moveToNext()){
                     clgnm= cursor.getString(cursor.getColumnIndex("clg_name"));
                }
              // String clgname= lldao.getclgname(latLng.latitude,latLng.longitude);

                if (clgnm!=null) {
                    Toast.makeText(MapsActivity.this, "db fetch" , Toast.LENGTH_SHORT).show();
                    String[] clms= {"lat","long"};
                    String[] selctnarg={clgnm+""};
                    Double latitude=null,longitude=null;
                    Cursor cursor1= databaseRead.query("MapsTable",clms,"clg_name=?",selctnarg,null,null,null);
                    while (cursor1.moveToNext()){
                        latitude= cursor1.getDouble(cursor1.getColumnIndex("lat"));
                        longitude= cursor1.getDouble(cursor1.getColumnIndex("long"));
                    }
                   // Double[] latlng=lldao.getlatlng(clgname);
                        LatLng ll= new LatLng(latitude,longitude);
                        mMap.addMarker(new MarkerOptions().position(ll).title(clgnm));

                } else {

                        Toast.makeText(MapsActivity.this, "in async return", Toast.LENGTH_SHORT).show();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("clg_name",clg_name);
                    contentValues.put("lat",latLng.latitude);
                    contentValues.put("long",latLng.longitude);
                   long sno= databaseWrite.insert("MapsTable",null,contentValues);
                       // obj = new latlngTable(clgname,latLng.latitude,latLng.longitude);
                        //lldao.insertLatlng(obj);
                        mMap.addMarker(new MarkerOptions().position(latLng).title(clgname));
                    }

            }

            super.onPostExecute(rett);
        }
    }

    class ReturnType{
        LatLng latLng;
        String clg_name;

        public LatLng getLatLng() {
            return latLng;
        }

        public void setLatLng(LatLng latLng) {
            this.latLng = latLng;
        }

        public String getClg_name() {
            return clg_name;
        }

        public void setClg_name(String clg_name) {
            this.clg_name = clg_name;
        }

        public ReturnType(LatLng latLng, String clg_name) {
            this.latLng = latLng;
            this.clg_name = clg_name;
        }
    }

}
