package com.example.ananya.charcha;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="dostregisteration";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Email";
    public static final String COL_4="Password";
    public static final String COL_5="Bio";
    public static final String COL_6="Location";
    public static final String COL_7="Gender";
    public static final String COL_8="Profession";
    public static final String TABLE2="userregistration";
    public static final String ATT_1="UID";
    public static final String ATT_2="uname";
    public static final String ATT_3="uloc";
    public static final String ATT_4="usleep";
    public static final String ATT_5="ua1";
    public static final String ATT_6="ua2";
    public static final String ATT_7="ua3";
    public static final String ATT_8="ua4";
    public static final String ATT_9="ua5";
    public static final String ATT_10="UEmail";
    public static final String ATT_11="UPAss";
    public static final String ATT_12="ugender";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Email TEXT,Password TEXT,Bio TEXT,Location TEXT, Gender TEXT, Profession TEXT)");
        db.execSQL("CREATE TABLE " + TABLE2 + " (UID INTEGER PRIMARY KEY AUTOINCREMENT,uname TEXT,UEmail TEXT,UPass TEXT,ugender TEXT,uloc TEXT, usleep TEXT, ua1 TEXT, ua2 TEXT, ua3 TEXT, ua4 TEXT, ua5 TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" +TABLE2);//Drop older table if exists
        onCreate(db);
    }

    public Cursor alldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from dostregistration where dostregistration.Location = userregistration.uloc", null);
        return cursor;
    }
}
