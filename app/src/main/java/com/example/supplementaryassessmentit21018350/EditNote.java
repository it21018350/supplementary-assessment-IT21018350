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

import java.util.ArrayList;

public class EditNote extends AppCompatActivity {

    TextView header;
    EditText title, noteadd;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        int value = -1;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getInt("noteId");
        }

        DBHandlerNotes db = new DBHandlerNotes(getApplicationContext());

        header = findViewById(R.id.dynamicHead);
        title = findViewById(R.id.titlead);
        noteadd = findViewById(R.id.noteAd);
        submit = findViewById(R.id.addUpdateNote);
        boolean isAdd;

        if(value != -1){
            isAdd = false;
            header.setText("Update Note "+ value);
            ArrayList list = new ArrayList(db.fetchNotebyID(value));
            title.setText(list.get(0).toString());
            noteadd.setText(list.get(1).toString());
        }else{
            header.setText("Add Note");
            isAdd = true;
        }

        int finalValue = value;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdd) {
                    long id = db.addNote(title.getText().toString(), noteadd.getText().toString());

                    if (id != -1) {
                        Toast.makeText(EditNote.this, "Note added " + id, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditNote.this, Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(EditNote.this, "Note was declined ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Integer intObj = Integer.valueOf(finalValue);
                    long count = db.updateNote(intObj, title.getText().toString(), noteadd.getText().toString());

                    if (count != 0) {
                        Toast.makeText(EditNote.this, "Note updated ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditNote.this, Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(EditNote.this, "Note was declined ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}