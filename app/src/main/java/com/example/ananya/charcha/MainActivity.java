package com.example.ananya.charcha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void helper(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, DostIntent.class);
        startActivity(myIntent);
    }

    public void help(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, UserIntent.class);
        startActivity(myIntent);
    }
}
