package com.example.supplementaryassessmentit21018350;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.supplementaryassessmentit21018350.Database.DBHandlerUser;
import com.example.supplementaryassessmentit21018350.Session.SessionManagement;
import com.example.supplementaryassessmentit21018350.Session.UserM;

import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerUser handler = new DBHandlerUser(getApplicationContext());

                int result = handler.fetchLoginUserByUsername(username.getText().toString(),password.getText().toString());

                if(result != -1){
                    UserM user = new UserM(result, username.getText().toString());

                    SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                    sessionManagement.saveSession(user);

                    Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    moveToHomeActivity();

                }else{
                    Toast.makeText(MainActivity.this, "Password or Username is incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        long userID = sessionManagement.getSession();

        if(userID != -1){
            moveToHomeActivity();
        }
    }

    public void moveToHomeActivity(){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void signupClick(View view) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }
}