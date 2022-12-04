package com.example.supplementaryassessmentit21018350;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }



    public void tAndCClick(View view) {
        String url = "https://www.termsandconditionsgenerator.com/live.php?token=OTA2SBfYLXf9C4ragUily7XlWetUV9eM";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }
}