package com.example.supplementaryassessmentit21018350;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supplementaryassessmentit21018350.Database.DBHandlerNotes;

public class EditNote extends AppCompatActivity {

    TextView header;
    EditText title, noteadd;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        DBHandlerNotes db = new DBHandlerNotes(getApplicationContext());

        header = findViewById(R.id.dynamicHead);
        title = findViewById(R.id.titlead);
        noteadd = findViewById(R.id.noteAd);
        submit = findViewById(R.id.addUpdateNote);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = db.addNote(title.getText().toString(), noteadd.getText().toString());

                if (id != -1){
                    Toast.makeText(EditNote.this, "Note added " + id, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditNote.this, Home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(EditNote.this, "Note was declined ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}