package com.example.rajneesh.googlesignin.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.rajneesh.googlesignin.APIClient;
import com.example.rajneesh.googlesignin.CalenderResponse;
import com.example.rajneesh.googlesignin.Contract;
import com.example.rajneesh.googlesignin.Openhelper;
import com.example.rajneesh.googlesignin.R;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderActivity extends AppCompatActivity {
    android.widget.CalendarView calendarView;
    TextView description;
    SQLiteDatabase databaseread;
    SQLiteDatabase databasewrite;
    ArrayList<CalenderResponse> responses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
//        ContentResolver contentResolver = CalenderActivity.this.getContentResolver();
//        ContentValues cv = new ContentValues();
//        cv.put(CalendarContract.Events.TITLE, "event1");
//        cv.put(CalendarContract.Events.DESCRIPTION, "event1 described");
//        cv.put(CalendarContract.Events.EVENT_LOCATION, "delhi");
//        cv.put(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis());
//        cv.put(CalendarContract.Events.DTEND, Calendar.getInstance().getTimeInMillis() + 60 * 60 * 1000);
//        cv.put(CalendarContract.Events.CALENDAR_ID, 1);
//        cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
//        if (ActivityCompat.checkSelfPermission(CalenderActivity.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, cv);


//        Openhelper openhelper= Openhelper.getInstance(CalenderActivity.this);
//          databaseread= openhelper.getReadableDatabase();
//          databasewrite= openhelper.getWritableDatabase();
//        calendarView= findViewById(R.id.calendar);
//        description= findViewById(R.id.description);
//      //  databasewrite.execSQL("INSERT INTO events VALUES(456,\'2018-06-26\',\'blabla\',\'eyrc\')");
//         putindb();
        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView calendarView, int i, int i1, int i2) {
                Toast.makeText(CalenderActivity.this, "year:" + i + "mon:" + i1 + "date:" + i2, Toast.LENGTH_SHORT).show();
                Calendar calendar=Calendar.getInstance();
                calendar.set(i,i1,i2);
                String date_selected = i + "-" + i1 + "-" + i2;






//                if (ActivityCompat.checkSelfPermission(CalenderActivity.this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                 //   Toast.makeText(CalenderActivity.this, "Kindly Provide Permission", Toast.LENGTH_SHORT).show();
//                }
//                Cursor cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null, null);
//
//                while (cursor.moveToNext()) {
//                    if (cursor != null) {
//                        String id = cursor.getString(cursor.getColumnIndex(CalendarContract.Events._ID));
//                        String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
//                        String description = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
//                        String event = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION));
//                    //    Toast.makeText(CalenderActivity.this, title, Toast.LENGTH_SHORT).show();
//                    } else {
//                    //    Toast.makeText(CalenderActivity.this, "no event", Toast.LENGTH_SHORT).show();
//                    }
//                }

//                Date date_selected= new Date(calendar.getTimeInMillis());






//                String[] args={date_selected};
//                Cursor cursor= databaseread.rawQuery("SELECT * FROM events WHERE date=?",args);
//                if(cursor!=null)
//                Toast.makeText(CalenderActivity.this, "came till here", Toast.LENGTH_SHORT).show();
//
//
//                if (cursor.moveToFirst()){
//                    Toast.makeText(CalenderActivity.this, "in if", Toast.LENGTH_SHORT).show();
//                    String desc= cursor.getString(cursor.getColumnIndex(Contract.Events.DESCRIPTION));
//                    description.setText(desc);
//
//                }
//                else{
//                    Toast.makeText(CalenderActivity.this, "nothing got yet", Toast.LENGTH_SHORT).show();
//                }
//
//
           }
        });





    }
    public void putindb(){
      //  Toast.makeText(this, "top", Toast.LENGTH_SHORT).show();

        Call<List<CalenderResponse>> call= APIClient.getInstance().getApi().getEvents();
        responses=new ArrayList<>();
        call.enqueue(new Callback<List<CalenderResponse>>() {
            @Override
            public void onResponse(Call<List<CalenderResponse>> call, Response<List<CalenderResponse>> response) {
                responses=(ArrayList<CalenderResponse>) response.body();
                Toast.makeText(CalenderActivity.this, "response came", Toast.LENGTH_SHORT).show();
                for(int i=0;i<responses.size();i++){
                    String date= responses.get(i).getDat();
                    String desc= responses.get(i).getDesc();
                    String ini= responses.get(i).getInit();
                    ContentValues cv= new ContentValues();
                    cv.put(Contract.Events.ID,i);
                    cv.put(Contract.Events.DATE,date);
                    cv.put(Contract.Events.DESCRIPTION,desc);
                    cv.put(Contract.Events.INITIATIVE,ini);
                    databasewrite.insert("events",null,cv);
                 //   databasewrite.execSQL("INSERT INTO events VALUES('"+i+"','"+date+"','"+desc+"','"+ini+"')");

                }
            }

            @Override
            public void onFailure(Call<List<CalenderResponse>> call, Throwable t) {
               // Toast.makeText(CalenderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
