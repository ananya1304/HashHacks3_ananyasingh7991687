package com.example.ananya.charcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DostIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dost);
    }
    public void up(View view)
    {
        Intent myIntent = new Intent(DostIntent.this, DostActivity.class);
        startActivity(myIntent);
    }

    public void in(View view)
    {
        Intent myIntent = new Intent(DostIntent.this, DostLogin.class);
        startActivity(myIntent);
    }
}

