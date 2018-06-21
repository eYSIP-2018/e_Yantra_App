package com.example.rajneesh.googlesignin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MapsOpenHelper extends SQLiteOpenHelper {
    public MapsOpenHelper(Context context) {
        super(context, "AppDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String expensesSql = "CREATE TABLE " + "MapsTable"  + " ( " +
                "Sno" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "clg_name" + " TEXT, " +
                "lat" + " DOUBLE, " +
                "long" + " DOUBLE)";
        db.execSQL(expensesSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
