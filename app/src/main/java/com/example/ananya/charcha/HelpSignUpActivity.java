package com.example.ananya.charcha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ananya.charcha.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HelpSignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword, editTextSleep, editTextLocation, editTextGender;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private RadioGroup first, second, third, fourth, fifth;
    private RadioButton r1, r2, r3, r4 ,r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpsignup);

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

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_register).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
            Intent myIntent=new Intent(HelpSignUpActivity.this, LoginActivity.class);
            HelpSignUpActivity.this.startActivity(myIntent);
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String gender = editTextGender.getText().toString().trim();
        final String sleep = editTextSleep.getText().toString().trim();
        final String location = editTextLocation.getText().toString().trim();
        final String type = "user";
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

        final String ans1 = r1.getText().toString();
        final String ans2 = r2.getText().toString();
        final String ans3 = r3.getText().toString();
        final String ans4 = r4.getText().toString();
        final String ans5 = r5.getText().toString();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }



        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    gender,
                                    email,
                                    location,
                                    sleep,
                                    ans1,
                                    ans2,
                                    ans3,
                                    ans4,
                                    ans5,
                                    type
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(HelpSignUpActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(HelpSignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                registerUser();
                break;
        }
    }
}