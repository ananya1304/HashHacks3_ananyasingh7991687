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

    public void help(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, HelpSignUpActivity.class);
        startActivity(myIntent);
    }
    public void login(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }
}
