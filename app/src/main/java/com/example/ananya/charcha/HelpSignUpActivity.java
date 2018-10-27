package com.example.ananya.charcha;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class HelpSignUpActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editTextSleep, editTextLocation, editTextGender;
    private ProgressBar progressBar;

    Button btnreg;
    private RadioGroup first, second, third, fourth, fifth;
    private RadioButton r1, r2, r3, r4 ,r5;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpsignup);

        openHelper = new DBHelper(this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextSleep = findViewById(R.id.edit_text_sleep);
        editTextLocation = findViewById(R.id.edit_text_location);
        editTextGender=findViewById(R.id.edit_text_gender);
        first = findViewById(R.id.radioGroup1);
        second = findViewById(R.id.radioGroup2);
        third = findViewById(R.id.radioGroup3);
        fourth = findViewById(R.id.radioGroup4);
        fifth = findViewById(R.id.radioGroup5);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        int rid1=first.getCheckedRadioButtonId();
        View rB1 = first.findViewById(rid1);
        int i1 = first.indexOfChild(rB1);
        r1 = (RadioButton) first.getChildAt(i1);

        int rid2=second.getCheckedRadioButtonId();
        View rB2 = second.findViewById(rid2);
        int i2 = second.indexOfChild(rB2);
        r2 = (RadioButton) second.getChildAt(i2);

        int rid3=third.getCheckedRadioButtonId();
        View rB3 = third.findViewById(rid3);
        int i3 = third.indexOfChild(rB3);
        r3 = (RadioButton) third.getChildAt(i3);

        int rid4=fourth.getCheckedRadioButtonId();
        View rB4 = fourth.findViewById(rid4);
        int i4 = fourth.indexOfChild(rB4);
        r4 = (RadioButton) fourth.getChildAt(i4);

        int rid5=fifth.getCheckedRadioButtonId();
        View rB5 = fifth.findViewById(rid5);
        int i5 = fifth.indexOfChild(rB5);
        r5 = (RadioButton) fifth.getChildAt(i5);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String gender = editTextGender.getText().toString();
                String sleep = editTextSleep.getText().toString();
                String location = editTextLocation.getText().toString();
                String ans1 = r1.getText().toString();
                final String ans2 = r2.getText().toString();
                final String ans3 = r3.getText().toString();
                final String ans4 = r4.getText().toString();
                final String ans5 = r5.getText().toString();
                String password = editTextPassword.getText().toString();

                insertdata(name, email, password, gender, location, sleep, ans1, ans2, ans3, ans4, ans5);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void insertdata(String name, String email, String pass, String gender, String loc, String sleep, String a1, String a2, String a3, String a4, String a5){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ATT_2, name);
        contentValues.put(DBHelper.ATT_10, email);
        contentValues.put(DBHelper.ATT_11, pass);
        contentValues.put(DBHelper.ATT_12, gender);
        contentValues.put(DBHelper.ATT_3, loc);
        contentValues.put(DBHelper.ATT_4, sleep);
        contentValues.put(DBHelper.ATT_5, a1);
        contentValues.put(DBHelper.ATT_6, a2);
        contentValues.put(DBHelper.ATT_7, a3);
        contentValues.put(DBHelper.ATT_8, a4);
        contentValues.put(DBHelper.ATT_9, a5);

        long id = db.insert(DBHelper.TABLE_NAME, null, contentValues);
    }
}