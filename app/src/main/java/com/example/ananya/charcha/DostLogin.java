package com.example.ananya.charcha;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

public class DostLogin extends AppCompatActivity {
    private static final String APP_ID = "9DA1B1F4-0BE6-4DA8-82C5-2E81DAB56F23";
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    EditText txtEmail, txtPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtEmail=findViewById(R.id.loginEmail);
        txtPass=findViewById(R.id.loginpaswd);
        btnLogin=findViewById(R.id.btnLogIn);
        openHelper=new DBHelper(this);
        db = openHelper.getReadableDatabase();
        SendBird.init(APP_ID, this.getApplicationContext());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                String userId=email;
                String nickname="Anonymous";
                connectToSendBird(userId, nickname);
                cursor = db.rawQuery("SELECT *FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.COL_3 + "=? AND " + DBHelper.COL_4 + "=?", new String[]{email, pass});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void connectToSendBird(final String userId, final String userNickname) {
        btnLogin.setEnabled(false);

        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {

                if (e != null) {
                    // Error!
                    Toast.makeText(
                            DostLogin.this, "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    btnLogin.setEnabled(true);
                    return;
                }

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);

                Intent intent = new Intent(DostLogin.this, ChatActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateCurrentUserInfo(String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(
                            DostLogin.this, "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

            }
        });
    }
}
