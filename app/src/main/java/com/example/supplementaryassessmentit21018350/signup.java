package com.example.supplementaryassessmentit21018350;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.supplementaryassessmentit21018350.Database.DBHandlerUser;

public class signup extends AppCompatActivity {

    EditText fname, uname, email, password;
    RadioButton male, female;
    Button create;
    Switch Agree;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = (EditText)findViewById(R.id.fullnameS);
        uname = (EditText)findViewById(R.id.usernameS);
        email = (EditText)findViewById(R.id.emailS);
        password = (EditText)findViewById(R.id.passwordS);
        male = (RadioButton)findViewById(R.id.maleS);
        female = (RadioButton)findViewById(R.id.femaleS);
        Agree = (Switch)findViewById(R.id.iAgreeS);
        create = (Button)findViewById(R.id.signupS);

        create.setEnabled(false);

        if(male.isChecked()){
            gender = "M";
        }else{
            gender = "F";
        }

        Agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    create.setEnabled(true);
                }else{
                    create.setEnabled(false);
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerUser dbHandlerUser = new DBHandlerUser(getApplicationContext());
                if ((uname.getText().toString() == "" && uname.getText().toString() == null) || (password.getText().toString() == "" && password.getText().toString() == null)){
                    Toast.makeText(signup.this, "required fields are empty", Toast.LENGTH_SHORT).show();
                }else{
                    long id = dbHandlerUser.addUser(fname.getText().toString(), uname.getText().toString(), gender, email.getText().toString(), password.getText().toString());

                    if(id != -1){
                        Toast.makeText(signup.this, "user created successfully", Toast.LENGTH_SHORT).show();
                        moveToLoginActivity();
                    }else{
                        Toast.makeText(signup.this, "user created unsuccessfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    public void tAndCClick(View view) {
        String url = "https://www.termsandconditionsgenerator.com/live.php?token=OTA2SBfYLXf9C4ragUily7XlWetUV9eM";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void moveToLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}