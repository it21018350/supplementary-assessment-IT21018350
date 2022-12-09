package com.example.supplementaryassessmentit21018350.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.supplementaryassessmentit21018350.R;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<NoteModle> {

    public NoteAdapter(Context context, List<NoteModle> notes){
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NoteModle noteModle = getItem(position);
//        if (convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell, parent, f);
//        }

        return super.getView(position, convertView, parent);
    }
}
