package com.example.ananya.charcha;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DostActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btnreg;
    EditText txtname, txtemail, txtpass, txtbio, txtloc, txtgender, txtprof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dostsignup);

        openHelper = new DBHelper(this);
        txtname = findViewById(R.id.name);
        txtemail = findViewById(R.id.email);
        txtpass = findViewById(R.id.pass);
        txtbio = findViewById(R.id.bio);
        txtloc = findViewById(R.id.loc);
        txtgender = findViewById(R.id.gen);
        txtprof= findViewById(R.id.prof);
        btnreg = findViewById(R.id.reg_butt);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String name=txtname.getText().toString();
                String email=txtemail.getText().toString();
                String pass=txtpass.getText().toString();
                String bio=txtbio.getText().toString();
                String loc=txtloc.getText().toString();
                String gender=txtgender.getText().toString();
                String prof=txtprof.getText().toString();
                insertdata(name, email,pass,bio,loc,gender,prof);
                Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();

            }
        });

    }
    public void insertdata(String name, String email, String pass, String bio, String loc, String gender, String prof){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_2, name);
        contentValues.put(DBHelper.COL_3, email);
        contentValues.put(DBHelper.COL_4, pass);
        contentValues.put(DBHelper.COL_5, bio);
        contentValues.put(DBHelper.COL_6, loc);
        contentValues.put(DBHelper.COL_7, gender);
        contentValues.put(DBHelper.COL_8, prof);
        long id = db.insert(DBHelper.TABLE_NAME, null, contentValues);
    }
}
