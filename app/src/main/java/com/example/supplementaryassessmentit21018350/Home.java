package com.example.supplementaryassessmentit21018350;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.supplementaryassessmentit21018350.Database.DBHandlerNotes;
import com.example.supplementaryassessmentit21018350.Database.DBHandlerUser;
import com.example.supplementaryassessmentit21018350.Session.SessionManagement;

public class Home extends AppCompatActivity {

    ListView lvContact;
    Button addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DBHandlerNotes noteHandler = new DBHandlerNotes(getApplicationContext());

        lvContact = findViewById(R.id.lvContact);
        addNote = findViewById(R.id.addNote);

        SimpleCursorAdapter simpleCursorAdapter = noteHandler.fetchAllNoteById();
        lvContact.setAdapter(simpleCursorAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String title = cursor.getString(1);
                int idNote = cursor.getInt(0);

                Intent intent = new Intent(Home.this, EditNote.class);
                intent.putExtra("noteId", idNote);
                startActivity(intent);
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, EditNote.class);
                startActivity(intent);
            }
        });

    }

    public void logout(View view) {
        SessionManagement sessionManagement = new SessionManagement(Home.this);
        sessionManagement.removeSession();

        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(Home.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}