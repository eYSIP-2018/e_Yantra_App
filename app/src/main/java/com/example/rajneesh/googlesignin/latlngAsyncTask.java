package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class latlngAsyncTask extends AsyncTask<ArrayList<String>,Void,ArrayList<LatLng>> {



    public interface latlngListner {
        void onlatlngGet(ArrayList<LatLng> latLngs);
    }

    private latlngListner listner;
    private Context context;

    public latlngAsyncTask(latlngListner listner, Context context) {
        this.listner = listner;
        this.context = context;
    }

    @Override
    protected ArrayList<LatLng> doInBackground(ArrayList<String>... arrayLists) {
        Geocoder coder = new Geocoder(context);
        ArrayList<String> addresses= arrayLists[0];
        List<Address> address;
        LatLng p1=null;
        ArrayList<LatLng> latlngs= new ArrayList<>();


        for(int i=0;i<addresses.size();i++)
        {
            String strAddress= addresses.get(i);

        try {
            // May throw an IOException

            address = coder.getFromLocationName(strAddress, 5);
            if (address.isEmpty()) {
                Toast.makeText(context,"address is null cant find latlong",Toast.LENGTH_SHORT).show();
            }
            else {

                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
                latlngs.add(p1);

            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }




    }
        return latlngs;
    }
}
