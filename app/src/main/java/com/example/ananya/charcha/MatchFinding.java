package com.example.ananya.charcha;

import android.database.Cursor;

import java.util.ArrayList;
import com.example.ananya.charcha.DBHelper;

public class MatchFinding {
    Post userdata = new Post();

    private class Post {
        String location;
    }

    public static ArrayList<String> possibleMatches(){
        DBHelper db;
        db = new DBHelper(null);
        Cursor cursor = db.alldata();

        ArrayList<String> list = new ArrayList<String>();


        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            list.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_5)));
            cursor.moveToNext();
        }

        return list;
    }
}
