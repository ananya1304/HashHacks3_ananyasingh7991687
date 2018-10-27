package com.example.ananya.charcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
    }
    public void reg(View view)
    {
        Intent myIntent = new Intent(UserIntent.this, HelpSignUpActivity.class);
        startActivity(myIntent);
    }

    public void lgn(View view)
    {
        Intent myIntent = new Intent(UserIntent.this, LoginActivity.class);
        startActivity(myIntent);
    }
}
