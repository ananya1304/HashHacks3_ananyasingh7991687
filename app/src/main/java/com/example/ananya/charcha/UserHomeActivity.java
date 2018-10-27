package com.example.ananya.charcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ananya.charcha.R;

public class UserHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);
    }
    public void match(View view)
    {
        Intent myIntent = new Intent(UserHomeActivity.this, SwipeActivity.class);
        startActivity(myIntent);
    }

    public void bot(View view)
    {
        Intent myIntent = new Intent(UserHomeActivity.this, UserHomeActivity.class);
        startActivity(myIntent);
    }
    public void out(View view)
    {
        Intent myIntent = new Intent(UserHomeActivity.this, SignOutActivity.class);
        startActivity(myIntent);
    }
}
