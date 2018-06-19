package com.example.rajneesh.googlesignin;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
                    for (int i = 0; i < responses.size(); i++) {
                        String add=responses.get(i).getAddress()+responses.get(i).getDistrict()+responses.get(i).getState()+"India";
                      //  addresses.add(add);
                         String clg_name= responses.get(i).getClg_name();
                       // clg_names.add(clg_name);

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
//                       asyncTask.execute(addresses);
                         getLocationFromAddress(add,clg_name);
                       // clg_names.add(responses.get(i).getClg_name());
                    }
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
}
